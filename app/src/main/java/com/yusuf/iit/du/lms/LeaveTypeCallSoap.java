package com.yusuf.iit.du.lms;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 5/26/2015.
 */
public class LeaveTypeCallSoap {

    public final String SOAP_ACTION="http://tempuri.org/GetLeaveType";

    public  final String OPERATION_NAME = "GetLeaveType";

    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    public  final String SOAP_ADDRESS = "http://192.168.2.116/LAPSAdminWebService/SecAdminService.asmx";
    public LeaveTypeCallSoap (){

    }

    public String Call(String UserId ) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);
        PropertyInfo pi = new PropertyInfo();

        pi.setName("UserId");
        pi.setValue(UserId);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        envelope.encodingStyle = SoapSerializationEnvelope.XSD1999;
        HttpTransportSE httpTransportSE = new HttpTransportSE(SOAP_ADDRESS);
//        AndroidHttpTransport androidHttpTransport= new AndroidHttpTransport(SOAP_ADDRESS);
        httpTransportSE.debug = true;
//        SoapObject response=null;
//
        Object response = null;
//        SoapObject resultRequestSOAP= null;
        try {
//
            httpTransportSE.call(SOAP_ACTION, envelope);
            response =  envelope.getResponse();

            //envelope.getResponse();
//            envelope.getResult();
//            response=envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();


        }
        String result= httpTransportSE.responseDump;
//        return result;
//        String r = response.getProperty(2).toString();
        return result;


    }
}
