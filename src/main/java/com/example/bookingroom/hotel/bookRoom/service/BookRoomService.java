package com.example.bookingroom.hotel.bookRoom.service;

import com.example.bookingroom.hotel.bookRoom.dao.BookRoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRoomService {
    @Autowired
    private BookRoomDAO bookRoomDAO;
}
