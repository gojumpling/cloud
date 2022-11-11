package com.code.client;

import com.code.pojo.Answer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("answerService")
public interface AnswerClient {

    @RequestMapping("/Answer/Save")
    public boolean insertAnswer(Answer answer);

    @RequestMapping("/Answer/PassingRate")
    public float getPassingRate(@RequestBody Map<String,Object> map);

}
