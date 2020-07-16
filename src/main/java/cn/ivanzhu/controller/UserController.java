package cn.ivanzhu.controller;

import cn.ivanzhu.controller.param.UserSaveParam;
import cn.ivanzhu.model.CustomerFinanceDetailDTO;
import cn.ivanzhu.model.TestModel;
import cn.ivanzhu.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author ivanzhu
 * @date 2020/1/7
 * @time 15:50
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RestTemplate restTemplate;

//    @Resource
//    private TestModel testModel;

    @GetMapping("/c0/json")
    public String jsonTest() {
        List<Long> longs = JSON.parseArray("[\"2020-04-02\",\"2020-04-02\"]", Long.class);
        System.out.println("longs = " + longs);
        return StringUtils.EMPTY;
    }

    @GetMapping("/http")
    public String httpMethod() throws Exception {
        String url = "http://127.0.0.1:8000/api/usercenter/tenant/detail";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("tenantId", "5284de3a-0a0f-4a61-a90e-3aeb69a63230");
//        HttpHeaders headers = new HttpHeaders();
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode objectNode = objectMapper.createObjectNode();
//        objectNode.put("tenantId","5284de3a-0a0f-4a61-a90e-3aeb69a63230");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<String>(objectNode.toString(), headers);
//        Map forObject = restTemplate.getForObject(url, Map.class, map);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(map);
        ResponseEntity<String> exchange = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, String.class);
//        String responseEntity = restTemplate.postForObject(url, entity, String.class);
        return String.valueOf(exchange);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserSaveParam param){
        userService.addUser(param);
        return "success";
    }

    @GetMapping("/test")
    public String exportExcel(){
//        System.out.println(testModel);
        Class<?> clazz = CustomerFinanceDetailDTO.builder().build().getClass();
        Field[] fields = clazz.getDeclaredFields();
        return "成功";
    }


}
