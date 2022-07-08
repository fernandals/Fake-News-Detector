package br.ufrn.imd.model;

import java.util.Date;

public class News {
	private int id;
	private String originalContent;
	private String processedContent;
	private String link;
	private Date timestamp;
	
	public News() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginalContent() {
		return originalContent;
	}
	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}
	public String getProcessedContent() {
		return processedContent;
	}
	public void setProcessedContent(String processedContent) {
		this.processedContent = processedContent;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp2) {
		this.timestamp = timestamp2;
	}
	
	
}
