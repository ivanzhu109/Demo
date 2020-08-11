package www.ivanzhu.com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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
