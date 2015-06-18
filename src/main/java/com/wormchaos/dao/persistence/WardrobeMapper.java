package com.wormchaos.dao.persistence;

import com.wormchaos.dao.entity.Cloth;
import com.wormchaos.dao.entity.Wardrobe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/13.
 */
public interface WardrobeMapper {

    /**
     * 查找某用户某列表的所有衣服
     * @param userId
     * @param type
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Cloth> findCloths(@Param("userId") Integer userId, @Param("type")  Integer type, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize );

    /**
     * 查找某个用户衣柜的某件衣服
     * @param userId
     * @param clothId
     * @return
     */
    Wardrobe findWardrobe(@Param("userId") Integer userId, @Param("clothId") Integer clothId);

    void createWardrobe(@Param("userId") Integer userId,@Param("clothId")  Integer clothId);
}
