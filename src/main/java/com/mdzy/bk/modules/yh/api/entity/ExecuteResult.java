package com.mdzy.bk.modules.yh.api.entity;

/**
 * Created by Administrator on 2016/8/23.
 */
public class ExecuteResult<T> {
    private String code;
    private String msg;
    private T extra;

    public ExecuteResult(){
        this.code = "200";
        this.msg = "success";
    }
    public ExecuteResult(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ExecuteResult(T t){
        this.code = "200";
        this.msg = "success";
        this.extra = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }

}
