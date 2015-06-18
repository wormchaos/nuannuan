package com.wormchaos.controller;

import com.wormchaos.common.NnUtils;
import com.wormchaos.controller.dto.DecorationDto;
import com.wormchaos.controller.dto.ResultBean;
import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.entity.User;
import com.wormchaos.service.base.ClothService;
import com.wormchaos.service.base.ItemService;
import com.wormchaos.service.base.WardrobeService;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wormchaos on 2015/6/9.
 */
@Controller
@RequestMapping("decoration")
public class DecorationController extends BaseController {

    @Autowired
    ItemService itemService;
    @Autowired

    ClothService clothService;

    @Autowired
    WardrobeService wardrobeService;

    /**
     * 进入衣柜详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("decorationPage")
    public ModelAndView decorationPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("decoration_details");
        return model;
    }

    /**
     * 进入我的衣柜
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("wardrobe")
    public ModelAndView wardrobe(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("wardrobe");
        model.addObject("action", "1");
        return model;
    }

    /**
     * 根据条件获取装饰列表
     * @param request
     * @param response
     * @param brief
     * @param elegance
     * @param lovely
     * @param pure
     * @param cool
     * @param label1
     * @param label2
     * @return
     * @throws IOException
     * @throws BiffException
     */
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
        Integer userId = getUserId(request);
        List<Cloth> clothList = clothService.calculateDecoration(userId, brief, elegance, lovely, pure, cool, null, null);
        List<DecorationDto> itemDtoList = covertFromCloth(clothList);
        return itemDtoList;
    }

    @RequestMapping("ajax/addCloth")
    @ResponseBody
    public ResultBean addCloth(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false, value = "clothId") Integer clothId) throws IOException, BiffException {
        User user = (User) request.getSession().getAttribute(NnUtils.SESSION_USER);
        if (null == user) {
            return null;
        }
        if(null == wardrobeService.findWardrobe(user.getId(), clothId)) {
            wardrobeService.insertClothIntoWardrobe(user.getId(), clothId);
        }
        Map<String, String> map = new HashMap<String, String>();
        ResultBean resultBean = new ResultBean();
        resultBean.setContent("success");
        return resultBean;
    }


}
