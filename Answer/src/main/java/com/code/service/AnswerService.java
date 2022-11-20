package com.code.service;

import com.code.pojo.Answer;
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
public interface AnswerService extends IService<Answer> {

     boolean insertAnswer(Answer answer);

     int countFullScoreUser(String item_id,String itemset_id,int grade);
     int countSubmitUser(String item_id,String itemset_id);

    Answer getAnswerRecord(String user_no,String item_id,String itemset_id);


}
