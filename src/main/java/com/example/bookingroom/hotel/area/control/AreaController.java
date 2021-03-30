package com.example.bookingroom.hotel.area.control;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.area.bean.AreaBean;
import com.example.bookingroom.hotel.area.bo.AreaBO;
import com.example.bookingroom.hotel.area.dao.AreaDAO;
import com.example.bookingroom.hotel.category.bean.CategoryBean;
import com.example.bookingroom.hotel.category.bo.CategoryBO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/area")
public class AreaController {
    private final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaDAO areaDAO;

    @Transactional
    @GetMapping("/fake-data")
    public @ResponseBody
    Response fakeData(){
        List<AreaBO> lst = new ArrayList<>();
        lst.add(new AreaBO("Hà Nội"));
        lst.add(new AreaBO("Đà Nẵng"));
        lst.add(new AreaBO("Quảng Ninh"));
        lst.add(new AreaBO("TP.Hồ Chí Minh"));
        lst.add(new AreaBO("Vũng Tàu"));
        lst.add(new AreaBO("Nha Trang"));
        lst.add(new AreaBO("Cát Bà"));
        lst.add(new AreaBO("Quy Nhơn"));
        lst.add(new AreaBO("Phú Quốc"));
        try {
            areaDAO.saveAll(lst);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error(Constants.RESPONSE_CODE.ERROR);
        }
        return Response.success();
    }
    @GetMapping("/get-all")
    public @ResponseBody Response getAll(){
        ModelMapper modelMapper = new ModelMapper();
        List<AreaBO> lstBO = (List<AreaBO>) areaDAO.findAll();
        List<AreaBean> lstBean = new ArrayList<>();
        lstBO.stream().forEach(bo->{
            AreaBean bean = modelMapper.map(bo,AreaBean.class);
            lstBean.add(bean);
        });
        return Response.success().withData(lstBean);
    }
}
