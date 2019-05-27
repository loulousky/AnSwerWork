package com.liuhai.bean;

import java.io.Serializable;

/**
 * 题目总数据集合
 */
public class BasicAnserDate<T>  implements Serializable {
    private static final long serialVersionUID = -2298814525219837737L;
    boolean success;
    String message;
    String requestURI;
    String execptionTrace;
    T obj;




    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getExecptionTrace() {
        return execptionTrace;
    }

    public void setExecptionTrace(String execptionTrace) {
        this.execptionTrace = execptionTrace;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
