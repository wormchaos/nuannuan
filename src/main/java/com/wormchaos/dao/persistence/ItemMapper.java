package com.wormchaos.dao.persistence;

import com.wormchaos.dao.entity.CompBean;
import com.wormchaos.dao.entity.ItemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/8.
 */
public interface ItemMapper {

    List<ItemEntity> findItems(@Param("type") Integer type, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    ItemEntity findItem(@Param("type") Integer type, @Param("num") Integer num);

    void createItem(ItemEntity itemEntity);

    List<CompBean> findNeedItems(@Param("type") Integer type, @Param("param1") String param1, @Param("param2") String param2);
}
