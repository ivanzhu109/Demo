package www.ivanzhu.cn.controller;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ivanzhu
 * @time 2020/8/30 15:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/")
public class AmqpController {

    private static final String TEST_QUEUE_1 = "test.queue";
    private static final String TEST_QUEUE_2 = "test.queue.haha";
    private static final String TEST_QUEUE_3 = "haha.test.queue";

    @RabbitListener(queues = TEST_QUEUE_1)
    public void listenMessage1(Message message, String msg , Channel channel) throws IOException {
        System.out.println(JSON.toJSONString(message));
        System.out.println(String.format("队列1>> 消费消息成功， 消息id：%s, 通道id：%s, 消息信息：%s",
                message.getMessageProperties().getMessageId(),
                message.getMessageProperties().getDeliveryTag(),
                msg));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
    @RabbitListener(queues = TEST_QUEUE_2)
    public void listenMessage2(Message message, String msg , Channel channel) throws IOException {
        System.out.println(JSON.toJSONString(message));
        System.out.println(String.format("队列2>> 消费消息成功， 消息id：%s, 通道id：%s, 消息信息：%s",
                message.getMessageProperties().getMessageId(),
                message.getMessageProperties().getDeliveryTag(),
                msg));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
    @RabbitListener(queues = TEST_QUEUE_3)
    public void listenMessage3(Message message, String msg , Channel channel) throws IOException {
        System.out.println(JSON.toJSONString(message));
        System.out.println(String.format("队列3>> 消费消息成功， 消息id：%s, 通道id：%s, 消息信息：%s",
                message.getMessageProperties().getMessageId(),
                message.getMessageProperties().getDeliveryTag(),
                msg));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @GetMapping("/")
    public String index(){
        return "hello";
    }
}
