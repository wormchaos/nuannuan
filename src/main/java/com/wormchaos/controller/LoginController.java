package com.wormchaos.controller;

import com.wormchaos.common.MD5Util;
import com.wormchaos.common.NnUtils;
import com.wormchaos.dao.entity.User;
import com.wormchaos.service.base.UserService;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wormchaos on 2015/6/13.
 */
@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false, value = "username") String username,
                                   @RequestParam(required = false, value = "password") String password,
                                   @RequestParam(required = false, value = "referer") String referer) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("login");
        if(StringUtils.isEmpty(username)) {
            return model;
        }
        User user = userService.findUser(username, MD5Util.MD5(password));
        if(null == user) {
            model.addObject("error", "用户名密码错误！");
            return model;
        } else {
            request.getSession().setAttribute(NnUtils.SESSION_USER, user);
            if(StringUtils.isEmpty(referer)) {
                response.sendRedirect("/decoration/decorationPage");
            } else {
                response.sendRedirect(referer);
            }
        }
        return model;
    }

    @RequestMapping("register")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false, value = "username") String username,
                                   @RequestParam(required = false, value = "password") String password,
                                   @RequestParam(required = false, value = "passwordConfirm") String passwordConfirm) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("login");
        if(null == password || !password.equals(passwordConfirm)) {
            model.addObject("error", "密码输入不一致！");
            return model;
        }
        if(null != userService.findUserByName(username)) {
            model.addObject("error", "用户名已存在！");
            return model;
        }
        password = MD5Util.MD5(password);
        userService.createUser(username, password);
        return model;
    }
}
