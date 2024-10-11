package com.dawn.dawn.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author chenliming
 * @date 2023/10/4 22:41
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.setUrlPathHelper(new UrlPathHelper());
//        registry.addEndpoint("/**").setAllowedOrigins("*");
//
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/");
//    }
//
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                if(StompCommand.CONNECT.equals(accessor.getCommand())){
//                    String firstNativeHeader = accessor.getFirstNativeHeader(Constants.TOKEN_WEBSOCKET_NAME);
//                    if(firstNativeHeader != null){
//
//                        System.out.println("token: " + firstNativeHeader);
//                    }
//                }
//                return ChannelInterceptor.super.preSend(message, channel);
//            }
//        });
//    }
}

