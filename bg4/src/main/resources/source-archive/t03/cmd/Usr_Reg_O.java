package org.spo.svc3.trx.pgs.t03.cmd;
public class Usr_Reg_O{
	 private String usr_id;

	    private String usr_nm;

	    private String usr_pw;

	    private String usr_msg;

	    public String getUsr_id ()
	    {
	        return usr_id;
	    }

	    public void setUsr_id (String usr_id)
	    {
	        this.usr_id = usr_id;
	    }

	    public String getUsr_nm ()
	    {
	        return usr_nm;
	    }

	    public void setUsr_nm (String usr_nm)
	    {
	        this.usr_nm = usr_nm;
	    }

	    public String getUsr_pw ()
	    {
	        return usr_pw;
	    }

	    public void setUsr_pw (String usr_pw)
	    {
	        this.usr_pw = usr_pw;
	    }

	    public String getUsr_msg ()
	    {
	        return usr_msg;
	    }

	    public void setUsr_msg (String usr_msg)
	    {
	        this.usr_msg = usr_msg;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [usr_id = "+usr_id+", usr_nm = "+usr_nm+", usr_pw = "+usr_pw+", usr_msg = "+usr_msg+"]";
	    }
}
