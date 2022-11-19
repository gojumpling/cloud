package com.code.controller;


import com.code.pojo.Itemset;
import com.code.pojo.Testcase;
import com.code.service.TestcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/Testcase")
public class TestcaseController {


    @Autowired
    TestcaseService testcaseService;

    @RequestMapping("/InsertTestCase")
    public Long insertTestCase(@RequestBody Map<String,Object> map){
        String item_id = map.get("item_id").toString();
        String testcase_input = map.get("testcase_input").toString();
        String testcase_output = map.get("testcase_output").toString();
        String testcase_name = map.get("testcase_name").toString();
        String testcase_grade = map.get("testcase_grade").toString();
        String flag = map.get("flag").toString();

        Testcase testcase = new Testcase();
        testcase.setItemId(Long.parseLong(item_id));
        testcase.setTestcaseInput(testcase_input);
        testcase.setTestcaseOutput(testcase_output);
        testcase.setTestcaseName(testcase_name);
        testcase.setTestcaseGrade(Long.parseLong(testcase_grade));
        testcase.setFlag(Long.parseLong(flag));

        return testcaseService.saveTestcase(testcase);

    }

}

