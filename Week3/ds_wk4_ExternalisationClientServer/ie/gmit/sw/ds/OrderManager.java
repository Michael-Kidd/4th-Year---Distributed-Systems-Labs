package ie.gmit.sw.ds;

import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class OrderManager {
	private static final int SERVER_PORT = 9999;
	private ServerSocket server = null;
	private Socket clientRequest = null;
	private boolean blnKeepRunning;
	private Listener listener;
	private Map<String, Order> orders = new HashMap<String, Order>();
	
	public OrderManager(){
		init();
		
		blnKeepRunning = true;
		try{
			server = new ServerSocket(SERVER_PORT);	
			listener = new Listener();
			listener.start();
			
			System.out.println("[INFO] Service started on port " + SERVER_PORT);
		}catch(Exception e){			
			e.printStackTrace();	
		}
	}
	
	private Order getOrder(String orderNumber) throws Exception{
		if (orders.containsKey(orderNumber)){
			return orders.get(orderNumber);
		}else{
			throw new Exception("Invalid order number");
		}
	}
	
	
	private String getOrderAsXML(String orderNumber) throws Exception{
		if (orders.containsKey(orderNumber)){
			Order o = orders.get(orderNumber);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<order number=\"" + o.getOrderNumber() + "\" date=\"" + o.getOrderDate() + "\">");
			Item[] items = o.items();
			for (int i = 0; i < items.length; i++) {
				buffer.append("<item>");
				buffer.append("<partNumber>" + items[i].getPartNumber() + "</partNumber>");
				buffer.append("<partName>" + items[i].getPartName() + "</partName>");
				buffer.append("<quantity>" + items[i].getQuantity() + "</quantity>");
				buffer.append("<price>" + items[i].getPrice() + "</price>");
				buffer.append("</item>");				
			}
			buffer.append("</order>");
			return buffer.toString();
		}else{
			throw new Exception("Invalid order number");
		}

	}
	
	private List<Order> orders() throws Exception{
		return new ArrayList<Order>(orders.values());
	}
	
	private String ordersAsXML() throws Exception{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<orders>");
		Set<String> orderNumbers = orders.keySet();
		for (String o: orderNumbers){
			buffer.append(getOrderAsXML(o));
		}
		
		buffer.append("</orders>");
		return buffer.toString();
	}
	
	private void init(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Order order1 = new Order("AAA-123");
		Date order1Date = null;
		try {
			order1Date = df.parse("2008-07-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order1.setOrderDate(order1Date);
		order1.addItem("QB-123", "Government Bond", 1000, 9.99);
		order1.addItem("QB-784", "Sub-Prime Loan", 10000, 999999.99);
		
		Order order2 = new Order("AAA-124");
		Date order2Date = null;
		try {
			order2Date = df.parse("2008-04-02");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order2.setOrderDate(order2Date);
		order2.addItem("QB-299", "Home Mortgage", 10000, 10000.00);
		order2.addItem("QB-458", "Car Loan", 100000, 5000);

		orders.put(order1.getOrderNumber(), order1);
		orders.put(order2.getOrderNumber(), order2);
	}
	
	public static void main(String[] args) {
		new OrderManager();
	}
	
	
	class Listener extends Thread{
		public void run(){
			try{
				while(blnKeepRunning){
					//Accept incoming socket request
					clientRequest = server.accept();
					
					//Deserialize the socket input stream into a String
					InputStream inputStream = clientRequest.getInputStream();
					ObjectInputStream oin = new ObjectInputStream(inputStream);
					
					OutputStream outStream = clientRequest.getOutputStream();
					ObjectOutputStream out = new ObjectOutputStream(outStream);

					
					String command = (String) oin.readObject();
					
					System.out.println("[INFO] Received " + command + " from " + clientRequest.getInetAddress());
					
					Object request = null;
					
					String[] orderRequest = command.split(">>");
					if(orderRequest[0].equals("bytecodes")){
						if (orderRequest[1].equals("orders")){
							request = orders();
						}else{
							request = getOrder(orderRequest[2]);
						}
					}else{ //An XML request
						if (orderRequest[1].equals("orders")){
							request = ordersAsXML();
						}else{
							request = getOrderAsXML(orderRequest[2]);
						}
					}

					out.writeObject(request);
					out.close();
				}		
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
