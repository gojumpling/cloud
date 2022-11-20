package com.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.code.pojo.Itemset;
import com.code.mapper.ItemsetMapper;
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
 * @since 2022-11-05
 */
@Service
public class ItemSetServiceImpl extends ServiceImpl<ItemsetMapper, Itemset> implements ItemSetService {
    @Override
    public List<Itemset> getExperimentItemSet() {
        LambdaQueryWrapper<Itemset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Itemset::getItemsetType,"实验");

        List<Itemset> itemSetList = this.list(lambdaQueryWrapper);
        return itemSetList;
    }

    @Override
    public List<Itemset> getMyItemSet(String user_no) {

        return null;
    }

    @Override
    public List<Itemset> getTestItemSet() {
        LambdaQueryWrapper<Itemset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Itemset::getItemsetType,"考试");

        List<Itemset> itemSetList = this.list(lambdaQueryWrapper);
        return itemSetList;
    }

    @Override
    public List<Itemset> getPracticeItemSet() {
        LambdaQueryWrapper<Itemset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Itemset::getItemsetType,"练习");

        List<Itemset> itemSetList = this.list(lambdaQueryWrapper);
        return itemSetList;
    }

    @Override
    public Itemset getItemSetById(String itemSet_id) {
        LambdaQueryWrapper<Itemset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Itemset::getItemsetId,itemSet_id);

        Itemset itemSet = this.getOne(lambdaQueryWrapper);
        return itemSet;
    }

    @Override
    public List<Itemset> getItemSetList(String user_no) {

        LambdaQueryWrapper<Itemset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Itemset::getUserNo,user_no);
        List<Itemset> itemSetList = this.list(lambdaQueryWrapper);
        return itemSetList;
    }

    @Override
    public Long saveItemSet(Itemset itemset) {
        this.save(itemset);
        LambdaQueryWrapper<Itemset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Itemset::getItemsetId);
        return this.getOne(lambdaQueryWrapper).getItemsetId();


    }


}
