package com.liuhai.answerwork.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liuhai
 */
public class StringUtils {

    /**
     * 取到答案和标题
     * @param resule
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



}
