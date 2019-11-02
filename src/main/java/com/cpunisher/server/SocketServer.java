package com.cpunisher.server;

import com.cpunisher.entity.MessageData;
import com.cpunisher.model.User;
import com.cpunisher.service.RoomService;
import com.cpunisher.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/multiplayer/{roomId}", configurator = HttpSessionConfigurator.class)
@Component
public class SocketServer {

    @Autowired
    private RoomService roomService;

    @OnOpen
    public void onOpen(@PathParam("roomId") String roomId, Session session, EndpointConfig endpointConfig) {
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        String openId = ((User)httpSession.getAttribute("user")).getOpenId();
        roomService.playerJoin(roomId, openId, session);
    }

    @OnClose
    public void onClose(@PathParam("roomId") String roomId, Session session) {
        roomService.playerLeave(roomId, session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        MessageData messageData = JsonHelper.parse(message, MessageData.class);

        switch (messageData.getMessageType()) {
            case Start:
                roomService.startGame(messageData, session);
                break;
            case Answer:
                roomService.answer(messageData, session);
                break;
        }
    }

    @OnError
    public void onError(Throwable error, Session session) {
        error.printStackTrace();
    }
}
