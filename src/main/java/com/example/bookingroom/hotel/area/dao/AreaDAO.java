package com.example.bookingroom.hotel.area.dao;

import com.example.bookingroom.hotel.area.bo.AreaBO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaDAO extends CrudRepository<AreaBO,Integer> {
    AreaBO getAllByName(String name);
}
