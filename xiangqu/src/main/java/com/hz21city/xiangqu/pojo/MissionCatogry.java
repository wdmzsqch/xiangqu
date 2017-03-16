package com.hz21city.xiangqu.pojo;

/**
 * 任务类型
 * @author shenyj
 *
 */
public class MissionCatogry {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}