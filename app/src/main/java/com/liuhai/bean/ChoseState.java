package com.liuhai.bean;

/**
 *
 * 选择情况，另外附带题目列表数据
 */
public class ChoseState {

     private String little_title;

     private State state;


    public String getLittle_title() {
        return little_title;
    }

    public void setLittle_title(String little_title) {
        this.little_title = little_title;
    }

    public ChoseState(String little_title, State state) {
        this.little_title = little_title;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
