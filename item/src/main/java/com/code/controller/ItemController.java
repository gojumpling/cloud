package com.code.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.pojo.Item;
import com.code.pojo.ItemSet;
import com.code.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-04
 */
@RestController
@RequestMapping("/Item")
public class ItemController {



    @Autowired
    ItemService itemService;

    @RequestMapping("/Item_info")
    public Item getItem(@RequestBody Map<String,Object> map){

        String item_id = map.get("item_id").toString();

        return itemService.getItemById(item_id);

    }


    @RequestMapping("/ItemList")
    public List<Item> getItemList(@RequestBody Map<String,Object> map){

        String itemSet_id = map.get("itemSet_id").toString();

        return itemService.getItemList(itemSet_id);

    }



}

