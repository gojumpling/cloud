package com.code.service;

import com.code.pojo.Testcase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-05
 */
public interface TestcaseService extends IService<Testcase> {

    public List<Testcase> getItemTestcase(String item_id);


    public int getItemGrade(String item_id);

    Long saveTestcase(Testcase testcase);

}
