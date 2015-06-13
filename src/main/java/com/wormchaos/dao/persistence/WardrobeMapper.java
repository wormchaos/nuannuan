package com.wormchaos.dao.persistence;

import com.wormchaos.dao.entity.Wardrobe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/13.
 */
public interface WardrobeMapper {

    List<Wardrobe> findWardrobes(Integer userId);

    Wardrobe createWardrobe(@Param("username") Integer userId, Integer clothId);
}
