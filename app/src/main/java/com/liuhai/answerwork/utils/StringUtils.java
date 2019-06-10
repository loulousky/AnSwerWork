package com.liuhai.answerwork.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.liuhai.bean.ChoseState;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liuhai
 */
public class StringUtils {

    /**
     * 取到答案和标题
     * @param resule | 分割
     * @return
     */
    public static List<String> getSelectdate(String resule){
        if(resule==null){
            return null;
        }
        String[] one=resule.split("\\|");
        //拿到标题
        if(null==one ||one.length<=0){
            return null;
        }

        return  Arrays.asList(one);
    }

    /**
     * 取到答案和标题
     * @param resule ** 分割
     * @return
     */
    public static List<String> getSelectdateStar(String resule){
        if(resule==null){
            return null;
        }
        String[] one=resule.split("\\*\\*");
        //拿到标题
        if(null==one ||one.length<=0){
            return null;
        }

        return  Arrays.asList(one);
    }
    /**
     * 取到答案和标题
     * @param resule $$ 分割
     * @return
     */
    public static List<String> getSelectdatedolr(String resule){
        if(resule==null){
            return null;
        }
        String[] one=resule.split("\\$\\$");
        //拿到标题
        if(null==one ||one.length<=0){
            return null;
        }

        return  Arrays.asList(one);
    }

    /**
     * 获取所有答案的拼接
     * @param guides
     * @return
     */
    public static String getAnsweers(List<ChoseState> guides) {
        int size=guides.size();

        String[] answers=new String[size];
        for (int i = 0; i < size; i++) {
            if(guides.get(i).isCurrentCorrect()){
                answers[i]="1";
            }else{
                //没有选择
                if(guides.get(i).getState().getAnswer()==null){
                    answers[i]=null;
                }else{
                    answers[i]="0";
                }
            }
        }


     StringBuilder builder=new StringBuilder();

        for (int i = 0; i < answers.length; i++) {
            builder.append("|");
            if(answers[i]==null){
                continue;
            }else
            {
                builder.append(answers[i]);
            }
        }

        return builder.toString();






    }
}
