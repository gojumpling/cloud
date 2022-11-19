package com.code.client;


import com.code.pojo.Item;
import com.code.pojo.Itemset;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient("item-set-service")
public interface ItemSetClient {


    @RequestMapping("/Itemset/ItemSetList_No")
     List<Itemset> getItemSetListByNo(@RequestBody String user_no);

    @RequestMapping("/Itemset/ItemList")
     List<Item> getItemList(@RequestBody List<Itemset> list);


}
