package org.spo.svc3.trx.pgs.mdl;

public class ActionAssembly {
	
	private String majorCode;
	private String minorCode;
	private String actionCode;
	
	
	private boolean showMoreInfoButton;
	private Menu promotedLinks;
	private Menu sampleEssayLink;
	private Menu newsLinks;
	
	
	public ActionAssembly setCodes(String majorCode, String minorCode, String actionCode) {
		this.setActionCode(actionCode);;
		this.setMajorCode(majorCode);
		this.setMinorCode(minorCode);
		return this;
	}
	
	
	
	
	
	public String getMajorCode() {
		return majorCode;
	}





	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}





	public String getMinorCode() {
		return minorCode;
	}





	public void setMinorCode(String minorCode) {
		this.minorCode = minorCode;
	}





	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public boolean isShowMoreInfoButton() {
		return showMoreInfoButton;
	}
	public void setShowMoreInfoButton(boolean showMoreInfoButton) {
		this.showMoreInfoButton = showMoreInfoButton;
	}
	
		
	public Menu getPromotedLinks() {
		return promotedLinks;
	}
	public void setPromotedLinks(Menu promotedLinks) {
		this.promotedLinks = promotedLinks;
	}
	public Menu getSampleEssayLink() {
		return sampleEssayLink;
	}
	public void setSampleEssayLink(Menu sampleEssayLink) {
		this.sampleEssayLink = sampleEssayLink;
	}
	public Menu getNewsLinks() {
		return newsLinks;
	}
	public void setNewsLinks(Menu newsLinks) {
		this.newsLinks = newsLinks;
	}
	
	
	
	
	

}
