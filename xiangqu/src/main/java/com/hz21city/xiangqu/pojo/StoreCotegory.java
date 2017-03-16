package com.hz21city.xiangqu.pojo;

public class StoreCotegory {
    private Long id;

    private String cotegoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCotegoryName() {
        return cotegoryName;
    }

    public void setCotegoryName(String cotegoryName) {
        this.cotegoryName = cotegoryName == null ? null : cotegoryName.trim();
    }
}