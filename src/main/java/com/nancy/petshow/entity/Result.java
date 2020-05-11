package com.nancy.petshow.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author chen
 * @date 2020/5/11 13:51
 */
@Data
@ToString
@NoArgsConstructor
public class Result {
    /**
     * 响应的状态码
     */
    private int code;

    /**
     * 响应的提示语
     */
    private String msg;

    /**
     * 响应的对象数据
     */
    private Object object;

    /**
     * 响应的集合数据
     */
    private List<Object> list;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    public Result(int code, String msg, List<Object> list) {
        this.code = code;
        this.msg = msg;
        this.list = list;
    }

    public Result(int code, String msg, Object object, List<Object> list) {
        this.code = code;
        this.msg = msg;
        this.object = object;
        this.list = list;
    }
}
