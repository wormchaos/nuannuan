package com.wormchaos.service;

import com.wormchaos.dao.entity.ItemEntity;
import com.wormchaos.dao.persistence.ItemMapper;
import com.wormchaos.service.base.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wormchaos on 2015/6/8.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public List<ItemEntity> findItems(Integer size) {
        return itemMapper.findItems(size);
    }

    @Override
    public void createItem(ItemEntity itemEntity) {
        itemMapper.createItem(itemEntity);
    }
}
