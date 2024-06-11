package com.dawn.dawn.rabbitmq.consumer;

import com.dawn.dawn.common.core.utils.EmailSender;
import com.dawn.dawn.common.system.entity.UserInfo;
import com.dawn.dawn.common.system.service.UserService;
import com.dawn.dawn.rabbitmq.constant.RabbitMqConstant;
import com.dawn.dawn.todo.entity.Todo;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author chenliming
 * @date 2024/5/28 下午5:26
 */
@Slf4j
@Component
public class MailRecevier {

    @Autowired
    UserService userService;
    @Autowired
    EmailSender mailSender;

    @RabbitListener(queues = RabbitMqConstant.MAIL_MESSAGE_QUEUE)
    public void receiveMessage(Todo todo, Message message, Channel channel) throws Exception {
        try {
            log.info("消息接受："+todo);
            UserInfo userInfo = userService.getUserInfo(todo.getUserId());
            String mail = Optional.ofNullable(userInfo).map(UserInfo::getEmail).orElse(null);
            if(Objects.isNull(mail)){
                return;
            }
            mailSender.sendEmail(mail,todo.getName(),todo.getDescription());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            // 处理异常，重新投递
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

    }
}
