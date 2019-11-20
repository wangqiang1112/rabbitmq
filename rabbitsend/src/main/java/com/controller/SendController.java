package com.controller;


import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class SendController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/sendDirect")
    @ResponseBody
    private String sendDirect(String message) throws Exception {
        User user = new User("1", message, "1", "1");
        template.convertAndSend("CalonDirectExchange", "CalonDirectRouting", objectMapper.writeValueAsString(user));
        return "OK,sendDirect:"+message;
    }

    @GetMapping("/sendTopicFirst")
    @ResponseBody
    private String sendTopicFirst(String message) {
        User user = new User(UUID.randomUUID().toString(), message, "123456", "sendTopicFirst");
        template.convertAndSend("topicExchange", "topic.first", user);
        return "OK,sendTopicFirst:" + message;
    }

    @GetMapping("/sendTopicSecond")
    @ResponseBody
    private String sendTopicSecond(String message) {
        User user = new User(UUID.randomUUID().toString(), message, "123456", "sendTopicSecond");
        template.convertAndSend("topicExchange", "topic.second", user);
        return "OK,sendTopicSecond:" + message;
    }

    @GetMapping("/sendFanout")
    private @ResponseBody String sendFanout(String message) {
        User user = new User(UUID.randomUUID().toString(), message, "123456", "sendFanout");
        template.convertAndSend("fanoutExchange", null, user);
        return "OK,sendFanout:" + message;
    }

}
