package com.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.pojo.User;
import com.code.mapper.UserMapper;
import com.code.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User Login(String user_no, String pwd) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserNo,user_no);
        lambdaQueryWrapper.eq(User::getUserPwd,pwd);

        User user = this.getOne(lambdaQueryWrapper);
        return user;

    }

    @Override
    public User getUserByNo(String user_no) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserNo,user_no);
        return this.getOne(lambdaQueryWrapper);

    }

    @Override
    public List<User> getTeacherByClazz(String clazz_no) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getClazzNo,clazz_no);
        lambdaQueryWrapper.eq(User::getUserRole,"教师");

        return this.list(lambdaQueryWrapper);


    }
}
