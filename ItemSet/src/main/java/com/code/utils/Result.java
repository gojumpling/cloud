package com.code.utils;

import lombok.Data;

import java.util.Map;

@Data
public class Result {

    private int code;
    private String msg;
    private Map<String,Object> map;

}
