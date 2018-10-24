package ie.gmit.sw.ds;

public class Item implements java.io.Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String 	partNumber;
	private String 	partName;
	private int quantity;
	private double price;

	public Item() {
		super();
	}

	public Item(String partNumber, String partName, int quantity, double price) {
		super();
		this.partNumber = 	partNumber;
		this.partName 	= 	partName;
		this.quantity 	= 	quantity;
		this.price 		= 	price;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}