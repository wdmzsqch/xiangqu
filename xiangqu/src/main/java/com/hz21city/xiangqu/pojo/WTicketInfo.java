package com.hz21city.xiangqu.pojo;

public class WTicketInfo {
	private Long id;
	
	private String ticketNum;//水票串号
	
	private Integer useState;//使用状态0 未使用 1已使用

	private Integer sellState;//出售状态0未出售 1已出售

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum == null ? null : ticketNum.trim();
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}

	public Integer getSellState() {
		return sellState;
	}

	public void setSellState(Integer sellState) {
		this.sellState = sellState;
	}
}