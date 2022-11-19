package com.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.pojo.Itemset;
import com.code.pojo.Testcase;
import com.code.mapper.TestcaseMapper;
import com.code.service.TestcaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-05
 */
@Service
public class TestcaseServiceImpl extends ServiceImpl<TestcaseMapper, Testcase> implements TestcaseService {

    @Override
    public List<Testcase> getItemTestcase(String item_id) {

        LambdaQueryWrapper<Testcase> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Testcase::getItemId,item_id);
        List<Testcase> testcaseList = this.list(lambdaQueryWrapper);

        return testcaseList;

    }

    @Override
    public int getItemGrade(String item_id) {

        LambdaQueryWrapper<Testcase> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Testcase::getItemId,item_id);
        List<Testcase> testcaseList = this.list(lambdaQueryWrapper);

        int sum = 0;
        for (Testcase testcase:testcaseList) {
            sum += testcase.getTestcaseGrade();
        }
        return sum;
    }

    @Override
    public Long saveTestcase(Testcase testcase) {

        this.save(testcase);
        LambdaQueryWrapper<Testcase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Testcase::getTestcaseId);
        return this.getOne(lambdaQueryWrapper).getTestcaseId();

    }

}
