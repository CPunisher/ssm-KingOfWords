package com.cpunisher.service.impl;

import com.cpunisher.constant.MessageType;
import com.cpunisher.entity.*;
import com.cpunisher.model.User;
import com.cpunisher.model.Word;
import com.cpunisher.service.RoomService;
import com.cpunisher.service.UserService;
import com.cpunisher.service.WordService;
import com.cpunisher.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomServiceImpl implements RoomService {

    private static Map<String, Room> rooms = new ConcurrentHashMap<>();
    private static List<String> roomIdPool = new ArrayList<>();
    static {
        for (int i = 1; i <= 50; i++) roomIdPool.add(String.valueOf(i));
    }

    @Autowired
    private UserService userService;

    @Autowired
    private WordService wordService;

    public void distribute(String roomId) {
        Random rand = new Random();
        Room room = rooms.get(roomId);

        if (room.isStarted()) {
            room.setAnsweredPlayer(0);
            for (Player player : room.getPlayers()) {
                player.getPlayerGameData().setAnswered(false);
            }

            int count = wordService.getWordsCount();
            int correctOption = rand.nextInt(4);
            room.setCorrectOption(correctOption);
            Map<String, Object> practice = new HashMap<>();
            String[] options = new String[4];
            practice.put("messageType", MessageType.Distribute.ordinal());

            //TODO 多个意思 id标号问题 防止重复
            int wordId = rand.nextInt(count) + 1;
            Word correct = wordService.getWordById(wordId);
            room.setWordId(wordId);
            practice.put("word", correct.getWord());
            options[correctOption] = correct.getMeaning1();
            for (int i = 0; i < 4; i++) {
                if (i != correctOption) options[i] = wordService.getWordById(rand.nextInt(count) + 1).getMeaning1();
            }
            practice.put("options", options);
            room.setStartTime(System.currentTimeMillis());
            practice.put("timestamp", System.currentTimeMillis());

            broadcast(roomId, practice);
        }
    }

    public void answer(MessageData messageData, Session session) {
        Room room = rooms.get(messageData.getRoomId());
        Player player = findPlayerBySessionId(messageData.getRoomId(), session.getId());

        if (room.isStarted() && player != null && !player.getPlayerGameData().isAnswered()) {
            messageData.getData().put("messageType", MessageType.Answer.ordinal());
            messageData.getData().put("correctOption", room.getCorrectOption());
            messageData.getData().put("wordId", room.getWordId());
            if ((int) messageData.getData().get("option") == room.getCorrectOption()) {
                int score = (int) (12 - (System.currentTimeMillis() - room.getStartTime()) / 1000);
                player.getPlayerGameData().setScore(player.getPlayerGameData().getScore() + score);
                messageData.getData().put("result", true);
            } else {
                messageData.getData().put("result", false);
            }

            try {
                session.getBasicRemote().sendText(JsonHelper.stringify(messageData.getData()));
                room.setAnsweredPlayer(room.getAnsweredPlayer() + 1);
                player.getPlayerGameData().setAnswered(true);
                if (room.getAnsweredPlayer() >= room.getPlayers().size()) {
                    room.setAnsweredWord(room.getAnsweredWord() + 1);
                    sendPlayerScore(messageData.getRoomId());

                    //TODO 不固定的题目数量
                    if (room.getAnsweredWord() >= room.getWordsCount()) {
                        endGame(messageData.getRoomId());
                    } else {
                        this.distribute(messageData.getRoomId());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPlayerScore(String roomId) {
        List<PlayerGameData> playerGameData = new LinkedList<>();
        for (Player player : rooms.get(roomId).getPlayers()) {
            playerGameData.add(player.getPlayerGameData());
        }

        Map jsonMap = new HashMap();
        jsonMap.put("messageType", MessageType.PlayerScore.ordinal());
        jsonMap.put("playerGameData", playerGameData);
        broadcast(roomId, jsonMap);
    }

    public void endGame(String roomId) {
        Room room = rooms.get(roomId);
        Map jsonMap = new HashMap();
        jsonMap.put("messageType", MessageType.End.ordinal());
        room.setStarted(false);
        room.setAnsweredWord(0);
        for (Player player : room.getPlayers()) {
            player.getPlayerGameData().setScore(0);
            player.getPlayerGameData().setDelta(0);
        }
        this.broadcast(roomId, jsonMap);
    }

    public void startGame(MessageData messageData, Session session) {
        Room room = rooms.get(messageData.getRoomId());
        if (!room.isStarted()) {
            room.setStarted(true);
            distribute(messageData.getRoomId());
        }
    }

    public void playerJoin(String roomId, String openId, Session session) {
        // 房间不存在则创建
        Room room;
        if (!rooms.containsKey(roomId)) {
            room = new Room();
            rooms.put(roomId, room);
        } else {
            room = rooms.get(roomId);
        }
        Player player = new Player(openId, session);
        User user = userService.getUserByOpenId(player.getOpenId());

        PlayerGameData playerGameData = new PlayerGameData(user.getNickname(), user.getIconUrl());
        player.setPlayerGameData(playerGameData);
        room.getPlayers().add(player);

System.out.println(openId + "加入房间:" + roomId);

        // 发送房间内全部玩家的信息
        broadcastRoomPlayers(roomId);
    }

    public void playerLeave(String roomId, Session session) {
        Room room = rooms.get(roomId);
        Player leaver = findPlayerBySessionId(roomId, session.getId());
        room.getPlayers().remove(leaver);
System.out.println(leaver.getOpenId() + "离开房间:" + roomId);
System.out.println("房间:" + roomId + "剩余人数:" + room.getPlayers().size());
        if (room.getPlayers().size() == 0) {
            rooms.remove(roomId);
            roomIdPool.add(roomId);
        } else {
            broadcastRoomPlayers(roomId);
        }
    }

    private void broadcastRoomPlayers(String roomId) {
        Room room = rooms.get(roomId);
        Map jsonMap = new HashMap();
        List<PlayerGameData> playerInfos = new LinkedList<>();
        for (Player player : room.getPlayers()) {
           playerInfos.add(player.getPlayerGameData());
        }
        jsonMap.put("messageType", MessageType.PlayerChange.ordinal());
        jsonMap.put("players", playerInfos);
        broadcast(roomId, jsonMap);
    }

    private void broadcast(String roomId, Object object) {
        try {
            String json = JsonHelper.stringify(object);
            for (Player player : rooms.get(roomId).getPlayers()) {
                player.getSession().getBasicRemote().sendText(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player findPlayerBySessionId(String roomId, String sessionId) {
        Room room = rooms.get(roomId);
        for (Player player : room.getPlayers()) {
            if (player.getSession().getId().equals(sessionId)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public String getAvailableRoomId() {
        Random random = new Random();
        String roomId = roomIdPool.get(random.nextInt(roomIdPool.size()));
        roomIdPool.remove(roomId);
        return roomId;
    }

    @Override
    public Map getRooms() {
        return rooms;
    }
}
