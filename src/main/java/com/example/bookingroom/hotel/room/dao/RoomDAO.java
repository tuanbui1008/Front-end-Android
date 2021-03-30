package com.example.bookingroom.hotel.room.dao;

import com.example.bookingroom.hotel.room.bo.RoomBO;
import org.springframework.data.repository.CrudRepository;

public interface RoomDAO extends CrudRepository<RoomBO,Integer> {
}
