package com.code.controller;


import com.code.pojo.Item;
import com.code.pojo.ItemSetDetail;
import com.code.pojo.Itemset;

import com.code.service.ItemService;
import com.code.service.ItemsetService;
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
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/Itemset")
public class ItemsetController {

    @Autowired
    ItemsetService itemSetService;

    @Autowired
    ItemService itemService;


    @RequestMapping("/ExperimentItemSet")
    public List<Itemset> getExperimentItemSet(){

        return itemSetService.getExperimentItemSet();

    }

    @RequestMapping("/MyItemSet")
    public List<Itemset> getMyItemSet(){

        return itemSetService.getMyItemSet();

    }

    @RequestMapping("/TestItemSet")
    public List<Itemset> getTestItemSet(){

        return itemSetService.getTestItemSet();

    }

    @RequestMapping("/PracticeItemSet")
    public List<Itemset> getPracticeItemSet(){

        return itemSetService.getPracticeItemSet();

    }

    @RequestMapping("/ItemSet_info")
    public Itemset getItemSetInfo(@RequestBody Map<String, Object> map){

        String item_id = map.get("itemset_id").toString();

        return itemSetService.getItemSetById(item_id);

    }


    @RequestMapping("/ItemSet_list")
    public ItemSetDetail getItemSetList(@RequestBody Map<String, Object> map){

        String itemSet_id = map.get("itemset_id").toString();


        ItemSetDetail itemSetDetail = new ItemSetDetail();

        Itemset itemset = itemSetService.getItemSetById(itemSet_id);
        List<Item> itemList = itemService.getItemList(itemSet_id);

        itemSetDetail.setItemset(itemset);
        itemSetDetail.setItemList(itemList);

        return itemSetDetail;

    }

}

