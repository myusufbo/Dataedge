package com.yusuf.iit.du.lms;

/**
 * Created by Administrator on 5/26/2015.
 */
public class RelieveNameCaller extends Thread {

    public RelieveNameCallSoap relieveNameCallSoap;
    public String DeptId;
    public void run(){
        try{
            relieveNameCallSoap=new RelieveNameCallSoap();
            String res=relieveNameCallSoap.Call(DeptId);
            MainActivity.relieverRslt=res;
        }
        catch(Exception e){
            MainActivity.relieverRslt=e.toString();
        }
    }
}
