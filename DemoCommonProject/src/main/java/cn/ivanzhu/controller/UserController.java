package cn.ivanzhu.controller;

import cn.ivanzhu.controller.param.UserSaveParam;
import cn.ivanzhu.model.UserPO;
import cn.ivanzhu.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

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
        String url = "http://127.0.0.1:8000/api/usercenter/tenant/detail";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("tenantId", "5284de3a-0a0f-4a61-a90e-3aeb69a63230");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(map);
        ResponseEntity<String> exchange = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, String.class);
        return String.valueOf(exchange);
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

}
