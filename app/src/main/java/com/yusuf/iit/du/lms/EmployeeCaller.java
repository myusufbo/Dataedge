package com.yusuf.iit.du.lms;

import android.app.AlertDialog;

/**
 * Created by Administrator on 5/14/2015.
 */
public class EmployeeCaller extends Thread {

    public EmployeeCallSoap employeeCallSoap;

    protected AlertDialog ad;
    public String UserId
            ;
    public int RetResult;

    public void run(){
        try{
            employeeCallSoap=new EmployeeCallSoap();
            String res=employeeCallSoap.EmployeeCall(UserId);
            MainActivity.employeeRslt=res;
        }
        catch(Exception e){
            MainActivity.employeeRslt=e.toString();
        }
    }
}
