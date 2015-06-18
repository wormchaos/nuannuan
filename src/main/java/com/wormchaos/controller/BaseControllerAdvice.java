package com.wormchaos.controller;

import com.wormchaos.common.Exception.ErrorCodeConstants;
import com.wormchaos.common.Exception.ErrorCodeException;
import com.wormchaos.controller.dto.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wormchaos on 2015/6/18.
 */
@ControllerAdvice
public class BaseControllerAdvice {

    protected Logger logger = LoggerFactory.getLogger(BaseControllerAdvice.class);

    @ExceptionHandler(value = ErrorCodeException.class)
    @ResponseBody
    public ResultBean handleException(ErrorCodeException ex, NativeWebRequest nativeWebRequest) {
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        ResultBean bean = new ResultBean(ex.getError(), ErrorCodeConstants.errorMapping.get(ex.getError()));
        return bean;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean handleIOException(Exception e,NativeWebRequest nativeWebRequest) {
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        ResultBean bean = new ResultBean(1001);
        return bean;
    }

}
