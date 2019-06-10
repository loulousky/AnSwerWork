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


    /**
     * 获取单个题目的答案
     * @param questionId 章节
     * @param blockid
     * @param examid 题目
     * @return
     */
    public static String QuestionInvaild(int questionId,int blockid,int examid){

        return "https://learn-quantum.com/api/Edu/education/examAnswerInfo.json?questionid="+questionId+"&examid="+examid+"&blockid="+blockid;
    }


    /**
     * 最后答案的提交算分
     * @param blockid
     * @param examid
     * @param answers  ！0！1！0！1  分割
     * @return
     */
    public static String QuestionCommit(int blockid,int examid,String answers){


        return "https://learn-quantum.com/api/Edu/education/updexaminfo.json?examid="+examid+"&addscoreflag=true&blockid="+blockid+"&status="+answers;
    }
}
