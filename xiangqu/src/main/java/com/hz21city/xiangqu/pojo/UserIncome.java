package com.hz21city.xiangqu.pojo;

public class UserIncome {
    private Long id;

    private Long userId;

    private Float dayIncome;

    private Float totalIncome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(Float dayIncome) {
        this.dayIncome = dayIncome;
    }

    public Float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Float totalIncome) {
        this.totalIncome = totalIncome;
    }
}