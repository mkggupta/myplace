package com.myplace.dto;

public class NotificationMessage extends PushMessage{

	long userId;
	long notifId;
	int status ;
	String createDate;
	String delUrl;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
