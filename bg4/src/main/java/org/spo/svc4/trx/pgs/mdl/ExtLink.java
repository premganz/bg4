package org.spo.svc4.trx.pgs.mdl;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExtLink {

	private String href;
	private String metaInfo;
	private String label;
	private boolean showIc;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
	private Date date;
	
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getMetaInfo() {
		return metaInfo;
	}
	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isShowIc() {
		return showIc;
	}
	public void setShowIc(boolean showIc) {
		this.showIc = showIc;
	}
	
	
	
	
	
	
}
