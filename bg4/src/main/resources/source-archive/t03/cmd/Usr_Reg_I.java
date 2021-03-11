package org.spo.svc3.trx.pgs.t03.cmd;
public class Usr_Reg_I{

    private String usr_mdf_pw;

    private String usr_id;

    private String usr_city;

    private String usr_nm;

    public String getUsr_mdf_pw ()
    {
        return usr_mdf_pw;
    }

    public void setUsr_mdf_pw (String usr_mdf_pw)
    {
        this.usr_mdf_pw = usr_mdf_pw;
    }

    public String getUsr_id ()
    {
        return usr_id;
    }

    public void setUsr_id (String usr_id)
    {
        this.usr_id = usr_id;
    }

    public String getUsr_city ()
    {
        return usr_city;
    }

    public void setUsr_city (String usr_city)
    {
        this.usr_city = usr_city;
    }

    public String getUsr_nm ()
    {
        return usr_nm;
    }

    public void setUsr_nm (String usr_nm)
    {
        this.usr_nm = usr_nm;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [usr_mdf_pw = "+usr_mdf_pw+", usr_id = "+usr_id+", usr_city = "+usr_city+", usr_nm = "+usr_nm+"]";
    }
}