package com.wormchaos;

import com.wormchaos.dao.entity.ItemEntity;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wormchaos on 2015/6/8.
 */
public class Main {

    public static void main(String... args) throws IOException, BiffException {
        readExcel();
    }

    public static void readExcel() throws BiffException, IOException {
        //创建一个list 用来存储读取的内容
        List<ItemEntity> list = new ArrayList();
        Workbook rwb = null;
        Cell cell = null;
        //创建输入流
        InputStream stream = new FileInputStream("d:\\nuannuan.xls");
        //获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);
        //获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(0);
        //行数(表头的目录不需要，从1开始)
        for(int i=1; i<sheet.getRows(); i++){
            //创建一个数组 用来存储每一列的值
            String[] str = new String[sheet.getColumns()];
            ItemEntity item = new ItemEntity();
            if(null == sheet.getCell(0, i).getContents() || "".equals(sheet.getCell(0, i).getContents())){
                break;
            }
            item.setName(sheet.getCell(0, i).getContents());
            item.setNum(Integer.parseInt(sheet.getCell(1, i).getContents()));
            item.setLevel(Integer.parseInt(sheet.getCell(2, i).getContents()));
            item.setHuali(getResult(sheet.getCell(3, i).getContents()));
            item.setJianyue(getResult(sheet.getCell(4, i).getContents()));
            item.setYouya(getResult(sheet.getCell(5, i).getContents()));
            item.setHuopo(getResult(sheet.getCell(6, i).getContents()));
            item.setChengshu(getResult(sheet.getCell(7, i).getContents()));
            item.setKeai(getResult(sheet.getCell(8, i).getContents()));
            item.setXinggan(getResult(sheet.getCell(9, i).getContents()));
            item.setQingchun(getResult(sheet.getCell(10, i).getContents()));
            item.setQingliang(getResult(sheet.getCell(11, i).getContents()));
            item.setBaonuan(getResult(sheet.getCell(12, i).getContents()));
            item.setLabel1(sheet.getCell(13, i).getContents());
            item.setLabel2(sheet.getCell(14, i).getContents());
            item.setGetfrom(sheet.getCell(15, i).getContents());
            //把刚获取的列存入list
            list.add(item);
        }
        System.out.print("1");
    }
    private static int getResult(String i) {
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
