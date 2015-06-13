package com.wormchaos.service;

import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.persistence.WardrobeMapper;
import com.wormchaos.service.base.WardrobeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/13.
 */
@Service
public class WardrobeServiceImpl implements WardrobeService {

    @Autowired
    WardrobeMapper wardrobeMapper;

    @Override
    public List<Cloth> findMyClothes(Integer userId, Integer type, Integer pageIndex, Integer pageSize) {
        Integer pageStart = ( pageIndex - 1) * pageSize;
        return wardrobeMapper.findCloths(userId, type, pageStart, pageSize);
    }

    @Override
    public void insertClothIntoWardrobe(Integer userId, List<Cloth> clothList) {
        if(CollectionUtils.isEmpty(clothList)) {
            return;
        }
        for(Cloth cloth : clothList) {
            wardrobeMapper.createWardrobe(userId, cloth.getId());
        }
    }
}
