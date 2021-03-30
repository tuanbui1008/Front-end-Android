package com.example.bookingroom.hotel.category.control;

import com.example.bookingroom.common.Constants;
import com.example.bookingroom.common.Response;
import com.example.bookingroom.hotel.category.bean.CategoryBean;
import com.example.bookingroom.hotel.category.bo.CategoryBO;
import com.example.bookingroom.hotel.category.dao.CategoryDAO;
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
@RequestMapping("api/category")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Transactional
    @GetMapping("/fake-data")
    public @ResponseBody
    Response fakeData(){
        List<CategoryBO> lst = new ArrayList<>();
        lst.add(new CategoryBO("Phòng đơn"));
        lst.add(new CategoryBO("Phòng đôi"));
        lst.add(new CategoryBO("Phòng gia đình"));
        try {
            categoryDAO.saveAll(lst);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.error(Constants.RESPONSE_CODE.ERROR).withData(e.getMessage());
        }
        return Response.success();
    }

    @GetMapping("/get-all")
    public @ResponseBody Response getAll(){
        ModelMapper modelMapper = new ModelMapper();
        List<CategoryBO> lstBO = (List<CategoryBO>) categoryDAO.findAll();
        List<CategoryBean> lstBean = new ArrayList<>();
        lstBO.stream().forEach(bo->{
            CategoryBean bean = modelMapper.map(bo,CategoryBean.class);
            lstBean.add(bean);
        });
        return Response.success().withData(lstBean);
    }
}
