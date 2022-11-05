package com.code.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.pojo.Answer;
import com.code.pojo.Item;
import com.code.pojo.Testcase;
import com.code.service.ItemService;
import com.code.service.TestcaseService;
import com.code.service.client.AnswerClient;

import kong.unirest.Unirest;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
@RequestMapping("/Item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    TestcaseService testcaseService;

    @Autowired
    AnswerClient answerClient;

    @RequestMapping("/Item_info")
    public Item getItem(@RequestBody Map<String,Object> map){

        String item_id = map.get("item_id").toString();

        return itemService.getItemById(item_id);

    }


    @RequestMapping("/Submit")
    public List<Testcase> submit(@RequestBody Map<String,Object> map){

        String user_no = map.get("user_no").toString();
        String item_id = map.get("item_id").toString();
        String itemset_id = map.get("itemset_id").toString();
        String code = map.get("code").toString();
        String lang = map.get("lang").toString();

        List<Testcase> testcaseList = testcaseService.getItemTestcase(item_id);

        int sum = 0;
        int count = 0;
        for (Testcase testcase:testcaseList) {
//            跑代码,得分放在testcase中测试点的testcase.setTestcaseGrade(),并算总分sum
            boolean flag = run(lang,code,testcase.getTestcaseInput(),testcase.getTestcaseOutput());
            count += testcase.getTestcaseGrade();
            if(flag){
                sum+=testcase.getTestcaseGrade();
            }
        }



        Answer answer = new Answer();
        answer.setUserNo(user_no);
        answer.setItemId(Long.parseLong(item_id));
        answer.setGrade(sum);
        answer.setContent(code);
        answer.setTime(new Date());
        answer.setItemsetId(Long.parseLong(itemset_id));
//        调用答题记录服务，保存答题记录
        answerClient.insertAnswer(answer);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("item_id",item_id);
        map1.put("itemset_id",itemset_id);
        map1.put("grade",count);

//        计算通过率,从该题目、该题目集的答题记录中,计算满分人数、和提交人数,
        float rate = answerClient.getPassingRate(map1);
        Item item = itemService.getItemById(item_id);
        item.setPassingRate(rate);
        itemService.updataItem(item);

        return testcaseList;

    }





    public boolean run(String lang,String code,String input,String output){

        input = input.replace("\\n","\n");
        output = output.replace("\\n","\n");


        JSONObject json = new JSONObject();
        json.put("lang",lang);
        json.put("code",code);
        json.put("input",input);
//        log.info(String.valueOf(json));
        String res = "";

        res = Unirest.post("http://114.132.64.132:8000/run/code")
                .header("User-Agent", "apifox/1.0.0 (https://www.apifox.cn)")
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Host", "114.132.64.132:8000")
                .header("Connection", "keep-alive")
                .body(json)
                .asString().getBody();

        JSONObject jsonObject = JSON.parseObject(res);

        System.out.println(res);

        String data = (String) jsonObject.get("data");
        String time = (String) jsonObject.get("time");
        if(data == null){
            return false;
        }
        return data.equals(output);


    }







}

