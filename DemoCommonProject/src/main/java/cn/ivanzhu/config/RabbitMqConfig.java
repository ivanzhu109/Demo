package cn.ivanzhu.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author ivanzhu
 * @time 2020/8/30 21:14
 * @Version 1.0
 */
@Configuration
public class RabbitMqConfig {

    @Bean(name = "test.exchange")
    public TopicExchange createTopicExchange() {
        return new TopicExchange("test.exchange.topic", true, false);
    }

//    @Bean(name = "test.queue")
//    public Queue createQueue1() {
//        return new Queue("test.queue", true, false, false);
//    }
//
//    @Bean(name = "test.queue.haha")
//    public Queue createQueue2() {
//        return new Queue("test.queue.haha", true, false, false);
//    }
//
//    @Bean(name = "haha.test.queue")
//    public Queue createQueue3() {
//        return new Queue("haha.test.queue", true, false, false);
//    }
//
//    @Bean
//    public Binding createBinding1(@Qualifier(value = "test.exchange") TopicExchange topicExchange,
//                                  @Qualifier(value = "test.queue") Queue queue) {
//        return BindingBuilder.bind(queue).to(topicExchange).with("test.queue");
//    }
//
//    @Bean
//    public Binding createBinding2(@Qualifier(value = "test.exchange") TopicExchange topicExchange,
//                                  @Qualifier(value = "test.queue.haha") Queue queue) {
//        return BindingBuilder.bind(queue).to(topicExchange).with("#.test.queue.#");
//    }
//
//    @Bean
//    public Binding createBinding3(@Qualifier(value = "test.exchange") TopicExchange topicExchange,
//                                  @Qualifier(value = "haha.test.queue") Queue queue) {
//        return BindingBuilder.bind(queue).to(topicExchange).with("haha.#");
//    }


    @Resource
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitMqTemplate() {
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            System.out.println(String.format("消息的唯一信息：%s", JSON.toJSONString(correlationData)));
            System.out.println(String.format("是否投递成功：%s", ack));
            System.out.println(String.format("消息失败原因：%s", cause));
        });

        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
            System.out.println(String.format("消息的信息：%s", JSON.toJSONString(message)));
            System.out.println(String.format("响应状态码：%s", replyCode));
            System.out.println(String.format("响应状态信息：%s", replyText));
            System.out.println(String.format("交换机名称：%s", exchange));
            System.out.println(String.format("路由键：%s", routingKey));
        });
    }

}
