package com.example.bookingroom.hotel.room.service;

import com.example.bookingroom.hotel.room.dao.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomDAO roomDAO;
}
