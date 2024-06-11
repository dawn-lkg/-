package com.dawn.dawn.rabbitmq.config;

import com.dawn.dawn.rabbitmq.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenliming
 * @date 2024/5/28 下午4:15
 */

@Slf4j
@Configuration
public class RabbitMqConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


    @Bean
    public CustomExchange mailExchange() {
        Map<String,Object> args = new HashMap<String,Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitMqConstant.MAIL_MESSAGE_EXCHANGE, "x-delayed-message",true, false, args);
    }

    @Bean
    public Queue mailMessageQueue(){
        return new Queue(RabbitMqConstant.MAIL_MESSAGE_QUEUE);
    }

    @Bean
    public Binding bindingMailMessageQueue(Queue mailMessageQueue, CustomExchange mailExchange){
        return BindingBuilder.bind(mailMessageQueue).to(mailExchange).with(RabbitMqConstant.MAIL_MESSAGE_ROUTING_KEY).noargs();
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        log.info("消息到交换机是否成功："+b+" CorrelationData="+correlationData);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("消息到队列是否成功");
    }
}

