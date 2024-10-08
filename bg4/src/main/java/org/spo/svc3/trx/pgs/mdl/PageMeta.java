package org.spo.svc3.trx.pgs.mdl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PageMeta {
	private List<String> photoStrip;
	private List<NewsItem> newsItems;

	private List<ExtLink> linkedItems=new ArrayList<ExtLink>();


	public List<ExtLink> getLinkedItems() {
		if(!linkedItems.isEmpty()) {
			return linkedItems;
		}
		ExtLink ext = new ExtLink();
		ext.setHref("e://community.hortonworks.com/questions/146756/expression-language-for-concatenating.html");
		ext.setLabel("The Mullberry Tree August 10, 2018");
		ext.setMetaInfo(" Sourced from The website , www.leafycampus.org");
		ext.setDescription("Back to Home");
		ext.setDate(Calendar.getInstance().getTime());
		linkedItems.add(ext);

		return linkedItems;
	}


	public void setLinkedItems(List<ExtLink> linkedItems) {
		this.linkedItems = linkedItems;
	}


	public List<NewsItem> getNewsItems() {
		return newsItems;
	}

	public void setNewsItems(List<NewsItem> newsItems) {
		this.newsItems = newsItems;
	}

	public List<String> getPhotoStrip() {
		return photoStrip;
	}

	public void setPhotoStrip(List<String> photoStrip) {
		this.photoStrip = photoStrip;
	}




}
class NewsItem{
	private String link;
	private String text;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}



}
