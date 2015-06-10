package com.wormchaos.controller;

import com.wormchaos.dao.entity.ItemEntity;
import com.wormchaos.service.base.ItemService;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wormchaos on 2015/6/9.
 */
@Controller
@RequestMapping("decoration")
public class DecorationController {

    @Autowired
    ItemService itemService;

    @RequestMapping("main")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = false, value = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false, value = "type", defaultValue = "1") Integer type ) throws IOException, BiffException {
        List<ItemEntity> itemList = itemService.findItems(type, pageIndex, pageSize);
        ModelAndView model = new ModelAndView("decoration");
        model.addObject("itemList",itemList);
        return model;
    }

    @RequestMapping("decorationPage")
    public ModelAndView decorationPage(HttpServletRequest request, HttpServletResponse response) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("decoration_details");
        return model;
    }

    @RequestMapping("getItemList")
    @ResponseBody
    public List<ItemEntity> getItemList(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = false, value = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false, value = "type", defaultValue = "1") Integer type ) throws IOException, BiffException {
        List<ItemEntity> itemList = itemService.findItems(type, pageIndex, pageSize);
        return itemList;
    }

    @RequestMapping("decorate")
    @ResponseBody
    public List<ItemEntity> decorate(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false, value = "param1") String param1,
                                        @RequestParam(required = false, value = "param2") String param2,
                                        @RequestParam(required = false, value = "label1") String label1,
                                        @RequestParam(required = false, value = "label2") String label2 ) throws IOException, BiffException {
        // TODO exception
        if(StringUtils.isEmpty(param1) || StringUtils.isEmpty(param2) ){
            //
        }
        List<ItemEntity> itemEntityList = itemService.calculateDecoration( param1, param2);
        return itemEntityList;
    }

}
