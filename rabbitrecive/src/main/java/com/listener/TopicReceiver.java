package com.listener;


import com.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    @RabbitHandler
    @RabbitListener(queues = "topic.first")
    public void process1(User user) {
        System.out.println("TopicFirstReceiver1消费者收到消息  : " + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.second")
    public void process2(User user) {
        System.out.println("TopicSecondReceiver2消费者收到消息  : " + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
    }

}
