package cn.ivanzhu.controller;

import cn.ivanzhu.controller.param.UserSaveParam;
import cn.ivanzhu.model.UserPO;
import cn.ivanzhu.service.UserService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ivanzhu
 * @date 2020/1/7
 * @time 15:50
 */
@RequestMapping("/user")
@RestController
@RefreshScope
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RestTemplate restTemplate;

    @Value("${test.user.name}")
    private String name;

    @Value("${test.user.age}")
    private Integer age;

    @GetMapping("/http")
    public String httpMethod() throws Exception {
        Map<String, Object> properties = Maps.newHashMapWithExpectedSize(3);
        properties.put("created_number", 2);
        properties.put("tenant_id", "24852d91-5c43-4f67-99c5-e6e441337749");
        properties.put("company_name", "新核云测试账号");
        SensorsEvent event = SensorsEvent.builder()
                .distinct_id("24852d91-5c43-4f67-99c5-e6e441337749")
                .time(System.currentTimeMillis())
                .type("track")
                .event("i_count_of_workorder_create")
                .properties(properties)
                .build();

        String base64 = Base64.getEncoder().encodeToString(JSON.toJSONString(event).getBytes());
        System.out.println(base64);
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("project", "default");
        paramMap.add("token", "33c3c3e1cc577f3b");
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl("https://xhy.datasink.sensorsdata.cn/sa").queryParams(paramMap);
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("gzip", 0);
        String utf8 = URLEncoder.encode(base64, "UTF8");
        System.out.println(utf8);
        objectNode.put("data", utf8);
//        paramMap.add("gzip", "0");
//        paramMap.add("data", base64);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestEntity = new HttpEntity<>(objectNode.toString(), headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, requestEntity, String.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        return "状态码：" + statusCodeValue + "，结果：" + responseEntity.getBody();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SensorsEvent{
        private String distinct_id;
        private long time;
        private String type;
        private String event;
        private Map<String, Object> properties;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserSaveParam param) {
        userService.addUser(param);
        return "success";
    }

    @GetMapping("/list")
    public String getUserList() {
        List<UserPO> users = userService.listAllUser();
        return JSON.toJSONString(users);
    }

    @GetMapping("/config")
    public String getConfig() {
        return "姓名：" + name + "， 年龄：" + age;
    }


    public static void main(String[] args) throws Exception {
//        // 从神策分析获取的数据接收的 URL
//        final String SA_SERVER_URL = "https://xhy.datasink.sensorsdata.cn/sa?project=default&token=safa93a4b4";
//        // 使用 Debug 模式，并且导入 Debug 模式下所发送的数据
//        final boolean SA_WRITE_DATA = true;
//
//        // 使用 DebugConsumer 初始化 SensorsAnalytics
//        final SensorsAnalytics sa = new SensorsAnalytics(
//                new SensorsAnalytics.DebugConsumer(SA_SERVER_URL, SA_WRITE_DATA));
//        sa.track("24852d91-5c43-4f67-99c5-e6e441337749", true, "i_count_of_order_create");

        SensorsEvent event = SensorsEvent.builder()
                .distinct_id("24852d91-5c43-4f67-99c5-e6e441337749")
                .time(System.currentTimeMillis())
                .type("track")
                .event("i_count_of_procurement_create")
                .properties(Collections.emptyMap())
                .build();

        String base64 = Base64.getEncoder().encodeToString(JSON.toJSONString(event).getBytes());
        System.out.println(base64);
        String urlEncode = URLEncoder.encode(base64, "UTF8");
        System.out.println(urlEncode);
        System.out.println(URLEncoder.encode(urlEncode, "UTF8"));
    }














}
