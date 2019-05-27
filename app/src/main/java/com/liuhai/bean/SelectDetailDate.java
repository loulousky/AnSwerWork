package com.liuhai.bean;


/**
 * 每个题目选项页面的详情
 * @author liuhai
 */
public class SelectDetailDate {


    /**
     * questionid : 1001001
     * blockid : 0
     * answer : null
     * questiontype : 0
     * examid : 0
     * content : 量子概念是由谁提出的？|A、海森堡|B、爱因斯坦|C、薛定谔|D、普朗克
     * answerdetail : null
     * num : 0
     */

    private int questionid;
    private int blockid;
    private Object answer;
    private int questiontype;
    private int examid;
    private String content;
    private String answerdetail;
    private int num;

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public int getBlockid() {
        return blockid;
    }

    public void setBlockid(int blockid) {
        this.blockid = blockid;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public int getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(int questiontype) {
        this.questiontype = questiontype;
    }

    public int getExamid() {
        return examid;
    }

    public void setExamid(int examid) {
        this.examid = examid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerdetail() {
        return answerdetail;
    }

    public void setAnswerdetail(String answerdetail) {
        this.answerdetail = answerdetail;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
