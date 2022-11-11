package com.code.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        System.out.println("响应拦截成功");
        System.out.println(body);

        //如果直接响应字符串返回,则会报类型转换异常.
//        if(body instanceof String){
//            return JSON.toJSON(Result.ok(o));
//        }
//        if (body instanceof Result) {
//            return body;
//        } else if (body == null) {
//            return BaseResponse.ok();
//        } else {
//            return BaseResponse.ok(body);
//        }
        return null;

    }
}
