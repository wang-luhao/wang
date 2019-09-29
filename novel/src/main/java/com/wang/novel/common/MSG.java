package com.wang.novel.common;
import java.util.HashMap;
import java.util.Map;

public class MSG {
    //状态码
    private int code;
    private String msg;
    private Map<String, Object> extend = new HashMap<String, Object>();
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
    public Map<String, Object> getExtend() {
        return extend;
    }
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
    public static MSG success() {
        MSG result = new MSG();
        result.setCode(100);
        result.setMsg("success");

        return result;
    }
    public static MSG fail() {
        MSG result = new MSG();
        result.setCode(200);
        result.setMsg("fail");

        return result;
    }
    public MSG add(String key,Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}
