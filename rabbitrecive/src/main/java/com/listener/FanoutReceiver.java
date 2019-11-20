package com.listener;


import com.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void processC(User user) {
        System.out.println("FanoutReceiverCC消费者收到消息  : " + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void processA(User user) {
        System.out.println("FanoutReceiverAA消费者收到消息  : " + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void processB(User user) {
        System.out.println("FanoutReceiverBB消费者收到消息  : " + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
    }

}
