package cn.ivanzhu.controller.param;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ivanzhu
 * @time 2020/8/30 15:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/mq")
public class RabbitMqController {

    private static final String TEST_ROUTING_KEY = "test.queue";
    private static final String TEST_EXCHANGE = "test_exchange";
    private static final String TEST_EXCHANGE_2 = "test_exchange_fanout";
    private static final String TEST_EXCHANGE_3 = "test.exchange.topic";

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMq(@RequestParam("num") Integer num) {
        if (num == null){
            return "参数错误，num不能为空";
        }
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend(TEST_EXCHANGE_3, TEST_ROUTING_KEY, "消息" + i, new CorrelationData("123123"));
        }
        return "发送消息通知成功";
    }
}
