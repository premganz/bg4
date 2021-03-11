package org.spo.svc3.trx.pgs.t01.cmd;

               
public class Wel_msg
{
    private String pgn_lnk_txt;

    private String pvs_lnk_txt;

    private String sub_ttl;

    private String sh_msg;

    private String pgn_lnk_ic;

    private String pvs_lnk_href;

    private String long_msg;

    private MSG_LNK_TBL[] MSG_LNK_TBL;

    private String ttl;

    private MSG_META_INFO_TBL[] MSG_META_INFO_TBL;

    private String pgn_lnk_href;

    private String pvs_lnk_ic;

    public String getPgn_lnk_txt ()
    {
        return pgn_lnk_txt;
    }

    public void setPgn_lnk_txt (String pgn_lnk_txt)
    {
        this.pgn_lnk_txt = pgn_lnk_txt;
    }

    public String getPvs_lnk_txt ()
    {
        return pvs_lnk_txt;
    }

    public void setPvs_lnk_txt (String pvs_lnk_txt)
    {
        this.pvs_lnk_txt = pvs_lnk_txt;
    }

    public String getSub_ttl ()
    {
        return sub_ttl;
    }

    public void setSub_ttl (String sub_ttl)
    {
        this.sub_ttl = sub_ttl;
    }

    public String getSh_msg ()
    {
        return sh_msg;
    }

    public void setSh_msg (String sh_msg)
    {
        this.sh_msg = sh_msg;
    }

    public String getPgn_lnk_ic ()
    {
        return pgn_lnk_ic;
    }

    public void setPgn_lnk_ic (String pgn_lnk_ic)
    {
        this.pgn_lnk_ic = pgn_lnk_ic;
    }

    public String getPvs_lnk_href ()
    {
        return pvs_lnk_href;
    }

    public void setPvs_lnk_href (String pvs_lnk_href)
    {
        this.pvs_lnk_href = pvs_lnk_href;
    }

    public String getLong_msg ()
    {
        return long_msg;
    }

    public void setLong_msg (String long_msg)
    {
        this.long_msg = long_msg;
    }

    public MSG_LNK_TBL[] getMSG_LNK_TBL ()
    {
        return MSG_LNK_TBL;
    }

    public void setMSG_LNK_TBL (MSG_LNK_TBL[] MSG_LNK_TBL)
    {
        this.MSG_LNK_TBL = MSG_LNK_TBL;
    }

    public String getTtl ()
    {
        return ttl;
    }

    public void setTtl (String ttl)
    {
        this.ttl = ttl;
    }

    public MSG_META_INFO_TBL[] getMSG_META_INFO_TBL ()
    {
        return MSG_META_INFO_TBL;
    }

    public void setMSG_META_INFO_TBL (MSG_META_INFO_TBL[] MSG_META_INFO_TBL)
    {
        this.MSG_META_INFO_TBL = MSG_META_INFO_TBL;
    }

    public String getPgn_lnk_href ()
    {
        return pgn_lnk_href;
    }

    public void setPgn_lnk_href (String pgn_lnk_href)
    {
        this.pgn_lnk_href = pgn_lnk_href;
    }

    public String getPvs_lnk_ic ()
    {
        return pvs_lnk_ic;
    }

    public void setPvs_lnk_ic (String pvs_lnk_ic)
    {
        this.pvs_lnk_ic = pvs_lnk_ic;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pgn_lnk_txt = "+pgn_lnk_txt+", pvs_lnk_txt = "+pvs_lnk_txt+", sub_ttl = "+sub_ttl+", sh_msg = "+sh_msg+", pgn_lnk_ic = "+pgn_lnk_ic+", pvs_lnk_href = "+pvs_lnk_href+", long_msg = "+long_msg+", MSG_LNK_TBL = "+MSG_LNK_TBL+", ttl = "+ttl+", MSG_META_INFO_TBL = "+MSG_META_INFO_TBL+", pgn_lnk_href = "+pgn_lnk_href+", pvs_lnk_ic = "+pvs_lnk_ic+"]";
    }
}
			
			
	class MSG_LNK_TBL
{
    private String desc;

    private String href;

    private String txt;

    public String getDesc ()
    {
        return desc;
    }

    public void setDesc (String desc)
    {
        this.desc = desc;
    }

    public String getHref ()
    {
        return href;
    }

    public void setHref (String href)
    {
        this.href = href;
    }

    public String getTxt ()
    {
        return txt;
    }

    public void setTxt (String txt)
    {
        this.txt = txt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [desc = "+desc+", href = "+href+", txt = "+txt+"]";
    }
}
		class MSG_META_INFO_TBL
{
    private String author;

    private String date;

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [author = "+author+", date = "+date+"]";
    }
}
			
				
					
							