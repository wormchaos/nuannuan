package com.wormchaos.service;

import com.wormchaos.dao.entity.CompBean;
import com.wormchaos.dao.entity.ItemEntity;
import com.wormchaos.dao.persistence.ItemMapper;
import com.wormchaos.service.base.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wormchaos on 2015/6/8.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public List<ItemEntity> findItems(Integer type, Integer pageIndex, Integer pageSize) {
        Integer pageStart = ( pageIndex - 1) * pageSize;
        return itemMapper.findItems(type, pageStart, pageSize);
    }

    @Override
    public void createItem(ItemEntity itemEntity) {
        itemMapper.createItem(itemEntity);
    }

    @Override
    public List<ItemEntity> calculateDecoration(String param1, String param2) {
        List<ItemEntity> list = new ArrayList<ItemEntity>();
        for(int type=1; type < 9; type++) {
            List<CompBean> itemList = itemMapper.findNeedItems(type, param1, param2);
            if(null == itemList || itemList.size() == 0) {
                continue;
            }
            int max = 0;
            int num = -1;
            for (CompBean compBean : itemList) {
                if (max < compBean.getGood1() + compBean.getGood2() + compBean.getLevel()) {
                    max = compBean.getGood1() + compBean.getGood2() + compBean.getLevel();
                    num = compBean.getNum();
                }
            }
            if (num  >= 0) {
                ItemEntity itemEntity = itemMapper.findItem(type, num);
                list.add(itemEntity);
            }
        }
        return list;
    }
}
