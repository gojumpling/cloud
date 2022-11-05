package com.code.service;

import com.code.pojo.Answer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-05
 */
public interface AnswerService extends IService<Answer> {

    public boolean insertAnswer(Answer answer);

    public int countFullScoreUser(String item_id,String itemset_id,int grade);
    public int countSubmitUser(String item_id,String itemset_id);



}
