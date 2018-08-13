package org.spo.svc3.trx.pgs.mdl;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String id;
	private String href;
	private String label;
	
	private List<Menu> subMenuItems= new ArrayList<Menu>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
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
	
	
}
