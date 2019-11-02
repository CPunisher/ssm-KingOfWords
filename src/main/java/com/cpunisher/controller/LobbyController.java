package com.cpunisher.controller;

import com.cpunisher.entity.Room;
import com.cpunisher.entity.LobbyRoom;
import com.cpunisher.model.User;
import com.cpunisher.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class LobbyController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("createRoom")
    public ModelAndView createRoom(ModelAndView mv, HttpSession session) {
        mv.addObject("roomId", roomService.getAvailableRoomId());
        mv.setViewName("game");
        return mv;
    }

    @RequestMapping("game")
    public ModelAndView game(ModelAndView mv, HttpSession session) {
        mv.setViewName("game");
        return mv;
    }

    @RequestMapping("lobby")
    public ModelAndView lobby(ModelAndView mv, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mv.setViewName("redirect:/login");
            return mv;
        }

        Map<String, Room> rooms = roomService.getRooms();
        List<LobbyRoom> lists = new LinkedList<>();
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            lists.add(new LobbyRoom(entry.getKey(), entry.getValue().getPlayers().size(), entry.getValue().isStarted()));
        }
        mv.addObject("rooms", lists);
        mv.setViewName("lobby");
        return mv;
    }
}
