package com.code.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.client.ItemSetClient;
import com.code.pojo.Item;
import com.code.pojo.Itemset;
import com.code.pojo.User;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * @since 2022-11-19
 */
@RestController
@RequestMapping("/User")

public class UserController {


    private UserService userService;


    private ItemSetClient itemSetClient;

    @Autowired
    public UserController(UserService userService,ItemSetClient itemSetClient){
        this.userService = userService;
        this.itemSetClient = itemSetClient;
    }



    @RequestMapping("/Login")
    public User login(@RequestBody Map<String,Object> map){

        String user_no = map.get("user_no").toString();
        String pwd = map.get("pwd").toString();
        User user = userService.Login(user_no,pwd);


        return user;

    }

    @RequestMapping("/Info")
    public User getUserInfo(@RequestBody Map<String,Object> map){

        String user_no = map.get("user_no").toString();

        return userService.getUserByNo(user_no);

    }


    /**
     * 获取该班级下教师号
     * 由获取我的题目集所调用
     * @param map
     * @return
     */
    @RequestMapping("/TeacherNo")
    public List<User> getTeacherByClazz(@RequestBody Map<String,Object> map){

        String clazz_no = map.get("clazz_no").toString();
        List<User> userList = userService.getTeacherByClazz(clazz_no);
        return userList;
    }

    @RequestMapping("/TeacherInfo")
    public Map<String,Object> getTeacherInfo(@RequestBody Map<String,Object> map){
        String user_no = map.get("user_no").toString();

//        User user = userService.getUserByNo(user_no);


//        返回题目集、题目、班级数量和列表
        Map<String, Object> infoMap = new HashMap<>(8);

        List<String> clazzList = new ArrayList<>();

        List<User> userList = userService.getTeacherClazz(user_no);
        for (User user:userList) {
            clazzList.add(user.getClazzNo());
        }

        infoMap.put("clazzList",clazzList);
        infoMap.put("clazzNum",clazzList.size());

        List<Itemset> itemSetList = itemSetClient.getItemSetListByNo(user_no);
        infoMap.put("itemSetList",itemSetList);
        infoMap.put("itemSetNum",itemSetList.size());



        List<Item> itemList = itemSetClient.getItemList(itemSetList);
        infoMap.put("itemList",itemList);
        infoMap.put("itemNum",itemList.size());



        return infoMap;


    }



}

