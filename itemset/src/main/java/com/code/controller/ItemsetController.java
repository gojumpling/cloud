package com.code.controller;


import com.code.client.UserClient;
import com.code.pojo.Item;
import com.code.pojo.ItemSetDetail;
import com.code.pojo.Itemset;

import com.code.pojo.User;
import com.code.service.ItemService;
import com.code.service.ItemsetService;
import lombok.val;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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


    @Autowired
    UserClient userClient;


    @RequestMapping("/ExperimentItemSet")
    public List<Itemset> getExperimentItemSet(){

        return itemSetService.getExperimentItemSet();

    }

    @RequestMapping("/MyItemSet")
    public List<Itemset> getMyItemSet(@RequestBody Map<String, Object> map){

//        String user_no = map.get("user_no").toString();

        User user = userClient.getUserInfo(map);
        String clazz = user.getClazzNo();

        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("clazz_no",clazz);
        List<User> userList = userClient.getTeacherByClazz(objectMap);

        List<Itemset> list = new ArrayList<>();
        for (User user1:userList) {
            List<Itemset> itemsetList = itemSetService.getItemSetList(user1.getUserNo());
            list.addAll(itemsetList);
        }

        return list;

    }

    @RequestMapping("/TestItemSet")
    public List<Itemset> getTestItemSet(){

        return itemSetService.getTestItemSet();

    }

    @RequestMapping("/PracticeItemSet")
    public List<Itemset> getPracticeItemSet(){
        System.out.println("1111");
        return itemSetService.getPracticeItemSet();

    }

    @RequestMapping("/ItemSet_info")
    public Itemset getItemSetInfo(@RequestBody Map<String, Object> map){

        String item_id = map.get("itemset_id").toString();

        return itemSetService.getItemSetById(item_id);

    }


    @RequestMapping("/ItemSetList")
    public ItemSetDetail getItemSetListById(@RequestBody Map<String, Object> map){

        String itemSet_id = map.get("itemset_id").toString();


        ItemSetDetail itemSetDetail = new ItemSetDetail();

        Itemset itemset = itemSetService.getItemSetById(itemSet_id);
        List<Item> itemList = itemService.getItemList(itemSet_id);

        itemSetDetail.setItemset(itemset);
        itemSetDetail.setItemList(itemList);

        return itemSetDetail;

    }



}

