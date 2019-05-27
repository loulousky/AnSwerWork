package com.liuhai.answerwork.utils;

/**
 * 请求地址
 * @author liuhai
 */
public class Content {

    /**
     *
     * @param vid 题目id
     * @return 具体地址
     */
    public static String QuestionsCollect(String vid){

        return "https://learn-quantum.com/api/Edu/education/getVideoInfoExamInfoByVid.json?vid="+vid;


    }


    /**
     *  题目详情
     * @param blockid 章节id
     * @param examid 题目id
     * @return 地址
     */
    public static String QuestionDeatil(int questionId,int blockid,int examid){


        return "https://learn-quantum.com/api/Edu/education/examinfo.json?questionid="+questionId+"&blockid="+blockid+"&examid="+examid;

    }




}
