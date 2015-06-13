package com.wormchaos.controller;

import jxl.read.biff.BiffException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wormchaos on 2015/6/13.
 */
@Controller
public class HomepageController {

    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("index");
        return model;
    }
}
