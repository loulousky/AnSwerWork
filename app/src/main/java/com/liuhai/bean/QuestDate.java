package com.liuhai.bean;

import java.util.List;

/**
 * 答案数据模型
 * @author liuhai
 *
 */
public class QuestDate {
    //标题
    private String questTitle;


    private int questionId;

    private int blockId;

    private  int examId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    //答案标
    private List<NumberDate> date;

    public QuestDate(int questionId, int blockId, int examId) {
        this.questionId = questionId;
        this.blockId = blockId;
        this.examId = examId;
    }

    public QuestDate(String questTitle, List<NumberDate> date) {
        this.questTitle = questTitle;
        this.date = date;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public List<NumberDate> getDate() {
        return date;
    }

    public void setDate(List<NumberDate> date) {
        this.date = date;
    }



}
