package com.dawn.dawn.rabbitmq.constant;

/**
 * @author chenliming
 * @date 2024/6/2 下午2:28
 */
public class RabbitMqConstant {
    /**
     * 延迟消息交换机
     */
    public static final String X_DELAYED_MESSAGE = "x-delayed-message";
    /**
     * 消息交换机
     */
    public static final String MAIL_MESSAGE_EXCHANGE = "mail:exchange";
    /**
     * 邮件消息队列
     */
    public static final String MAIL_MESSAGE_QUEUE = "mail:message";
    /**
     * 邮件消息路由
     */
    public static final String MAIL_MESSAGE_ROUTING_KEY = "mail:router";
}
