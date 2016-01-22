package com.myplace.dto;

public class PushMessage {
	
	//notification type like 1- business created,2-event ,3- build upgrade etc
	int type;
	String title;
	String description;
	long id;
	String clkurl;
	String imgurl;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
