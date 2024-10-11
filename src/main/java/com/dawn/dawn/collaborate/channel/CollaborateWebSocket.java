package com.dawn.dawn.collaborate.channel;

import com.dawn.dawn.common.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 陈黎明
 * @date 2024/9/26 下午4:31
 */
//@Data
@Slf4j
@Component
@ServerEndpoint(value = "/abcd/{roomId}")
public class CollaborateWebSocket {

    private String userId;

    private String roomId;

    private Session session;

    private static CopyOnWriteArraySet<CollaborateWebSocket> webSockets= new CopyOnWriteArraySet<>();

    private static ConcurrentMap<String, CollaborateWebSocket> sessionPool = new ConcurrentHashMap<>();

    private static ConcurrentMap<String, User> userPool = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "roomId") String roomId){
        try {
            User user = getUser(session);
            this.session = session;
            this.userId = user.getUserId();
            this.roomId = roomId;
            webSockets.add(this);
            sessionPool.put(roomId, this);
            session.getAsyncRemote().sendText("你好");
            log.info("【websocket消息】有新的连接，总数为:"+webSockets.size());
        }catch (Exception e){
            log.error("onOpen error: {}", e.getMessage());
        }

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // 处理 WebSocket 错误，例如记录日志或处理连接异常
        throwable.printStackTrace();
    }

    @OnMessage
    public String onMessage(String message, Session session) {
//        session.getAsyncRemote().sendText("Received: " + message);
        return new StringBuilder(message).reverse().toString();
    }

    public User getUser(Session session) {
        UsernamePasswordAuthenticationToken userPrincipal =(UsernamePasswordAuthenticationToken) session.getUserPrincipal();
        return (User) userPrincipal.getPrincipal();
    }

    public User getUser() {
        UsernamePasswordAuthenticationToken userPrincipal =(UsernamePasswordAuthenticationToken) session.getUserPrincipal();
        return (User) userPrincipal.getPrincipal();
    }


}
