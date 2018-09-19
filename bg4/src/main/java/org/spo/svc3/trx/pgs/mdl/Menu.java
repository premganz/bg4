package org.spo.svc3.trx.pgs.mdl;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String id;
	private String dataId;
	private String label;
	private String levelCd;
	
	//does, theme, visit
	private String linkDescription;
	private String linkMetaInfo;
	
	private List<Menu> subMenuItems= new ArrayList<Menu>();
	
	public String getLinkDescription() {
		return linkDescription;
	}

	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	public String getLinkMetaInfo() {
		return linkMetaInfo;
	}

	public void setLinkMetaInfo(String linkMetaInfo) {
		this.linkMetaInfo = linkMetaInfo;
	}

	
	
	
		

	

	public String getLevelCd() {
		return levelCd;
	}

	public void setLevelCd(String levelCd) {
		this.levelCd = levelCd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Menu> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<Menu> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
	
	
	
	
	
//	@Override
//	public String toString() {
//		StringBuffer buf = new StringBuffer();
//		buf.append(label +">"+'\n');
////		System.out.println(label);
//		if(!subMenuItems.isEmpty()) {
//			for (Menu m1:subMenuItems) {
//				buf.append('\t'+m1.toString()+">"+'\n');
//			}
//		}
//		return buf.toString();
//	}
//	
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(label +">"+'\n');
		//		System.out.println(label);
		for (Menu m1:subMenuItems) {
			buf.append(" "+m1.getLabel()+">"+'\n');
			if(!m1.subMenuItems.isEmpty()) {
				for (Menu m2:m1.subMenuItems) {
					buf.append("     "+m2.getLabel()+">"+'\n');	
					if(!m2.subMenuItems.isEmpty()) {
						for (Menu m3:m2.subMenuItems) {
							buf.append("         *"+m3.getLabel()+">"+'\n');	
						}
						
					}
				}

				
			}

		}

		return buf.toString();
	}
	
	public String asJson() {
		StringBuffer buf = new StringBuffer();
		buf.append(label +">"+'\n');
		//		System.out.println(label);
		for (Menu m1:subMenuItems) {
			buf.append(" "+m1.getLabel()+"{"+'\n');
			if(!m1.subMenuItems.isEmpty()) {
				for (Menu m2:m1.subMenuItems) {
					buf.append("     "+m2.getLabel()+">"+'\n');	
					if(!m2.subMenuItems.isEmpty()) {
						for (Menu m3:m2.subMenuItems) {
							buf.append("         *"+m3.getLabel()+">"+'\n');	
						}
						
					}
				}

				
			}

		}

		return buf.toString();
	}
	
	
}
