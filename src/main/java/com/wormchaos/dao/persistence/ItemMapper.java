package com.wormchaos.dao.persistence;

import com.wormchaos.dao.entity.ItemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/8.
 */
public interface ItemMapper {

    List<ItemEntity> findItems(@Param("size") Integer size);

    void createItem(ItemEntity itemEntity);
}
