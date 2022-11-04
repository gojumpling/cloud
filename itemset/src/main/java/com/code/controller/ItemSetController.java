package com.code.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.pojo.ItemSet;
import com.code.pojo.ItemSetDetail;
import com.code.service.ItemSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-04
 */
@RestController
@RequestMapping("/ItemSet")
public class ItemSetController {


    @Autowired
    ItemSetService itemSetService;

    @RequestMapping("/ExperimentItemSet")
    public List<ItemSet> getExperimentItemSet(){

        return itemSetService.getExperimentItemSet();

    }

    @RequestMapping("/MyItemSet")
    public List<ItemSet> getMyItemSet(){

        return itemSetService.getMyItemSet();

    }

    @RequestMapping("/TestItemSet")
    public List<ItemSet> getTestItemSet(){

        return itemSetService.getTestItemSet();

    }

    @RequestMapping("/PracticeItemSet")
    public List<ItemSet> getPracticeItemSet(){

        return itemSetService.getPracticeItemSet();

    }

    @RequestMapping("/ItemSet_info")
    public ItemSet getItemSetInfo(@RequestBody Map<String, Object> map){

        String item_id = map.get("itemSet_id").toString();

        return itemSetService.getItemSetById(item_id);

    }


    @RequestMapping("/ItemSet_list")
    public ItemSetDetail getItemSetList(@RequestBody Map<String, Object> map){

        String itemSet_id = map.get("itemSet_id").toString();


        ItemSetDetail itemSetDetail=itemSetService.getItemSetList(itemSet_id);

        return itemSetDetail;

    }






}

