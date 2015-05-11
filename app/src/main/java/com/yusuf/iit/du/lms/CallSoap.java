package com.yusuf.iit.du.lms;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 4/27/2015.
 */
@SuppressWarnings("ALL")
public class CallSoap {
    public final String SOAP_ACTION="http://tempuri.org/ValidateADUser";

    public  final String OPERATION_NAME = "ValidateADUser";

    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    public  final String SOAP_ADDRESS = "http://192.168.2.116/LAPSAdminWebService/SecAdminService.asmx";

    public CallSoap (){

    }
    public String Call(String Userdomain,String Username, String Password){
        SoapObject request= new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        PropertyInfo pi= new PropertyInfo();
//        pi.setName("user");
////         pi.setValue("ofaruk");
//        pi.setType(Object.class);
//        request.addProperty(pi);
        //username
        pi= new PropertyInfo();
        pi.setName("Userdomain");
        pi.setValue(Userdomain);
        pi.setType(String.class);
        request.addProperty(pi);


//        //password
        pi= new PropertyInfo();
        pi.setName("Username");
        pi.setValue(Username);
        pi.setType(String.class);
        request.addProperty(pi);
        //domainname
        pi= new PropertyInfo();
        pi.setName("Password");
        pi.setValue(Password);
        pi.setType(String.class);
        request.addProperty(pi);

//
//        pi = new PropertyInfo();
//        pi.setName("_valTxt");
//        pi.setValue("!data@edge#limited$");
//        pi.setType(String.class);
//        request.addProperty(pi);
//
//        pi=new PropertyInfo();
//        pi.setN
        SoapSerializationEnvelope envelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;

        envelope.setOutputSoapObject(request);
        envelope.encodingStyle=SoapSerializationEnvelope.XSD1999;
        HttpTransportSE httpTransportSE= new HttpTransportSE(SOAP_ADDRESS);
        httpTransportSE.debug=true;
        Object response=null;

        try{
            httpTransportSE.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
            //envelope.getResponse();
//            envelope.getResult();
//            response=envelope.getResponse();
        }
        catch (Exception e){
            e.printStackTrace();


        }
//        String result= httpTransportSE.responseDump;
//        return result;
            return  response.toString();


    }


}
