package com.wormchaos.controller;

import com.wormchaos.common.DecorationConstant;
import com.wormchaos.common.NnUtils;
import com.wormchaos.controller.dto.DecorationDto;
import com.wormchaos.controller.dto.ResultBean;
import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.entity.ItemEntity;
import com.wormchaos.dao.entity.User;
import com.wormchaos.service.base.ClothService;
import com.wormchaos.service.base.WardrobeService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wormchaos on 2015/6/11.
 */
@Controller
@RequestMapping("cloth")
public class ClothController extends BaseController {

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
        User user = getUserWithOutException(request);
        if(null != user) {
            model.addObject("username", user.getUsername());
        }
        model.addObject("action", "0");
        return model;
    }

    /**
     * 上传主页
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws BiffException
     */
    @RequestMapping("uploadPage")
    public ModelAndView uploadPage(HttpServletRequest request, HttpServletResponse response) throws IOException, BiffException {
        ModelAndView model = new ModelAndView("upload_page");
        return model;
    }

    @RequestMapping("ajax/getClothList")
    @ResponseBody
    public ResultBean getClothList(HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam(required = false, value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                            @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize,
                                            @RequestParam(required = false, value = "type", defaultValue = "1") Integer type,
                                            @RequestParam(required = false, value = "action") Integer action ) throws IOException, BiffException {
        User user = null;
        if(action > 0) {
            user = getUser(request);
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
        ResultBean resultBean = new ResultBean();
        resultBean.setContent(decorationDtoList);
        return resultBean;
    }

    /**
     * 上传数据接口
     * @param request
     * @param response
     * @param file
     * @return
     * @throws IOException
     * @throws BiffException
     */
    @RequestMapping(value = "ajax/uploadData", method = RequestMethod.POST)
    @ResponseBody
    public List<Cloth> uploadData(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("xlsUpload") MultipartFile file) throws IOException, BiffException {
        User user = (User) request.getSession().getAttribute(NnUtils.SESSION_USER);
        if (null == user) {
            return null;
        }
        Integer userId = user.getId();
        Workbook rwb = null;
        Cell cell = null;
        InputStream inputStream = file.getInputStream();
        //获取Excel文件对象
        rwb = Workbook.getWorkbook(inputStream);
        List<Cloth> errorList = new ArrayList<Cloth>();
        for(int k = 0; k < 8; k++) {
            //获取文件的指定工作表 默认的第一个
            Sheet sheet = rwb.getSheet(k);
            //行数(表头的目录不需要，从1开始)
            for (int i = 1; i < sheet.getRows(); i++) {
                int j = 0;
                String name = sheet.getCell(j, i).getContents();
                if (null == name || "".equals(name)) {
                    break;
                }
                String num = sheet.getCell(j + 1, i).getContents();
                if (null == num || "".equals(num)) {
                    continue;
                }
                // 判断如果存在就不执行
                Cloth searchCloth = clothService.findClothsByNameNum(name, Integer.parseInt(num), k+1);
                if(null != searchCloth) {
                    if(!StringUtils.isEmpty(sheet.getCell(j + 16, i).getContents())){
                        wardrobeService.insertClothIntoWardrobe(userId, searchCloth.getId());
                    }
                    continue;
                }

                Cloth cloth = new Cloth();
                cloth.setName(name);
                cloth.setType(k + 1);
                if(null != num && !"".equals(num)){
                    cloth.setNum(Integer.parseInt(num));
                }
                boolean errorFlg = false;

                if(null != sheet.getCell(j + 2, i).getContents() && !"".equals(sheet.getCell(2, i).getContents())) {
                    cloth.setLevel(Integer.parseInt(sheet.getCell(j + 2, i).getContents()));
                }
                // TODO 装饰
                if(k == 7) {
                    j++;
                }
                Integer huali = getResult(sheet.getCell(j + 3, i).getContents());
                Integer jianyue = getResult(sheet.getCell(j + 4, i).getContents());

                if((huali == 0 && jianyue > 0) || (huali > 0 && jianyue == 0)) {
                    if(jianyue > 0) {
                        cloth.setBrief( jianyue);
                    }
                    if(huali > 0) {
                        cloth.setBrief( -huali);
                    }
                } else{
                    errorFlg = true;
                }

                Integer youya = getResult(sheet.getCell(j + 5, i).getContents());
                Integer huopo = getResult(sheet.getCell(j + 6, i).getContents());

                if((youya == 0 && huopo > 0) || (youya > 0 && huopo == 0)) {
                    if(youya > 0) {
                        cloth.setElegance( youya);
                    }
                    if(huopo > 0) {
                        cloth.setElegance( -huopo);
                    }
                } else{
                    errorFlg = true;
                }

                Integer chengshu = getResult(sheet.getCell(j + 7, i).getContents());
                Integer keai = getResult(sheet.getCell(j + 8, i).getContents());
                if((keai == 0 && chengshu > 0) || (keai > 0 && chengshu == 0)) {
                    if(keai > 0) {
                        cloth.setLovely( keai);
                    }
                    if(chengshu > 0) {
                        cloth.setLovely( -chengshu);
                    }
                } else{
                    errorFlg = true;
                }

                Integer xinggan = getResult(sheet.getCell(j + 9, i).getContents());
                Integer qingchun = getResult(sheet.getCell(j + 10, i).getContents());

                if((xinggan == 0 && qingchun > 0) || (xinggan > 0 && qingchun == 0)) {
                    if(qingchun > 0) {
                        cloth.setPure( qingchun);
                    }
                    if(xinggan > 0) {
                        cloth.setPure( -xinggan);
                    }
                } else{
                    errorFlg = true;
                }

                Integer qingliang = getResult(sheet.getCell(j + 11, i).getContents());
                Integer baonuan = getResult(sheet.getCell(j + 12, i).getContents());

                if((qingliang == 0 && baonuan > 0) || (qingliang > 0 && baonuan == 0)) {
                    if(qingliang > 0) {
                        cloth.setCool( qingliang);
                    }
                    if(baonuan > 0) {
                        cloth.setCool( -baonuan);
                    }
                } else{
                    errorFlg = true;
                }
                cloth.setLabel1(sheet.getCell(j + 13, i).getContents());
                cloth.setLabel2(sheet.getCell(j + 14, i).getContents());
                cloth.setCloth_from(sheet.getCell(j + 15, i).getContents());
                //把刚获取的列存入list
                if(errorFlg) {
                    errorList.add(cloth);
                } else {
                    Integer cloId = clothService.createCloth(cloth);
                    // 如果已有，加入
                    if(!StringUtils.isEmpty(sheet.getCell(j + 16, i).getContents())){
                        wardrobeService.insertClothIntoWardrobe(userId, cloId);
                    }
                }
            }
        }
        return errorList;
    }

    private Integer getResult(String contents) {
        return DecorationConstant.levelToValMapping.get(contents);
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
}
