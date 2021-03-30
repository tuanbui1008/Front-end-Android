package com.example.bookingroom.hotel.hotel.service;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.hotel.area.bo.AreaBO;
import com.example.bookingroom.hotel.area.dao.AreaDAO;
import com.example.bookingroom.hotel.hotel.bo.HotelBO;
import com.example.bookingroom.hotel.hotel.dao.HotelDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelDAO hotelDAO;


    public List<HotelBO> fakeDataByNameArea(String name, int idArea) {
        List<HotelBO> lst = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        if (name.equalsIgnoreCase("Hà Nội")) {
            lst.add(modelMapper.map(Constants.HANOI.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.HANOI.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.HANOI.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.HANOI.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.HANOI.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("Đà Nẵng")) {
            lst.add(modelMapper.map(Constants.DaNang.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.DaNang.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.DaNang.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.DaNang.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.DaNang.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("Quảng Ninh")) {
            lst.add(modelMapper.map(Constants.QuangNinh.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.QuangNinh.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.QuangNinh.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.QuangNinh.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.QuangNinh.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("TP.Hồ Chí Minh")) {
            lst.add(modelMapper.map(Constants.TP_HCM.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.TP_HCM.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.TP_HCM.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.TP_HCM.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.TP_HCM.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("Vũng Tàu")) {
            lst.add(modelMapper.map(Constants.VUNG_TAU.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.VUNG_TAU.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.VUNG_TAU.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.VUNG_TAU.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.VUNG_TAU.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("Nha Trang")) {
            lst.add(modelMapper.map(Constants.NhaTrang.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.NhaTrang.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.NhaTrang.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.NhaTrang.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.NhaTrang.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("Cát Bà")) {
            lst.add(modelMapper.map(Constants.CAT_BA.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.CAT_BA.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.CAT_BA.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.CAT_BA.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.CAT_BA.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("Quy Nhơn")) {
            lst.add(modelMapper.map(Constants.QUY_NHON.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.QUY_NHON.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.QUY_NHON.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.QUY_NHON.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.QUY_NHON.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }
        if (name.equalsIgnoreCase("Phú Quốc")) {
            lst.add(modelMapper.map(Constants.PHU_QUOC.Hotel1, HotelBO.class));
            lst.add(modelMapper.map(Constants.PHU_QUOC.Hotel2, HotelBO.class));
            lst.add(modelMapper.map(Constants.PHU_QUOC.Hotel3, HotelBO.class));
            lst.add(modelMapper.map(Constants.PHU_QUOC.Hotel4, HotelBO.class));
            lst.add(modelMapper.map(Constants.PHU_QUOC.Hotel5, HotelBO.class));
            lst.forEach(bo -> {
                bo.setIdArea(idArea);
            });
        }

        return lst;
    }
}
