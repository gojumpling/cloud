package com.code.controller;


import com.code.pojo.User;
import com.code.service.UserService;
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
 * @since 2022-11-19
 */
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

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



    @RequestMapping("/TeacherNo")
    public List<User> getTeacherByClazz(@RequestBody Map<String,Object> map){

        String clazz_no = map.get("clazz_no").toString();
        List<User> userList = userService.getTeacherByClazz(clazz_no);
        return userList;
    }


}

