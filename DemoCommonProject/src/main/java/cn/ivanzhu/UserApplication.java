package cn.ivanzhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author ivanzhu
 * @date 2020/1/7
 * @time 16:01
 */
@SpringBootApplication(scanBasePackages = "cn.ivanzhu")
@EnableDiscoveryClient
public class UserApplication {
    @Resource
    private RestTemplateBuilder builder;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return builder.build();
    }
}
