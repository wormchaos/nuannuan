package com.wormchaos.controller;

import com.wormchaos.dao.entity.ItemEntity;
import com.wormchaos.service.base.ItemService;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wormchaos on 2015/6/9.
 */
@Controller
public class DecorationController {

    @Autowired
    ItemService itemService;

    @RequestMapping("main")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = false, value = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize ) throws IOException, BiffException {
        List<ItemEntity> itemList = itemService.findItems(pageIndex, pageSize);
        ModelAndView model = new ModelAndView("decoration");
        model.addObject("itemList",itemList);
        return model;
    }
}
