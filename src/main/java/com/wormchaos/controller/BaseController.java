package com.wormchaos.controller;

import com.wormchaos.common.DecorationConstant;
import com.wormchaos.common.Exception.ErrorCodeException;
import com.wormchaos.common.NnUtils;
import com.wormchaos.controller.dto.DecorationDto;
import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wormchaos on 2015/6/18.
 */
@Controller
public class BaseController {

    /**
     * 获取用户
     * @param request
     * @return
     */
    protected User getUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(NnUtils.SESSION_USER);
        if(null == user ){
            throw new ErrorCodeException(2000);
        }
        return user;
    }

    /**
     * 获取用户
     * @param request
     * @return
     */
    protected User getUserWithOutException(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(NnUtils.SESSION_USER);
    }

    protected Integer getUserId(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(NnUtils.SESSION_USER);
        Integer userId;
        if (null == user) {
            userId = null;
        } else {
            userId = user.getId();
        }
        return userId;
    }



    /**
     * 转化成前端
     * @param clothList
     * @return
     */
    protected List<DecorationDto> covertFromCloth(List<Cloth> clothList) {
        List<DecorationDto> list = new ArrayList<DecorationDto>();
        if (!CollectionUtils.isEmpty(clothList)) {
            for (Cloth cloth : clothList) {
                DecorationDto dto = new DecorationDto();
                BeanUtils.copyProperties(cloth, dto);
                if (cloth.getBrief() > 0) {
                    dto.setJianyue(DecorationConstant.levelMapping.get(cloth.getBrief()));
                } else {
                    dto.setHuali(DecorationConstant.levelMapping.get(-cloth.getBrief()));
                }
                if (cloth.getElegance() > 0) {
                    dto.setYouya(DecorationConstant.levelMapping.get(cloth.getElegance()));
                } else {
                    dto.setHuopo(DecorationConstant.levelMapping.get(-cloth.getElegance()));
                }
                if (cloth.getLovely() > 0) {
                    dto.setKeai(DecorationConstant.levelMapping.get(cloth.getLovely()));
                } else {
                    dto.setChengshu(DecorationConstant.levelMapping.get(-cloth.getLovely()));
                }
                if (cloth.getPure() > 0) {
                    dto.setQingchun(DecorationConstant.levelMapping.get(cloth.getPure()));
                } else {
                    dto.setXinggan(DecorationConstant.levelMapping.get(-cloth.getPure()));
                }
                if (cloth.getCool() > 0) {
                    dto.setQingliang(DecorationConstant.levelMapping.get(cloth.getCool()));
                } else {
                    dto.setBaonuan(DecorationConstant.levelMapping.get(-cloth.getCool()));
                }
                dto.setType(DecorationConstant.typeMapping.get(cloth.getType()));
                list.add(dto);
            }
        }
        return list;
    }
}
