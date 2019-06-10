package com.liuhai.bean;

/**
 * 题目答案返回的数据类
 * @author liuhai
 */
public class InvaildAnswer {


    /**
     * questionid : 2001001
     * blockid : 2
     * answer : B
     * questiontype : 0
     * examid : 1
     * content : null
     * answerdetail : 解析：本源量子云平台包含五大模块：云体验区、量子计算后端、量子程序、科普与教程、论坛与反馈。
     * num : 0
     */

    private int questionid;
    private int blockid;
    private String answer;
    private int questiontype;
    private int examid;
    private Object content;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
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
