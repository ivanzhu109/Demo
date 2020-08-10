package www.ivanzhu.com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ivanzhu
 * @date 2020/8/10
 * @time 18:17
 */
@FeignClient("demo")
public interface UserFeignClient {

    @GetMapping("/user/list")
    String allUsers();
}
