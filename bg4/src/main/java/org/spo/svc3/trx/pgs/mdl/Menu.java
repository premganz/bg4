package org.spo.svc3.trx.pgs.mdl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Menu {

	private String id;
	private String nlId; //this is the id
	private String nl;
	private String lbl;
	private String levelCd;  //this is for nesting
	private boolean isClickable=true;
	private List<Menu> subMenuItems= new ArrayList<Menu>();
	
	
	//does, theme, visit
//	private String linkDescription;
//	private String linkMetaInfo;

	public boolean isClickable() {
		return isClickable;
	}

	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevelCd() {
		return levelCd;
	}

	public void setLevelCd(String levelCd) {
		this.levelCd = levelCd;
	}



	public String getNlId() {
		return nlId;
	}

	public void setNlId(String nlId) {
		this.nlId = nlId;
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

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}
	public String getLbl() {
		return lbl;
	}

	public void setLbl(String lbl) {
		this.lbl = lbl;
	}

	
	
	
	

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(lbl +">"+'\n');
		//		System.out.println(label);
		for (Menu m1:subMenuItems) {
			buf.append(" "+m1.getLbl()+">"+'\n');
			if(!m1.subMenuItems.isEmpty()) {
				for (Menu m2:m1.subMenuItems) {
					buf.append("     "+m2.getLbl()+">"+'\n');	
					if(!m2.subMenuItems.isEmpty()) {
						for (Menu m3:m2.subMenuItems) {
							buf.append("         *"+m3.getLbl()+">"+'\n');	
						}

					}
				}


			}

		}

		return buf.toString();
	}

	public String asJson() {
		StringBuffer buf = new StringBuffer();
		buf.append(lbl +">"+'\n');
		//		System.out.println(label);
		for (Menu m1:subMenuItems) {
			buf.append(" "+m1.getLbl()+"{"+'\n');
			if(!m1.subMenuItems.isEmpty()) {
				for (Menu m2:m1.subMenuItems) {
					buf.append("     "+m2.getLbl()+">"+'\n');	
					if(!m2.subMenuItems.isEmpty()) {
						for (Menu m3:m2.subMenuItems) {
							buf.append("         *"+m3.getLbl()+">"+'\n');	
						}

					}
				}


			}

		}

		return buf.toString();
	}


}
