package com.wormchaos.service;

import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.persistence.ClothMapper;
import com.wormchaos.service.base.ClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/11.
 */
@Service
public class ClothServiceImpl implements ClothService {

    @Autowired
    ClothMapper clothMapper;

    @Override
    public List<Cloth> findCloths(Integer type, Integer pageIndex, Integer pageSize) {
        Integer pageStart = ( pageIndex - 1) * pageSize;
        return clothMapper.findCloths(type, pageStart, pageSize);
    }

    @Override
    public void createCloth(Cloth cloth) {
        clothMapper.createCloth(cloth);
    }
}
