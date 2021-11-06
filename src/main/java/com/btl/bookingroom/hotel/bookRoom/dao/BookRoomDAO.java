package com.btl.bookingroom.hotel.bookRoom.dao;

import com.btl.bookingroom.hotel.bookRoom.bo.BookRoomBO;
import org.springframework.data.repository.CrudRepository;

public interface BookRoomDAO extends CrudRepository<BookRoomBO,Integer> {
}
