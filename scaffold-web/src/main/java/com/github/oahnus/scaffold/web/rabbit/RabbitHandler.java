package com.github.oahnus.scaffold.web.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by oahnus on 2019/6/13
 * 16:30.
 */
@Service
public class RabbitHandler {
    @RabbitListener(queues = RabbitQueues.TEST)
    public void handleTestQueue(Date date) {
        System.out.println("Handle Queue " + date);
    }
}
