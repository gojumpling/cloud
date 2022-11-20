package com.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.pojo.Answer;
import com.code.mapper.AnswerMapper;
import com.code.service.AnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Override
    public boolean insertAnswer(Answer answer) {

        return this.save(answer);
    }

    @Override
    public int countFullScoreUser(String item_id, String itemset_id,int grade) {
        LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Answer::getItemId,item_id);
        lambdaQueryWrapper.eq(Answer::getItemsetId,itemset_id);
        lambdaQueryWrapper.eq(Answer::getGrade,grade);

        int sum = this.count(lambdaQueryWrapper);

        return sum;

    }

    @Override
    public int countSubmitUser(String item_id, String itemset_id) {
        LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Answer::getItemId,item_id);
        lambdaQueryWrapper.eq(Answer::getItemsetId,itemset_id);

        int sum = this.count(lambdaQueryWrapper);

        return sum;
    }

    @Override
    public Answer getAnswerRecord(String user_no, String item_id, String itemset_id) {
        LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Answer::getUserNo,user_no);
        lambdaQueryWrapper.eq(Answer::getItemsetId,itemset_id);
        lambdaQueryWrapper.eq(Answer::getItemId,item_id);

        lambdaQueryWrapper.orderByDesc(Answer::getGrade);
        return this.getOne(lambdaQueryWrapper);





    }
}
