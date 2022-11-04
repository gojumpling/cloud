package com.code.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ItemSetDetail {

    private ItemSet itemSet;
    private List<Item> itemList;

}
