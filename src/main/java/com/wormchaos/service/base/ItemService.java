package com.wormchaos.service.base;

import com.wormchaos.dao.entity.ItemEntity;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/8.
 */
public interface ItemService {

    List<ItemEntity> findItems(Integer type, Integer pageIndex, Integer pageSize);

    void createItem(ItemEntity itemEntity);

    List<ItemEntity> calculateDecoration(String param1, String param2);

}
