package models;
import 	java.util.*;

public class ProductFilter {
	
	ArrayList<Product> prods;
	
	public ProductFilter(ArrayList<Product> products) {
		this.prods = products;
	}
	
	public ArrayList<Product> filterOnType(ArrayList<String> types){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(types.contains(p.getType())) {
				return_products.add(p);
			}
		}
		return return_products;
	}
	
	public ArrayList<Product> filterOnMaterial(ArrayList<String> materials){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(materials.contains(p.getMaterial())) {
				return_products.add(p);
			}
		}
		return return_products;
	}
	
	public ArrayList<Product> filterOnSupplier(ArrayList<String> suppliers){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(suppliers.contains(p.getSupplierName())) {
				return_products.add(p);
			}
		}
		return return_products;
	}
	
	public ArrayList<Product> filterOnHeightRange(int min, int max){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(min<=p.getHeight() && p.getHeight()<=max) {
				return_products.add(p);
			}
		}
		return return_products;
	}
}
