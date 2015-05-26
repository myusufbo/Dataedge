package com.yusuf.iit.du.lms;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends ActionBarActivity {
//    public static String username=null;
//    public static String password=null;
//    public static String dept=null;
    public static String employeeId;
    public static String employeeName;
    public  static String deptId;
    public static String rslt=null;
    public static String employeeRslt=null;
    public static String leaveRslt=null;
    public static ArrayList<String> leaveType= new ArrayList<String>();
    public static ArrayList<String> relieverName= new ArrayList<String>();
    public static String relieverRslt="";
    EditText edtUserName,edtPassword;
    Button btnSignIn;
    Spinner deptSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserName=(EditText)findViewById(R.id.userName);
        edtPassword=(EditText)findViewById(R.id.password);
        deptSpinner=(Spinner)findViewById(R.id.deptSpinner);
        ArrayAdapter<CharSequence> deptAdapter;



        deptAdapter=ArrayAdapter.createFromResource(MainActivity.this,R.array.deptArray,android.R.layout.simple_spinner_item);
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deptSpinner.setAdapter(deptAdapter);

//        username= edtUserName.getText().toString();
//        password=edtPassword.getText().toString();
//        dept = deptSpinner.getSelectedItem().toString();


        final AlertDialog ad= new AlertDialog.Builder(MainActivity.this).create();
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity( new Intent(getBaseContext(),Home.class));

                try{
                    String username= edtUserName.getText().toString();
                    String password=edtPassword.getText().toString();
                    String dept =deptSpinner.getSelectedItem().toString();
//                    Log.e(dept,"sf");
//                    Log.e(username,"sf");
//                    Log.e(password,"sf");
                    rslt="START";
                    Caller c= new Caller();
                    c.Username=username;
                    c.Password=password;
                    c.Userdomain=dept;
                    c.join();c.start();
                    while (rslt=="START"){
                        try
                        {
                            Thread.sleep(10);
                        }
                        catch (Exception ezx){
                            ezx.toString();
                        }
                    }


                    ad.setMessage(rslt);
                }
                catch (Exception ex){
                    ad.setMessage("ERROR");
                }

                relieverName.clear();
                leaveType.clear();
                employee();
                leaveType();
                relieverName();


                login();



            }
        });

         //finish();
//        onPause();

    }

    private void relieverName() {
        relieverRslt="START";
        try{
            relieverRslt="START";
            RelieveNameCaller relieveNameCaller= new RelieveNameCaller();
            relieveNameCaller.DeptId=deptId;
//                    c.Password=password;
//                   c.Userdomain=domain;
            relieveNameCaller.join();relieveNameCaller.start();
            while (relieverRslt=="START"){
                try
                {
                    Thread.sleep(10);
                }
                catch (Exception ezx){
                    ezx.toString();
                }
            }
            //ad.setMessage(rslt);
        }
        catch (Exception ex){
            //ad.setMessage("ERROR");
        }
        //ad.show();

        try {
//                    InputStream is = getAssets().open(rslt);
            InputStream is = new ByteArrayInputStream(relieverRslt.getBytes("UTF-8"));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("Releaver");
            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;

//                            tv1.setText(tv1.getText() + "\nName : " + getValue("LeaveTypeName", element2) + "\n");
////                            tv1.setText(tv1.getText()+"Salary : " + getValue("EmployeeName", element2)+"\n");
//                            tv1.setText(tv1.getText()+"-----------------------");

//                            leaveType=getValue("LeaveTypeName", element2);
                    relieverName.add(getValue("EmployeeName", element2).toString());
                    //listType.setAdapter(adapter);
//                            String a= getValue("EmployeeId", element2).toString();
//                            tv1.setText(a);



                }
            }//end of for loop

        } catch (Exception e) {e.printStackTrace();}


    }

    private void leaveType() {
        String userName=edtUserName.getText().toString();
        String userId= "DEFSSLPO\\"+userName;

        leaveRslt="START";
        try{
            leaveRslt="START";
            LeaveTypeCaller leaveTypeCaller= new LeaveTypeCaller();
            leaveTypeCaller.UserId=userId;
//                    c.Password=password;
//                   c.Userdomain=domain;
            leaveTypeCaller.join();leaveTypeCaller.start();
            while (leaveRslt=="START"){
                try
                {
                    Thread.sleep(10);
                }
                catch (Exception ezx){
                    ezx.toString();
                }
            }
            //ad.setMessage(rslt);
        }
        catch (Exception ex){
            //ad.setMessage("ERROR");
        }
        //ad.show();

        try {
//                    InputStream is = getAssets().open(rslt);
            InputStream is = new ByteArrayInputStream(leaveRslt.getBytes("UTF-8"));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("LeaveType");
            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;

//                            tv1.setText(tv1.getText() + "\nName : " + getValue("LeaveTypeName", element2) + "\n");
////                            tv1.setText(tv1.getText()+"Salary : " + getValue("EmployeeName", element2)+"\n");
//                            tv1.setText(tv1.getText()+"-----------------------");

//                            leaveType=getValue("LeaveTypeName", element2);
                    leaveType.add(getValue("LeaveTypeName", element2).toString());
                    //listType.setAdapter(adapter);
//                            String a= getValue("EmployeeId", element2).toString();
//                            tv1.setText(a);



                }
            }//end of for loop

        } catch (Exception e) {e.printStackTrace();}

    }


    private void login() {

        int m= Integer.valueOf(rslt);
        if(m==0){
            startActivity(new Intent(MainActivity.this,Home.class));
        }
        else{
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
        }

    }

    private void employee(){
        try{
            String userName=edtUserName.getText().toString();
            String userId= "DEFSSLPO\\"+userName;
            //Log.e(userId,"sf");
            employeeRslt="START";
            EmployeeCaller c= new EmployeeCaller();

            c.UserId=userId;
            c.join();c.start();
            while (employeeRslt=="START"){
                try
                {
                    Thread.sleep(10);
                }
                catch (Exception ezx){
                    ezx.toString();
                }
            }

//            ad.setMessage(employeeRslt);
//                    ad.setMessage(rslt);
            Log.e(employeeRslt,"rslt");
            try {
//                    InputStream is = getAssets().open(rslt);
                InputStream is = new ByteArrayInputStream(employeeRslt.getBytes("UTF-8"));
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(is);

                Element element=doc.getDocumentElement();
                element.normalize();

                NodeList nList = doc.getElementsByTagName("EmployeeInfoResponse");
                for (int i=0; i<nList.getLength(); i++) {

                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;

                        employeeName= getValue("EmployeeName", element2).toString();
                        employeeId= getValue("EmployeeId", element2).toString();
                        deptId=getValue("DepartmentId",element2).toString();
                        // tv1.setText(tv1.getText() + "\nName : " + getValue("EmployeeId", element2) + "\n");
//                            Toast.makeText(getApplicationContext(),employeeName +"and"+ employeeId,Toast.LENGTH_LONG).show();
//                        Log.e(employeeName,"name");
//                        Log.e(employeeId,"Id");
                    }
                }//end of for loop
//                Toast.makeText(MainActivity.this, employeeName +"and"+ employeeId,Toast.LENGTH_LONG).show();

            } catch (Exception e) {e.printStackTrace();}
        }
        catch (Exception ex){
            //ad.setMessage("ERROR");
            ex.toString();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private  String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
