package com.liuhai.bean;

/**
 * 选项的数据类
 * @author liuhai
 */
public class NumberDate {

    private String head;
    private String content;
    private boolean chose=false;

    public NumberDate(String head, String content) {
        this.head = head;
        this.content = content;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public boolean isChose() {
        return chose;
    }

    public void setChose(boolean chose) {
        this.chose = chose;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
