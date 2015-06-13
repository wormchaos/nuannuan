package com.wormchaos.dao.persistence;

import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.entity.Wardrobe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/13.
 */
public interface WardrobeMapper {

    List<Cloth> findCloths(@Param("userId") Integer userId, @Param("type")  Integer type, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize );

    Wardrobe findWardrobe(@Param("userId") Integer userId, @Param("clothId") Integer clothId);

    void createWardrobe(@Param("userId") Integer userId,@Param("clothId")  Integer clothId);
}
