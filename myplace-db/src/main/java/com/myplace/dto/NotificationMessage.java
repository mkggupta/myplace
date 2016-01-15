package com.myplace.dto;

public class NotificationMessage extends PushMessage{

	long userId;
	long notifId;
	int status ;
	String createDate;
	String delUrl;
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDelUrl() {
		return delUrl;
	}

	public void setDelUrl(String delUrl) {
		this.delUrl = delUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getNotifId() {
		return notifId;
	}

	public void setNotifId(long notifId) {
		this.notifId = notifId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClkurl() {
		return clkurl;
	}

	public void setClkurl(String clkurl) {
		this.clkurl = clkurl;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String toString() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(type);
        buffer.append("  \n");
        buffer.append(title);
        buffer.append("  \n");
        buffer.append(imgurl);
        buffer.append("  \n");
        buffer.append(clkurl);
        buffer.append("  \n");
        buffer.append(description);
        buffer.append("  \n");
        buffer.append(id);
        buffer.append("  \n");
        return buffer.toString();
    }


}
