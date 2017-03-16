package com.hz21city.xiangqu.pojo;

public class ChannelInfo {

	private Long id;

    private String channelName;

    private String channelCode;
    
    private String channelpic;
    
    private int sellcount;
    
    private int yestoday_sellcount;
    
    private int lookcount;
    
    private int yestody_lookcount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

	public String getChannelpic() {
		return channelpic;
	}

	public void setChannelpic(String channelpic) {
		this.channelpic = channelpic;
	}

	public int getSellcount() {
		return sellcount;
	}

	public void setSellcount(int sellcount) {
		this.sellcount = sellcount;
	}

	public int getYestoday_sellcount() {
		return yestoday_sellcount;
	}

	public void setYestoday_sellcount(int yestoday_sellcount) {
		this.yestoday_sellcount = yestoday_sellcount;
	}

	public int getLookcount() {
		return lookcount;
	}

	public void setLookcount(int lookcount) {
		this.lookcount = lookcount;
	}

	public int getYestody_lookcount() {
		return yestody_lookcount;
	}

	public void setYestody_lookcount(int yestody_lookcount) {
		this.yestody_lookcount = yestody_lookcount;
	}

}