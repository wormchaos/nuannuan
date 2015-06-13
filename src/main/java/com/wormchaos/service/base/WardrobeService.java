package com.wormchaos.service.base;

import com.wormchaos.dao.entity.Cloth;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/13.
 */
public interface WardrobeService {

    List<Cloth> findMyClothes(Integer userId, Integer type, Integer pageIndex, Integer pageSize);

    void insertClothIntoWardrobe(Integer userId, List<Cloth> clothList);
}
