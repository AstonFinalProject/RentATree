package models;

public class Product {
	private String type, material, treeDescription;
	private int height, id, price;
	private String SupplierName;
	
	public Product(int id, int height, int price, String type, String material, String desc, String Supplier) {
		this.type = type;
		this.material = material;
		this.treeDescription = desc;
		this.SupplierName = Supplier;
		this.id = id;
		this.price = price;
		this.height = height;
	}
	
	public String getType() {
		return this.type;
	}
	public String getMaterial() {
		return this.material;
	}
	public String getTreeDescription() {
		return this.treeDescription;
	}
	public int getHeight() {
		return this.height;
	}
	public String getSupplierName() {
		return this.SupplierName;
	}
	public int getID() {
		return this.id;
	}
	public int getPrice() {
		return this.price;
	}
	
	public String toString() {
		return "ID: "+ this.id +" Height: "+ this.height +" Material: "+this.material+" Desc: "+this.treeDescription+" Supplier: "+this.SupplierName+" Type: "+this.type;
	}
}
