package com.example.bookingroom.hotel.historyRoom.control;

import com.example.bookingroom.hotel.historyRoom.service.HistoryRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/history-room")
public class HistoryRoomController {
    @Autowired
    private HistoryRoomService historyRoomService;
}
