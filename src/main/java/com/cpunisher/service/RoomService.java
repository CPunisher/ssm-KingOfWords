package com.cpunisher.service;

import com.cpunisher.entity.MessageData;
import com.cpunisher.entity.Room;

import javax.websocket.Session;
import java.util.Map;

public interface RoomService {

    void distribute(String roomId);

    void answer(MessageData messageData, Session session);

    void sendPlayerScore(String roomId);

    void playerJoin(String roomId, String openId, Session session);

    void playerLeave(String roomId, Session session);

    void startGame(MessageData messageData, Session session);

    void endGame(String roomId);

    String getAvailableRoomId();

    Map<String, Room> getRooms();
}