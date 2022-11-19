package com.code.service;

import com.code.pojo.Itemset;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-05
 */
public interface ItemsetService extends IService<Itemset> {

    public List<Itemset> getExperimentItemSet();
    public List<Itemset> getMyItemSet(String user_no);
    public List<Itemset> getTestItemSet();
    public List<Itemset> getPracticeItemSet();
    public Itemset getItemSetById(String itemSet_id);

    List<Itemset> getItemSetList(String user_no);

    Long saveItemSet(Itemset itemset);

}
