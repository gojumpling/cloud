package com.code.controller;


import com.code.client.AnswerClient;
import com.code.client.ItemSetClient;

import com.code.pojo.Answer;
import com.code.pojo.Testcase;
import com.code.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
public class AnswerController  implements AnswerClient {



    private AnswerService answerService;
    private ItemSetClient itemSetClient;

    @Autowired
    public AnswerController(AnswerService answerService,ItemSetClient itemSetClient){
        this.answerService = answerService;
        this.itemSetClient = itemSetClient;
    }



    @Override
    @RequestMapping("/Save")
    public boolean insertAnswer(@RequestBody Answer answer){

        answerService.insertAnswer(answer);
        return true;
    }

    @Override
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

    @RequestMapping("/Record")
    public Map<String,Object> getAnswerRecord(@RequestBody Map<String,Object> map){

        String item_id = map.get("item_id").toString();
        String user_no = map.get("user_no").toString();
        String itemset_id = map.get("itemset_id").toString();

        Answer answer = answerService.getAnswerRecord(user_no,item_id,itemset_id);
        Map<String, Object> hashMap = new HashMap<>(2);


        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("item_id",item_id);

        List<Testcase> testcaseList = itemSetClient.getTestCase(objectMap);

        testcaseList.forEach(testcase ->{
            testcase.setTestcaseOutput("");

        });

        hashMap.put("reset",answer);
        hashMap.put("testcaseList",testcaseList);


        return hashMap;
    }





}

