package com.wormchaos.service.base;

import com.wormchaos.dao.entity.Cloth;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/11.
 */
public interface ClothService {

    List<Cloth> findCloths(Integer type, Integer pageIndex, Integer pageSize);

    void createCloth(Cloth cloth);

    /**
     * 计算对应的匹配数据
     * @param briefFlg 对应属性的系数 0为无所谓，正数为需要的属性，负数为不需要的属性
     * @param eleganceFlg
     * @param lovelyFlg
     * @param pureFlg
     * @param coolFlg
     * @param label1 需要的标签
     * @param label2
     * @return
     */
    List<Cloth> calculateDecoration(int briefFlg, int eleganceFlg, int lovelyFlg, int pureFlg, int coolFlg, String label1, String label2);

}
