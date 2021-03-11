package org.spo.svc3.trx.pgs.t03.cmd;

public class Usr_Menu {

	private USR_MENU_LST[] USR_MENU_LST;

		public USR_MENU_LST[] getUSR_MENU_LST ()
		{
			return USR_MENU_LST;
		}

		public void setUSR_MENU_LST (USR_MENU_LST[] USR_MENU_LST)
		{
			this.USR_MENU_LST = USR_MENU_LST;
		}

		@Override
		public String toString()
		{
			return "ClassPojo [USR_MENU_LST = "+USR_MENU_LST+"]";
		}
	}



class USR_MENU_LST
{
	private String menu_id;

	private String menu_disp_nm;

	private String menu_ev;

	private String menu_act;

	public String getMenu_id ()
	{
		return menu_id;
	}

	public void setMenu_id (String menu_id)
	{
		this.menu_id = menu_id;
	}

	public String getMenu_disp_nm ()
	{
		return menu_disp_nm;
	}

	public void setMenu_disp_nm (String menu_disp_nm)
	{
		this.menu_disp_nm = menu_disp_nm;
	}

	public String getMenu_ev ()
	{
		return menu_ev;
	}

	public void setMenu_ev (String menu_ev)
	{
		this.menu_ev = menu_ev;
	}

	public String getMenu_act ()
	{
		return menu_act;
	}

	public void setMenu_act (String menu_act)
	{
		this.menu_act = menu_act;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [menu_id = "+menu_id+", menu_disp_nm = "+menu_disp_nm+", menu_ev = "+menu_ev+", menu_act = "+menu_act+"]";
	}
}
