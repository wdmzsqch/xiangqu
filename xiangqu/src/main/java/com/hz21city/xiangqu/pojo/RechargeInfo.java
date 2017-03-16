package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class RechargeInfo {
    private Long id;

    private Long toRechargeId;//充值对像

    private Long fromRechargeId;//管理员id

    private Integer rechargeMoney;//充值次数

    private Date rechargeTime;//充值时间
    
    private Integer checkStatusC;//财务审核
    
    private Integer checkStatusB;//最张审核
    
    private String comment;//备注
    private String comment1;
    private Integer isCheck;//0未审 1已审
    
    private String from_name;
    
    private String to_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToRechargeId() {
        return toRechargeId;
    }

    public void setToRechargeId(Long toRechargeId) {
        this.toRechargeId = toRechargeId;
    }

    public Long getFromRechargeId() {
        return fromRechargeId;
    }

    public void setFromRechargeId(Long fromRechargeId) {
        this.fromRechargeId = fromRechargeId;
    }

    public Integer getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(Integer rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

	public Integer getCheckStatusC() {
		return checkStatusC;
	}

	public void setCheckStatusC(Integer checkStatusC) {
		this.checkStatusC = checkStatusC;
	}

	public Integer getCheckStatusB() {
		return checkStatusB;
	}

	public void setCheckStatusB(Integer checkStatusB) {
		this.checkStatusB = checkStatusB;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
}