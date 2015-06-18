package com.wormchaos.service;

import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.persistence.ClothMapper;
import com.wormchaos.dao.persistence.WardrobeMapper;
import com.wormchaos.service.base.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wormchaos on 2015/6/11.
 */
@Service
public class ClothServiceImpl implements ClothService {

    @Autowired
    ClothMapper clothMapper;

    @Autowired
    WardrobeMapper wardrobeMapper;

    @Override
    public List<Cloth> findCloths(Integer type, Integer pageIndex, Integer pageSize) {
        Integer pageStart = ( pageIndex - 1) * pageSize;
        return clothMapper.findCloths(type, pageStart, pageSize);
    }

    @Override
    public List<Cloth> findUnSelectCloths(Integer userId, Integer type, Integer pageIndex, Integer pageSize) {
        Integer pageStart = ( pageIndex - 1) * pageSize;
        return clothMapper.findUnSelectCloths(userId, type, pageStart, pageSize);
    }

    @Override
    public Integer createCloth(Cloth cloth) {
        return clothMapper.createCloth(cloth);
    }

    @Override
    public List<Cloth> calculateDecoration(Integer userId, int briefFlg, int eleganceFlg, int lovelyFlg, int pureFlg, int coolFlg,
                                           String label1, String label2) {
        List<Cloth> list = new ArrayList<Cloth>();
        for(int type = 1; type < 10; type++) {
            List<Cloth> clothList;
            // 如果不传userId，则默认所有衣服
            if(null == userId) {
                clothList = clothMapper.findCloths(type,0,Integer.MAX_VALUE);
            } else {
                clothList = wardrobeMapper.findCloths(userId, type, 0, Integer.MAX_VALUE);
            }
            if(null == clothList || clothList.size() == 0) {
                continue;
            }
            double max = 0;
            Cloth clothSel = null;
            for (Cloth cloth : clothList) {
                double brief = rate(cloth.getBrief() * briefFlg);
                double elegance = rate(cloth.getElegance() * eleganceFlg);
                double lovely = rate(cloth.getLovely() * lovelyFlg);
                double pure = rate(cloth.getPure() * pureFlg);
                double cool = rate(cloth.getCool() * coolFlg);
                if( cloth.getNum() > 0 ) {
                    double result = getLevelCount(cloth.getLevel()) * (brief + elegance + lovely + pure + cool);
                    if (result > max) {
                        max = brief + elegance + lovely + pure + cool;
                        clothSel = cloth;
                    }
                }
            }
            if (null != clothSel) {
                list.add(clothSel);
            }
        }
        return list;
    }

    private double rate(int i) {
        if(i < 0 ){
            return 1.5 * i;
        } else {
            return i;
        }
    }

    @Override
    public Cloth findClothsByNameNum(String name, Integer num, Integer type) {
        return clothMapper.findClothsByNameNum(name, num, type);
    }

    private Double getLevelCount(int level) {
//        return 1 + level * 0.1;
        return 1d;
    }
}
