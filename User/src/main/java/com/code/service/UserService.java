package com.code.service;

import com.code.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-19
 */
public interface UserService extends IService<User> {

    User Login(String user_no,String pwd);
    User getUserByNo(String user_no);
    List<User> getTeacherByClazz(String clazz_no);


}
