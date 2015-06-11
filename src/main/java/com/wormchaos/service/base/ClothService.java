package com.wormchaos.service.base;

import com.wormchaos.dao.entity.Cloth;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/11.
 */
public interface ClothService {

    List<Cloth> findCloths(Integer type, Integer pageIndex, Integer pageSize);

    void createCloth(Cloth cloth);

}
