package com.liuhai.bean;

import java.util.List;

/**
 * 具体题目合集数据
 * @author  liuhai
 *
 * 注意：：：！！
 *  解决数据返回接口 examInfoList 有时候是数组有时候是对象的问题
 *  是对象使用这个类型
 */
public class QuestDetailDateTwo {




    /**
     * block : 1
     * brief : 本章节主要介绍了量子力学以及量子计算的发展历程，通过理解量子计算所需的基本概念和基础数学知识，能够对量子计算入门起到导引作用。
     * free : 0
     * img : https://learn-quantum.com/assets/images/video/QuantumWorld.png
     * lectureurl : /assets/lecture/QuantumWorld.pdf
     * title : 1.1 量子世界
     * url : Basic/BasicKnowledgeQuantumMechanics.mp4
     * videoUrl : http://video.qpanda.cn:65520/
     * vid : 11
     * view : 1187
     */

    private int block;
    private String brief;
    private int free;
    private String img;
    private String lectureurl;
    private String title;
    private String url;
    private String videoUrl;
    private int vid;
    private int view;

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLectureurl() {
        return lectureurl;
    }

    public void setLectureurl(String lectureurl) {
        this.lectureurl = lectureurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

     private List<QuestDate>  examInfoList;


    public List<QuestDate> getExamInfoList() {
        return examInfoList;
    }

    public void setExamInfoList(List<QuestDate> examInfoList) {
        this.examInfoList = examInfoList;
    }
}
