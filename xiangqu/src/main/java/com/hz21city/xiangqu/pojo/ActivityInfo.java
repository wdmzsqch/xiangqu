package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class ActivityInfo {

	private Long id;

    private String title; //标题

    private Date acitvityTime;//活动时间

    private Date addTime;//添加时间

    private String nextIntro;//下期预先

    private String content;//活动详情

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getAcitvityTime() {
        return acitvityTime;
    }

    public void setAcitvityTime(Date acitvityTime) {
        this.acitvityTime = acitvityTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getNextIntro() {
        return nextIntro;
    }

    public void setNextIntro(String nextIntro) {
        this.nextIntro = nextIntro == null ? null : nextIntro.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}