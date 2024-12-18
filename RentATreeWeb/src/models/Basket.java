package models;
import java.util.*;

public class Basket {
	private ArrayList<Product> prods;
	private ArrayList<Product> inBasket;
	public Basket(ArrayList<Product> products) {
		this.prods = products;
		this.inBasket = new ArrayList<Product>();
	}
	
	public Basket(Basket b) {
		this.inBasket = b.getBasket();
	}
	
	public void addToBasket(int pid) {
		for(Product p : this.prods) {
			if(p.getID()==pid) {
				this.inBasket.add(p);
				break;
			}
		}
		System.out.println("added"+pid);
		System.out.println(this.prods);
	}
	
	public ArrayList<Product> getBasket(){
		return this.inBasket;
	}
	public void remove(int pid) {
		for(Product p : this.prods) {
			if(p.getID()==pid) {
				this.inBasket.remove(p);
				break;
			}
		}
		System.out.println("removed"+pid);
		System.out.println(this.prods);
	}
	
	public void clearBasket() {
		this.inBasket.clear();
	}
}
