package com.code.controller;


import com.code.pojo.Answer;
import com.code.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/Answer")
public class AnswerController {


    @Autowired
    AnswerService answerService;

    @RequestMapping("/Save")
    public boolean insertAnswer(@RequestBody Answer answer){

        answerService.insertAnswer(answer);
        return true;
    }


    @RequestMapping("/PassingRate")
    public float getPassingRate(@RequestBody Map<String,Object> map){

        String item_id = map.get("item_id").toString();
        String itemset_id = map.get("itemset_id").toString();
        int grade = (int)map.get("grade");

        int fullSum = answerService.countFullScoreUser(item_id,itemset_id,grade);
        int countSum = answerService.countSubmitUser(item_id,itemset_id);

        float rate = (float) fullSum/(float) countSum;
        return rate;


    }


}

