package org.spo.svc3.trx.pgs.mdl;

public class ActionPage {
	
	private String mainContentId;
	private boolean showMoreInfoButton;
	private Menu queryLinks;
	private Menu newsLinks;
	
	public String getMainContentId() {
		return mainContentId;
	}
	public void setMainContentId(String mainContentId) {
		this.mainContentId = mainContentId;
	}
	public boolean isShowMoreInfoButton() {
		return showMoreInfoButton;
	}
	public void setShowMoreInfoButton(boolean showMoreInfoButton) {
		this.showMoreInfoButton = showMoreInfoButton;
	}
	public Menu getQueryLinks() {
		return queryLinks;
	}
	public void setQueryLinks(Menu queryLinks) {
		this.queryLinks = queryLinks;
	}
	public Menu getNewsLinks() {
		return newsLinks;
	}
	public void setNewsLinks(Menu newsLinks) {
		this.newsLinks = newsLinks;
	}
	
	
	
	
	

}
