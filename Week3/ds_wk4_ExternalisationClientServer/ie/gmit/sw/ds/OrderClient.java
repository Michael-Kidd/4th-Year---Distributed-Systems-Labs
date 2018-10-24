package ie.gmit.sw.ds;

import java.net.*;
import java.util.*;
import java.io.*;

public class OrderClient {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String allOrdersAsBytecodes = "bytecodes>>orders>>null";
		String getOrderAsBytecodes = "bytecodes>>getOrder>>AAA-123";
		String allOrdersAsXML = "xml>>orders>>null";
		String getOrderAsXML = "xml>>getOrder>>AAA-123";
		
		try{
			List<Order> orders = (List<Order>) getResponse(allOrdersAsBytecodes);
			System.out.println("------------ All Orders ----------------");
			for(Order o: orders){
				System.out.println(o.getOrderNumber() + " Date: " + o.getOrderDate() + " Line items: " + o.itemCount());
			}
			
			
			System.out.println("------------ Order AAA-123 ----------------");
			Order order = (Order) getResponse(getOrderAsBytecodes);
			System.out.println(order.getOrderNumber() + " Date: " + order.getOrderDate() + " Line items: " + order.itemCount());
			Item[] items = order.items();
			for (int i = 0; i < items.length; i++) {
				Item item = items[i];
				System.out.println("\tPart Number: " + item.getPartNumber());
				System.out.println("\tName: " + item.getPartName());
				System.out.println("\tQty: " + item.getQuantity());
				System.out.println("\tPrice: " + item.getPrice());
			}
			
			
			System.out.println("------------ All Orders in XML Format ----------------");
			System.out.println(getResponse(allOrdersAsXML));
			
			
			System.out.println("------------ Order AAA-123 in XML Format ----------------");
			System.out.println(getResponse(getOrderAsXML));
			
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
