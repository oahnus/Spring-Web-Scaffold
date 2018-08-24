package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by oahnus on 2018/8/24
 * 15:31.
 */
@Slf4j
@Component
public class RabbitService {
    @RabbitListener(queues = "test-queue")
    public void sendCaptcha(Object paylaod) {
        log.debug("[RabbitService] - receive payload {}", paylaod);
    }
}
