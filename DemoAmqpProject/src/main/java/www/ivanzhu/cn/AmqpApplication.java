package www.ivanzhu.cn;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ivanzhu
 * @time 2020/8/30 15:26
 * @Version 1.0
 */

@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
public class AmqpApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmqpApplication.class, args);
    }
}
