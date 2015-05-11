package com.yusuf.iit.du.lms;

import android.app.AlertDialog;

/**
 * Created by Administrator on 4/27/2015.
 */
public class Caller extends Thread {
    public CallSoap cs;

    //protected AlertDialog ad;
    public String Username,Password,Userdomain
            ;
    public int RetResult;

    public void run(){
        try{
            cs=new CallSoap();
            String res=cs.Call(Userdomain,Username,Password);
            MainActivity.rslt=res;
        }
        catch(Exception e){
            MainActivity.rslt=e.toString();
        }
    }
}
