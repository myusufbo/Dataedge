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


public class MainActivity extends ActionBarActivity {

    public static String rslt=null;
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

//        String username= edtUserName.getText().toString();
//        String password=edtPassword.getText().toString();
//        String dept =deptSpinner.toString();
        final AlertDialog ad= new AlertDialog.Builder(MainActivity.this).create();
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity( new Intent(getBaseContext(),Home.class));

                try{
                    String username= edtUserName.getText().toString();
                    String password=edtPassword.getText().toString();
                    String dept = deptSpinner.getSelectedItem().toString();
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


//                    ad.setMessage(rslt);
                }
                catch (Exception ex){
                    ad.setMessage("ERROR");
                }
                int m= Integer.valueOf(rslt);
                if(m==0){
                    startActivity(new Intent(MainActivity.this,Home.class));
                }
                else{
                    ad.setMessage("Error");
                }
                //ad.show();

            }
        });

         //finish();
        onPause();

    }

    private void login() {


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
}
