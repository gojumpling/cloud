package com.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.pojo.Item;
import com.code.mapper.ItemMapper;
import com.code.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-05
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Override
    public Item getItemById(String item_id) {
        LambdaQueryWrapper<Item> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Item::getItemId,item_id);

        Item item = this.getOne(lambdaQueryWrapper);
        return item;
    }

    @Override
    public List<Item> getItemList(String itemSet_id) {
        LambdaQueryWrapper<Item> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Item::getItemsetId,itemSet_id);

        List<Item> itemList = this.list(lambdaQueryWrapper);
        return itemList;
    }

}
