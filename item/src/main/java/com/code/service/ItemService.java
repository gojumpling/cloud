package com.code.service;

import com.code.pojo.Item;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-04
 */
public interface ItemService extends IService<Item> {



    public Item getItemById(String item_id);

    public List<Item> getItemList(String itemSet_id);




}
