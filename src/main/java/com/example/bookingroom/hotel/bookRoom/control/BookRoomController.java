package com.example.bookingroom.hotel.bookRoom.control;

import com.example.bookingroom.hotel.bookRoom.service.BookRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/book-room")
public class BookRoomController {
    @Autowired
    private BookRoomService bookRoomService;
}
