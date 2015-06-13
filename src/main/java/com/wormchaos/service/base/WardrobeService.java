package com.wormchaos.service.base;

import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.entity.Wardrobe;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/13.
 */
public interface WardrobeService {

    List<Cloth> findMyClothes(Integer userId, Integer type, Integer pageIndex, Integer pageSize);

    Wardrobe findWardrobe(Integer userId,Integer clothId);

    void insertClothListIntoWardrobe(Integer userId, List<Cloth> clothList);

    void insertClothIntoWardrobe(Integer userId, Integer clothId);
}
