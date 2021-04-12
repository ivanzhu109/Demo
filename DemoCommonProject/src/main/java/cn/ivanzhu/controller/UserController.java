package cn.ivanzhu.controller;

import cn.ivanzhu.controller.param.UserSaveParam;
import cn.ivanzhu.model.UserPO;
import cn.ivanzhu.service.UserService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
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
        properties.put("created_number", 1);
        properties.put("tenant_id", "24852d91-5c43-4f67-99c5-e6e441337749");
        properties.put("company_name", "新核云测试账号");
        SensorsEvent event = SensorsEvent.builder()
                .distinct_id("24852d91-5c43-4f67-99c5-e6e441337749")
                .time(System.currentTimeMillis())
                .type("track")
                .event("i_count_of_procurement_create")
                .properties(properties)
                .build();

        String base64 = Base64.getEncoder().encodeToString(JSON.toJSONString(event).getBytes());
        System.out.println(base64);
        MultiValueMap<String, String> urlMap = new LinkedMultiValueMap<>(2);
        urlMap.add("project", "default");
        urlMap.add("token", "33c3c3e1cc577f3b");
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl("https://xhy.datasink.sensorsdata.cn/sa").queryParams(urlMap);
//        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>(2);
        String utf8 = URLEncoder.encode(base64, "UTF8");
        System.out.println(utf8);
        urlMap.clear();
        urlMap.add("gzip", "0");
        urlMap.add("data", base64);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(urlMap, headers);
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.build().encode().toUri(), requestEntity, String.class);
//        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, requestEntity, String.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        return "状态码：" + statusCodeValue + "，结果：" + responseEntity.getBody();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SensorsEvent {
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

//        SensorsEvent event = SensorsEvent.builder()
//                .distinct_id("24852d91-5c43-4f67-99c5-e6e441337749")
//                .time(System.currentTimeMillis())
//                .type("track")
//                .event("i_count_of_procurement_create")
//                .properties(Collections.emptyMap())
//                .build();
//
//        String base64 = Base64.getEncoder().encodeToString(JSON.toJSONString(event).getBytes());
//        System.out.println(base64);
//        String urlEncode = URLEncoder.encode(base64, "UTF8");
//        System.out.println(urlEncode);
//        System.out.println(URLEncoder.encode(urlEncode, "UTF8"));
        String utf8 = URLDecoder.decode("eyJkaXN0aW5jdF9pZCI6IjI0ODUyZDkxLTVjNDMtNGY2Ny05OWM1LWU2ZTQ0MTMzNzc0OSIsInRpbWUiOjE2MTcyNDg0MDA3MDcsInR5cGUiOiJ0cmFjayIsImV2ZW50IjoiaV9jb3VudF9vZl9vcmRlcl9jcmVhdGUiLCJwcm9wZXJ0aWVzIjp7ImNyZWF0ZWRfbnVtYmVyIjoxLCJ0ZW5hbnRfaWQiOiIyNDg1MmQ5MS01YzQzLTRmNjctOTljNS1lNmU0NDEzMzc3NDkiLCJjb21wYW55X25hbWUiOiLmlrDmoLjkupHmtYvor5UifX0%3D", "UTF8");
        System.out.println(new String(Base64.getDecoder().decode(utf8.getBytes())));
        String utf82 = URLDecoder.decode("eyJkaXN0aW5jdF9pZCI6IjI0ODUyZDkxLTVjNDMtNGY2Ny05OWM1LWU2ZTQ0MTMzNzc0OSIsImV2ZW50IjoiaV9jb3VudF9vZl9vcmRlcl9jcmVhdGUiLCJwcm9wZXJ0aWVzIjp7InRlbmFudF9pZCI6IjI0ODUyZDkxLTVjNDMtNGY2Ny05OWM1LWU2ZTQ0MTMzNzc0OSIsImNyZWF0ZWRfbnVtYmVyIjozLCJjb21wYW55X25hbWUiOiLmlrDmoLjkupHmtYvor5UifSwidGltZSI6MTYxNzI0ODM5NjM2NywidHlwZSI6InRyYWNrIn0%3D", "UTF8");
        System.out.println(new String(Base64.getDecoder().decode(utf82.getBytes())));
    }


}
