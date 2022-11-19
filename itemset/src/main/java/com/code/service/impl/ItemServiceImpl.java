package com.code.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.code.pojo.Item;
import com.code.mapper.ItemMapper;
import com.code.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kong.unirest.Unirest;
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
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Override
    public Item getItemById(String item_id) {
        LambdaQueryWrapper<Item> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Item::getItemId,item_id);

        Item item = this.getOne(lambdaQueryWrapper);
        return item;
    }

    @Override
    public List<Item> getItemList(String itemSet_id) {
        LambdaQueryWrapper<Item> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Item::getItemsetId,itemSet_id);

        List<Item> itemList = this.list(lambdaQueryWrapper);
        return itemList;
    }

    @Override
    public boolean updataItem(Item item) {

        return this.updateById(item);



    }

    @Override
    public boolean run(String lang, String code, String input, String output) {
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

    @Override
    public Long saveItem(Item item) {

        this.save(item);
        LambdaQueryWrapper<Item> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Item::getItemId);

        return this.getOne(lambdaQueryWrapper).getItemId();


    }


}
