package ie.gmit.sw.ds;

import java.net.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import java.io.*;

public class Client {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String getOrderAsXML = "xml";
		String getOrderAsJSON = "json";
		
		try{
			JAXBContext jc = JAXBContext.newInstance("ie.gmit.sw.ds");
			
			//Unmarshal the XML into a PurchaseOrder Object
			Object response1 = getResponse(getOrderAsXML);
			StringReader sr1 = new StringReader((String) response1);
			Unmarshaller um1 = jc.createUnmarshaller();
			StreamSource source1 = new StreamSource(sr1);
			JAXBElement<PurchaseOrder> poElement1 = um1.unmarshal(source1, PurchaseOrder.class);
			PurchaseOrder poFromXml = (PurchaseOrder) poElement1.getValue();
			System.out.println("\n\n######### XML Unmarshalling #########\n" + poFromXml.getOrderNumber());
			
			//Unmarshal the JSON into a PurchaseOrder Object
			Object response2 = getResponse(getOrderAsJSON);
			StringReader sr2 = new StringReader((String) response2);
			Unmarshaller um2 = jc.createUnmarshaller();
			um2.setProperty("eclipselink.media-type", "application/json");
			um2.setProperty("eclipselink.json.include-root", false);
			StreamSource source2 = new StreamSource(sr2);
			JAXBElement<PurchaseOrder> poElement2 = um2.unmarshal(source2, PurchaseOrder.class);
			PurchaseOrder poFromJson = (PurchaseOrder) poElement2.getValue();
			System.out.println("\n\n######### JSON Unmarshalling #########\n" + poFromJson.getOrderNumber());
			
						
		}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	public static Object getResponse(String request) throws Exception{
		Socket s = new Socket("127.0.0.1", 9999);
		ObjectOutputStream output= new ObjectOutputStream(s.getOutputStream());
		output.writeObject(request);
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		Object response =  in.readObject();		
		s.close();
		return response;
	}
	
}
