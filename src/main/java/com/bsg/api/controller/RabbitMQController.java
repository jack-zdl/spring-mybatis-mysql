package com.bsg.api.controller;

import com.bsg.api.service.qmq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * connect mq testunit
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitMQController {

    @Autowired
    private Producer producer;

    @GetMapping("/set")
    @ResponseBody
    public void setValue(){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", "hello rabbitmq");
            String exchange_key = "spring.queue.tag.key";
            String queue_key = "spring.queue.tag";
            producer.sendQueue(exchange_key,queue_key,map);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
