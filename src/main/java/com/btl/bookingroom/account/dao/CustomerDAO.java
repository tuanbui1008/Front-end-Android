package com.btl.bookingroom.account.dao;

import com.btl.bookingroom.account.bo.CustomerBO;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<CustomerBO,Integer> {
}
