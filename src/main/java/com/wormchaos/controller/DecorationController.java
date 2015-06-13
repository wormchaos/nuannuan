package com.wormchaos.controller;

import com.wormchaos.common.DecorationConstant;
import com.wormchaos.common.NnUtils;
import com.wormchaos.controller.dto.DecorationDto;
import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.entity.ItemEntity;
import com.wormchaos.dao.entity.User;
import com.wormchaos.service.base.ClothService;
import com.wormchaos.service.base.ItemService;
import com.wormchaos.service.base.WardrobeService;
import jxl.read.biff.BiffException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wormchaos on 2015/6/9.
 */
@Controller
@RequestMapping("decoration")
public class DecorationController {

    @Autowired
    ItemService itemService;
    @Autowired

    ClothService clothService;

    @Autowired
    WardrobeService wardrobeService;

    @RequestMapping("main")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = false, value = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false, value = "type", defaultValue = "1") Integer type ) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("decoration");
        model.addObject("action", "0");
        return model;
    }

    @RequestMapping("decorationPage")
    public ModelAndView decorationPage(HttpServletRequest request, HttpServletResponse response) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("decoration_details");
        return model;
    }

    @RequestMapping("wardrobe")
    public ModelAndView wardrobe(HttpServletRequest request, HttpServletResponse response) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("wardrobe");
        model.addObject("action", "1");
        return model;
    }

    @RequestMapping("ajax/getClothList")
    @ResponseBody
    public List<DecorationDto> getClothList(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = false, value = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false, value = "type", defaultValue = "1") Integer type,
                             @RequestParam(required = false, value = "action") Integer action ) throws IOException, BiffException {
        User user = (User) request.getSession().getAttribute(NnUtils.SESSION_USER);
        if (null == user) {
            return null;
        }
        List<DecorationDto> decorationDtoList = null;
        if(null == action || 0 ==action) {
            List<Cloth> clothList = clothService.findCloths(type, pageIndex, pageSize);
            decorationDtoList = covertFromCloth(clothList);
        } else if(1 == action) {
            List<Cloth> clothList = wardrobeService.findMyClothes(user.getId(), type, pageIndex, pageSize);
            decorationDtoList = covertFromCloth(clothList);
        } else if(2 == action) {
            List<Cloth> clothList = clothService.findUnSelectCloths(user.getId(), type, pageIndex, pageSize);
            decorationDtoList = covertFromCloth(clothList);
        }
        return decorationDtoList;
    }

    @RequestMapping("ajax/decorate")
    @ResponseBody
    public List<DecorationDto> decorate(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false, value = "brief") Integer brief,
                                        @RequestParam(required = false, value = "elegance") Integer elegance,
                                        @RequestParam(required = false, value = "lovely") Integer lovely,
                                        @RequestParam(required = false, value = "pure") Integer pure,
                                        @RequestParam(required = false, value = "cool") Integer cool,
                                        @RequestParam(required = false, value = "label1") String label1,
                                        @RequestParam(required = false, value = "label2") String label2 ) throws IOException, BiffException {
        List<Cloth> clothList = clothService.calculateDecoration(brief, elegance, lovely, pure, cool, null, null);
        List<DecorationDto> itemDtoList = covertFromCloth(clothList);
        return itemDtoList;
    }

    @RequestMapping("ajax/addCloth")
    @ResponseBody
    public Map<String, String> addCloth(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false, value = "clothId") Integer clothId) throws IOException, BiffException {
        User user = (User) request.getSession().getAttribute(NnUtils.SESSION_USER);
        if (null == user) {
            return null;
        }
        if(null == wardrobeService.findWardrobe(user.getId(), clothId)) {
            wardrobeService.insertClothIntoWardrobe(user.getId(), clothId);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "1");
        return map;
    }

    private List<DecorationDto> covertFromItem(List<ItemEntity> itemEntityList) {
        List<DecorationDto> list = new ArrayList<DecorationDto>();
        if (!CollectionUtils.isEmpty(list)) {
            for (ItemEntity item : itemEntityList) {
                DecorationDto dto = new DecorationDto();
                BeanUtils.copyProperties(item, dto);
                dto.setJianyue(DecorationConstant.levelMapping.get(item.getJianyue()));
                dto.setHuali(DecorationConstant.levelMapping.get(item.getHuali()));
                dto.setYouya(DecorationConstant.levelMapping.get(item.getYouya()));
                dto.setHuopo(DecorationConstant.levelMapping.get(item.getHuopo()));
                dto.setChengshu(DecorationConstant.levelMapping.get(item.getChengshu()));
                dto.setKeai(DecorationConstant.levelMapping.get(item.getKeai()));
                dto.setXinggan(DecorationConstant.levelMapping.get(item.getXinggan()));
                dto.setQingchun(DecorationConstant.levelMapping.get(item.getQingchun()));
                dto.setQingliang(DecorationConstant.levelMapping.get(item.getQingliang()));
                dto.setBaonuan(DecorationConstant.levelMapping.get(item.getBaonuan()));
                dto.setType(DecorationConstant.typeMapping.get(item.getType()));
                list.add(dto);
            }
        }
        return list;
    }

    private List<DecorationDto> covertFromCloth(List<Cloth> clothList) {
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
