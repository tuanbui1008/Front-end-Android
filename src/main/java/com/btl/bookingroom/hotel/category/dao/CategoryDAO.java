package com.btl.bookingroom.hotel.category.dao;

import com.btl.bookingroom.hotel.category.bo.CategoryBO;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDAO extends CrudRepository<CategoryBO,Integer> {
}
