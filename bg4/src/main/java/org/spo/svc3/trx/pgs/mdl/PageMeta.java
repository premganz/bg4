package org.spo.svc3.trx.pgs.mdl;

import java.util.List;

public class PageMeta {
	private List<String> photoStrip;
	private List<NewsItem> newsItems;



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
