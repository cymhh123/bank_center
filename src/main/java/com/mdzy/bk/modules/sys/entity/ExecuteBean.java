package com.mdzy.bk.modules.sys.entity;

/**
 * 返回实体
 * Created by Administrator on 2016/7/18.
 */
public class ExecuteBean<T> {
    private String code;
    private String msg;
    private T extra;
    private Integer pageTotal;
    private Integer total;

    public ExecuteBean(){
        this.code = StatusCode.SUCCESS.getValue();
        this.msg = StatusCode.SUCCESS.getDescription();
    }
    public ExecuteBean(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public ExecuteBean(T extra,Integer pageTotal,Integer total,String code,String msg){
        this.extra = extra;
        this.pageTotal = pageTotal;
        this.total = total;
        this.code = code;
        this.msg = msg;
    }
    public ExecuteBean(T extra,Integer pageTotal,Integer total){
        this.extra = extra;
        this.pageTotal = pageTotal;
        this.total = total;
        this.code = StatusCode.SUCCESS.getValue();
        this.msg = StatusCode.SUCCESS.getDescription();
    }

    public ExecuteBean(T extra){
        this.extra = extra;
        this.code = StatusCode.SUCCESS.getValue();
        this.msg = StatusCode.SUCCESS.getDescription();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
