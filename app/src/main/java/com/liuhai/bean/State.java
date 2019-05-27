package com.liuhai.bean;

import android.support.annotation.NonNull;
import android.util.Log;

/***
 * 标记答案选择情况
 * 默认为NOTCHOSE
 * @author liuhai
 */

public enum State {
NOANSWER(null),A("A"),B("B"),C("C"),D("D"),E("E"),F("F"),G("G"),H("H"),I("I"),J("J"),K("K"),L("L"),M("M"),N("N"),O("O"),P("P"),Q("Q"),R("R"),
    S("S"),T("T"),U("U"),V("V"),W("W"),X("X"),Y("Y"),Z("Z");
    private final String answer;
     State(String answer){
        this.answer=answer;
    }

    public String getAnswer() {
        return answer;
    }

    public static State  getValue(String value){
        for(State temp:State.values()){

            Log.d("liuhai",temp.toString());
            if(value.contains(temp.toString()))
           {
                return temp;
            }
        }

        return State.NOANSWER;

    }

}
