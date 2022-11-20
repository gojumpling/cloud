package com.code.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.client.UserClient;
import com.code.pojo.Item;
import com.code.pojo.ItemSetDetail;
import com.code.pojo.Itemset;

import com.code.pojo.User;
import com.code.service.ItemService;
import com.code.service.ItemsetService;
import lombok.Data;
import lombok.val;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    private ItemsetService itemSetService;


    private ItemService itemService;

    private UserClient userClient;

    @Autowired
    public ItemsetController(ItemsetService itemSetService,ItemService itemService,UserClient userClient){
        this.itemSetService = itemSetService;
        this.itemService = itemService;
        this.userClient = userClient;
    }


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


    @RequestMapping("/ItemSetList_No")
    public List<Itemset> getItemSetListByNo(@RequestBody String user_no){

        List<Itemset> itemSetList = itemSetService.getItemSetList(user_no);

        return itemSetList;

    }


    @RequestMapping("/ItemList")
    public List<Item> getItemList(@RequestBody List<Itemset> list){

        List<String> stringList = new ArrayList<>();
        for (Itemset itemset:list) {
            stringList.add(String.valueOf(itemset.getItemsetId()));
        }

        LambdaQueryWrapper<Item> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.in(Item::getItemsetId,stringList);

        return itemService.list(lambdaQueryWrapper);


    }


    @RequestMapping("/CreateItemSet")
    public Long CreateItemSet(@RequestBody Map<String, Object> map) throws ParseException {
        String itemset_title = map.get("itemset_title").toString();
        String itemset_type = map.get("itemset_type").toString();
        Date start_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("start_time").toString());
        Date end_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("end_time").toString());
        String user_no = map.get("user_no").toString();

        Itemset itemset = new Itemset();
        itemset.setItemsetTitle(itemset_title);
        itemset.setItemsetType(itemset_type);
        itemset.setItemsetStarttime(start_time);
        itemset.setItemsetEndtime(end_time);
        itemset.setUserNo(user_no);

        return itemSetService.saveItemSet(itemset);

    }




}

