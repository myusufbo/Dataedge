package com.yusuf.iit.du.lms;

/**
 * Created by Administrator on 5/26/2015.
 */
public class LeaveTypeCaller extends Thread {
    public LeaveTypeCallSoap leaveTypeCallSoap;
    public String UserId,Password,Userdomain
            ;

    public void run(){
        try{
            leaveTypeCallSoap=new LeaveTypeCallSoap();
            String res=leaveTypeCallSoap.Call(UserId);
            MainActivity.leaveRslt=res;
        }
        catch(Exception e){
            MainActivity.leaveRslt=e.toString();
        }
    }
}
