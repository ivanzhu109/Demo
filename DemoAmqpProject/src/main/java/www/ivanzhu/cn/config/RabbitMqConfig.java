package www.ivanzhu.cn.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ivanzhu
 * @time 2020/8/30 21:43
 * @Version 1.0
 */
@Configuration
public class RabbitMqConfig {
    @Bean(name = "test.queue")
    public Queue createQueue1(){
        return new Queue("test.queue", true, false, false);
    }

    @Bean(name = "test.queue.haha")
    public Queue createQueue2(){
        return new Queue("test.queue.haha", true, false, false);
    }

    @Bean(name = "haha.test.queue")
    public Queue createQueue3(){
        return new Queue("haha.test.queue", true, false, false);
    }
}
