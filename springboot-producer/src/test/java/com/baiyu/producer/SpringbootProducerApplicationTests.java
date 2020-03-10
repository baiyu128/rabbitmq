package com.baiyu.producer;

import com.baiyu.producer.entity.Order;
import com.baiyu.producer.mq.RabbitSender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootProducerApplicationTests {

    @Resource
    private RabbitSender rabbitSender;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSender1() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put("number", "12345");
        properties.put("send_time", new Date());
        rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);
        Thread.sleep(2000);
    }

    @Test
    public void testSender2() throws Exception {
        Order order = new Order("001", "第一个订单");
        rabbitSender.sendOrder(order);
        //防止资源提前关闭，ConfirmCallback异步回调失败
        Thread.sleep(2000);
    }

}
