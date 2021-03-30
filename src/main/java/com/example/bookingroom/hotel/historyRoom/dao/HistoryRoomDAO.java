package com.example.bookingroom.hotel.historyRoom.dao;

import com.example.bookingroom.hotel.historyRoom.bo.HistoryRoomBO;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRoomDAO extends CrudRepository<HistoryRoomBO,Integer> {
}
