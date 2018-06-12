package org.spo.svc3.trx.pgs.t01.cmd;

public class Home_pg
{
private MENU_LINKS_CONT_ZN[] MENU_LINKS_CONT_ZN;

private MENU_LINKS_FORM_FLW_ZN[] MENU_LINKS_FORM_FLW_ZN;

private String about_link_ref;

private String home_link_ref;

private String list_pagination_ic;

public MENU_LINKS_CONT_ZN[] getMENU_LINKS_CONT_ZN ()
{
return MENU_LINKS_CONT_ZN;
}

public void setMENU_LINKS_CONT_ZN (MENU_LINKS_CONT_ZN[] MENU_LINKS_CONT_ZN)
{
this.MENU_LINKS_CONT_ZN = MENU_LINKS_CONT_ZN;
}

public MENU_LINKS_FORM_FLW_ZN[] getMENU_LINKS_FORM_FLW_ZN ()
{
return MENU_LINKS_FORM_FLW_ZN;
}

public void setMENU_LINKS_FORM_FLW_ZN (MENU_LINKS_FORM_FLW_ZN[] MENU_LINKS_FORM_FLW_ZN)
{
this.MENU_LINKS_FORM_FLW_ZN = MENU_LINKS_FORM_FLW_ZN;
}

public String getAbout_link_ref ()
{
return about_link_ref;
}

public void setAbout_link_ref (String about_link_ref)
{
this.about_link_ref = about_link_ref;
}

public String getHome_link_ref ()
{
return home_link_ref;
}

public void setHome_link_ref (String home_link_ref)
{
this.home_link_ref = home_link_ref;
}

public String getList_pagination_ic ()
{
return list_pagination_ic;
}

public void setList_pagination_ic (String list_pagination_ic)
{
this.list_pagination_ic = list_pagination_ic;
}

@Override
public String toString()
{
return "ClassPojo [MENU_LINKS_CONT_ZN = "+MENU_LINKS_CONT_ZN+", MENU_LINKS_FORM_FLW_ZN = "+MENU_LINKS_FORM_FLW_ZN+", about_link_ref = "+about_link_ref+", home_link_ref = "+home_link_ref+", list_pagination_ic = "+list_pagination_ic+"]";
}
}

			
class MENU_LINKS_FORM_FLW_ZN
{
    private MENU_SUBLINKS_ZN[] MENU_SUBLINKS_ZN;

    private String link_title;

    public MENU_SUBLINKS_ZN[] getMENU_SUBLINKS_ZN ()
    {
        return MENU_SUBLINKS_ZN;
    }

    public void setMENU_SUBLINKS_ZN (MENU_SUBLINKS_ZN[] MENU_SUBLINKS_ZN)
    {
        this.MENU_SUBLINKS_ZN = MENU_SUBLINKS_ZN;
    }

    public String getLink_title ()
    {
        return link_title;
    }

    public void setLink_title (String link_title)
    {
        this.link_title = link_title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MENU_SUBLINKS_ZN = "+MENU_SUBLINKS_ZN+", link_title = "+link_title+"]";
    }
}
			
class MENU_LINKS_CONT_ZN
{
    private MENU_SUBLINKS_ZN[] MENU_SUBLINKS_ZN;

    private String link_title;

    public MENU_SUBLINKS_ZN[] getMENU_SUBLINKS_ZN ()
    {
        return MENU_SUBLINKS_ZN;
    }

    public void setMENU_SUBLINKS_ZN (MENU_SUBLINKS_ZN[] MENU_SUBLINKS_ZN)
    {
        this.MENU_SUBLINKS_ZN = MENU_SUBLINKS_ZN;
    }

    public String getLink_title ()
    {
        return link_title;
    }

    public void setLink_title (String link_title)
    {
        this.link_title = link_title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MENU_SUBLINKS_ZN = "+MENU_SUBLINKS_ZN+", link_title = "+link_title+"]";
    }
}
			
			


class MENU_SUBLINKS_ZN
{
    private String link_href;

    private String link_title;

    private String link_id;

    public String getLink_href ()
    {
        return link_href;
    }

    public void setLink_href (String link_href)
    {
        this.link_href = link_href;
    }

    public String getLink_title ()
    {
        return link_title;
    }

    public void setLink_title (String link_title)
    {
        this.link_title = link_title;
    }

    public String getLink_id ()
    {
        return link_id;
    }

    public void setLink_id (String link_id)
    {
        this.link_id = link_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [link_href = "+link_href+", link_title = "+link_title+", link_id = "+link_id+"]";
    }
}
			
			