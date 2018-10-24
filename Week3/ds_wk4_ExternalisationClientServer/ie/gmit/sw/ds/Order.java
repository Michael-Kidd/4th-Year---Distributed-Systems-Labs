package ie.gmit.sw.ds;

import java.util.*;

public class Order implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String orderNumber;
	private Date OrderDate;
	private List<Item> items = new ArrayList<Item>();
	
	public Order() {
		super();
	}

	public Order(String orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	
	public void addItem(String partNumber, String partName, int quantity, double price){
		items.add(new Item(partNumber, partName, quantity, price));		
	}
	
	public void removeItem(String partNumber){
		Item target = null;		
		for (Item i : items){
			if(partNumber.equalsIgnoreCase(i.getPartNumber())){
				target = i;
				break;
			}
		}
		
		if(target != null) items.remove(target);
	}
	
	public Item[] items(){
		Item[] clones = new Item[items.size()];
		for(int i = 0; i < items.size(); i++){
			Item item = items.get(i);
			try{
				clones[i] = (Item)item.clone();
			}
			catch(CloneNotSupportedException e){
				//Ignore CloneNotSupportedException
			}
		}
		return clones;
	}
	
	public int itemCount(){
		return items.size();
	}
}