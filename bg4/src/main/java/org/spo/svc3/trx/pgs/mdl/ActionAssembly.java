package org.spo.svc3.trx.pgs.mdl;

public class ActionAssembly {
	
	private String doesCode;
	private String domainCode;
	private String actionCode;
	
	
	private boolean showMoreInfoButton;
	private Menu promotedLinks;
	private Menu sampleEssayLink;
	private Menu newsLinks;
	
	
	public ActionAssembly setCodes(String doesCode, String domainCode, String actionCode) {
		this.setActionCode(actionCode);;
		this.setDomainCode(domainCode);
		this.setDoesCode(doesCode);
		return this;
	}
	
	
	
	
	public String getDoesCode() {
		return doesCode;
	}
	public void setDoesCode(String doesCode) {
		this.doesCode = doesCode;
	}
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
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
