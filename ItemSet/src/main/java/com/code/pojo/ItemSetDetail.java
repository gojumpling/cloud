package com.code.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ItemSetDetail {

    private Itemset itemset;
    private List<Item> itemList;
}
