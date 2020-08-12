package www.ivanzhu.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ivanzhu
 * @date 2020/8/12
 * @time 14:49
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator myRotes(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(p -> p
//                        .path("/user/**")
//                        .uri("http://127.0.0.1:80/user/config")
//                )
//                .build();
//    }
}
