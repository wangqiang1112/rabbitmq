package com.listener;


import com.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "CalonDirectQueue")
public class DirectReceiver {

    @RabbitHandler
    public void process(User user) {
        System.out.println("DirectReceiver消费者收到消息  : "
                + user.getId()+","+user.getUsername()+","+user.getPassword()+","+user.getType());
    }

}
