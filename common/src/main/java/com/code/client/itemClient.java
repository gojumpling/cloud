package com.code.client;

import com.code.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@FeignClient("itemservice")//全部小写，改了报错
public interface itemClient {

    @RequestMapping("/Item/ItemList")
    List<Item> getItemList(@RequestBody Map<String,Object> map);

}
