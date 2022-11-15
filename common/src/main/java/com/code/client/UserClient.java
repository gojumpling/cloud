package com.code.client;


import com.code.pojo.Itemset;
import com.code.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient("user-service")
public interface UserClient {


    @RequestMapping("/User/Info")
    User getUserInfo(@RequestBody Map<String,Object> map);

    @RequestMapping("/User/TeacherNo")
    public List<User> getTeacherByClazz(@RequestBody Map<String,Object> map);


}
