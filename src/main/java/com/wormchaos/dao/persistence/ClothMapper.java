package com.wormchaos.dao.persistence;

import com.wormchaos.dao.entity.Cloth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/11.
 */
public interface ClothMapper {

    List<Cloth> findCloths(@Param("type") Integer type, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    List<Cloth> findUnSelectCloths(@Param("userId") Integer userId, @Param("type") Integer type, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    Integer createCloth(Cloth cloth);

    Cloth findClothsByNameNum(@Param("name") String name, @Param("num") Integer num, @Param("type") Integer type);
}
