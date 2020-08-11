package www.ivanzhu.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.ivanzhu.com.feign.UserFeignClient;

import javax.annotation.Resource;

/**
 * @author ivanzhu
 * @date 2020/8/10
 * @time 18:16
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping("/user/list")
    public String allUser(){
        return userFeignClient.allUsers();
    }
}
