package com.code.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.code.pojo.ItemSet;
import com.code.mapper.ItemSetMapper;
import com.code.pojo.ItemSetDetail;
import com.code.service.ItemSetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;


import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-04
 */
@Service
public class ItemSetServiceImpl extends ServiceImpl<ItemSetMapper, ItemSet> implements ItemSetService {


    @Override
    public List<ItemSet> getExperimentItemSet() {
        LambdaQueryWrapper<ItemSet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ItemSet::getItemsetType,"实验");

        List<ItemSet> itemSetList = this.list(lambdaQueryWrapper);
        return itemSetList;
    }

    @Override
    public List<ItemSet> getMyItemSet() {
        return null;
    }

    @Override
    public List<ItemSet> getTestItemSet() {
        LambdaQueryWrapper<ItemSet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ItemSet::getItemsetType,"考试");

        List<ItemSet> itemSetList = this.list(lambdaQueryWrapper);
        return itemSetList;
    }

    @Override
    public List<ItemSet> getPracticeItemSet() {
        LambdaQueryWrapper<ItemSet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ItemSet::getItemsetType,"练习");

        List<ItemSet> itemSetList = this.list(lambdaQueryWrapper);
        return itemSetList;
    }

    @Override
    public ItemSet getItemSetById(String itemSet_id) {
        LambdaQueryWrapper<ItemSet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ItemSet::getItemsetId,itemSet_id);

        ItemSet itemSet = this.getOne(lambdaQueryWrapper);
        return itemSet;
    }

    @Override
    public ItemSetDetail getItemSetList(String itemSet_id) {



        ItemSetDetail itemSetDetail = new ItemSetDetail();

        ItemSet itemSet = this.getItemSetById(itemSet_id);




        itemSetDetail.setItemSet(itemSet);



        return itemSetDetail;


    }
}
