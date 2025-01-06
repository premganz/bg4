package org.spo.cms4.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QMessage {
	private String repo;
	private String handler;
	private String content;
	private String meta;
	
	public String getMeta() {
		return meta;
	}
	@XmlElement
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getHandler() {
		return handler;
	}
	@XmlElement
	public void setHandler(String handler) {
		this.handler = handler;
	}
	
	
	
	public String getContent() {
		return content;
	}
	
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}
	public String getRepo() {
		return repo;
	}
	@XmlElement
	public void setRepo(String repo) {
		this.repo = repo;
	}

	
	
}
