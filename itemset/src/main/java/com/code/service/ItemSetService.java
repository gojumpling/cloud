package com.code.service;

import com.code.pojo.ItemSet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.code.pojo.ItemSetDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2022-11-04
 */
public interface ItemSetService extends IService<ItemSet> {

    public List<ItemSet> getExperimentItemSet();
    public List<ItemSet> getMyItemSet();
    public List<ItemSet> getTestItemSet();
    public List<ItemSet> getPracticeItemSet();
    public ItemSet getItemSetById(String itemSet_id);
    public ItemSetDetail getItemSetList(String itemSet_id);


}
