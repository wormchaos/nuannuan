package com.wormchaos.controller;

import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.service.base.ClothService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ClothController {

    @Autowired
    ClothService clothService;

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
                //创建一个数组 用来存储每一列的值
                String[] str = new String[sheet.getColumns()];
                Cloth cloth = new Cloth();
                if (null == sheet.getCell(0, i).getContents() || "".equals(sheet.getCell(0, i).getContents())) {
                    break;
                }
                cloth.setType(k + 1);
                boolean errorFlg = false;
                cloth.setName(sheet.getCell(0, i).getContents());
                if(null != sheet.getCell(1, i).getContents() && !"".equals(sheet.getCell(1, i).getContents())){
                    cloth.setNum(Integer.parseInt(sheet.getCell(1, i).getContents()));
                }
                if(null != sheet.getCell(2, i).getContents() && !"".equals(sheet.getCell(2, i).getContents())) {
                    cloth.setLevel(Integer.parseInt(sheet.getCell(2, i).getContents()));
                }
                Integer huali = getResult(sheet.getCell(3, i).getContents());
                Integer jianyue = getResult(sheet.getCell(4, i).getContents());

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

                Integer youya = getResult(sheet.getCell(5, i).getContents());
                Integer huopo = getResult(sheet.getCell(6, i).getContents());

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

                Integer chengshu = getResult(sheet.getCell(7, i).getContents());
                Integer keai = getResult(sheet.getCell(8, i).getContents());

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

                Integer xinggan = getResult(sheet.getCell(9, i).getContents());
                Integer qingchun = getResult(sheet.getCell(10, i).getContents());

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

                Integer qingliang = getResult(sheet.getCell(11, i).getContents());
                Integer baonuan = getResult(sheet.getCell(12, i).getContents());

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
                cloth.setLabel1(sheet.getCell(13, i).getContents());
                cloth.setLabel2(sheet.getCell(14, i).getContents());
                cloth.setCloth_from(sheet.getCell(15, i).getContents());
                //把刚获取的列存入list
                if(errorFlg) {
                    errorList.add(cloth);
                } else {
                    clothService.createCloth(cloth);
                }
            }
        }
        return errorList;
    }


    private int getResult(String i) {
        if(null == i || "".equals(i)) {
            return 0;
        } else {
            if("D".equals(i)){
                return 1;
            } else if("C".equals(i)){
                return 2;
            } else if("B".equals(i)){
                return 3;
            } else if("A".equals(i)){
                return 4;
            } else if("S".equals(i)){
                return 5;
            } else if("SS".equals(i)){
                return 6;
            } else {
                return 0;
            }
        }
    }
}
