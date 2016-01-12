package com.myplace.dto;

public class DefaultFileInfo {
	private String encodeType;
	private String mimeType = null;
	private String filePath;
	private String fileExt;
	private Integer fileSize;
	private String fileId;
	private String fileName;
	private String origFName;
	private String mediaType = "image";
	private int id;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getEncodeType() {
		return encodeType;
	}
	public void setEncodeType(String encodeType) {
		this.encodeType = encodeType;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOrigFName() {
		return origFName;
	}
	public void setOrigFName(String origFName) {
		this.origFName = origFName;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
}
