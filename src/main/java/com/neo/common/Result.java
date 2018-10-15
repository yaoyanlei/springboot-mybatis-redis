package com.neo.common;

import java.io.Serializable;
import java.util.Collections;

/**
 * 通用返回对象
 * @param <T>
 * @ClassName: Result
 * @Description: 数据封装对象
 * @author:yuepeng001
 * @date 2018年9月16日 下午4:40:11
 */
@SuppressWarnings("serial")
public class Result<T> implements Serializable {

    public static final Result<Object> SUCCESS = new Result<>(true);
    public static final Result<Object> FAIL = new Result<>(false);

    /**
     * 错误编码
     */
    private int code;

    /**
     * 本地化的信息,对应错误编码的本地化说明
     */
    private String msg;

    /**
     * 执行状态
     */
    private boolean status;

    /**
     * 返回值对象
     */
    private T data;

    public Result() {
    }

    /**
     * 如果状态为true,默认 msg=succes code="0000" 创建一个新的实例 Result.
     *
     * @param status
     */
    @SuppressWarnings("unchecked")
	public Result(boolean status) {
        if (status == true) {
            this.status = status;
            this.msg = "success";
            this.code = 0;
            this.data = (T) Collections.emptyMap();
        } else {
            this.status = status;
            this.msg = "请求失败";
            this.code = 1;
            this.data = (T) Collections.emptyMap();
        }

    }

    /**
     * 创建一个新的实例 Result.
     *
     * @param status
     * @param data
     */
    public Result(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    /**
     * 创建一个新的实例 Result. 对于增加、删除、修改成功或者失败的处理
     *
     * @param status
     * @param code
     */
    @SuppressWarnings("unchecked")
	public Result(boolean status, int code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = (T) Collections.emptyMap();
    }

    /**
     * 对于查询或者需要返回Data数据的处理
     *
     * @param status
     * @param data
     */
    public Result(boolean status, int code, T data, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 如果放入的数据不为空, this.status = true;this.msg = "success";this.code = "0000"; 创建一个新的实例 Result.
     *
     * @param data
     */
    public Result(T data) {
        this.status = true;
        this.data = data;
        this.msg = "success";
        this.code = 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
