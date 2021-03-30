package com.example.bookingroom.hotel.hotel.control;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.area.bo.AreaBO;
import com.example.bookingroom.hotel.area.dao.AreaDAO;
import com.example.bookingroom.hotel.hotel.bo.HotelBO;
import com.example.bookingroom.hotel.hotel.dao.HotelDAO;
import com.example.bookingroom.hotel.hotel.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/hotel")
public class HotelController {
    private final Logger logger = LoggerFactory.getLogger(HotelController.class);
    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private AreaDAO areaDAO;

    @Transactional
    @GetMapping("/fake-data")
    public @ResponseBody
    Response fakeData() {
        List<AreaBO> lstArea = (List<AreaBO>) areaDAO.findAll();
        List<HotelBO> lst = new ArrayList<>();
        lstArea.forEach(data -> {
            lst.addAll(hotelService.fakeDataByNameArea(data.getName(), data.getId()));
        });
        try {
            hotelDAO.saveAll(lst);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.warning(Constants.RESPONSE_CODE.ERROR, e.getMessage());
        }
        return Response.success();
    }

    @GetMapping("/get-all")
    public @ResponseBody
    Response getAll() {
        return Response.success(Constants.RESPONSE_CODE.SUCCESS).withData(hotelDAO.findAll());
    }
}
