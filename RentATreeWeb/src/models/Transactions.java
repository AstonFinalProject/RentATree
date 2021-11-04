package models;
import db.*;
import java.util.*;



public class Transactions {
	
	private Basket t_basket;
	private String username, lease_start, lease_end;
	int total_cost, discount;
	private ArrayList<ProductTransaction> p_transactions;
	
	private String deliverySlot, returnSlot;
	
	public Transactions(Basket b, String username, String lease_start, String lease_end, String deliverySlot, String returnSlot, String promoCode) {
		this.t_basket = b;
		this.lease_end = lease_end;
		this.lease_start = lease_start;
		this.username = username;
		this.deliverySlot = deliverySlot;
		this.returnSlot = returnSlot;
		this.p_transactions = new ArrayList<ProductTransaction>();
		this.createProductTransactions();
		this.calcTotalCost();
		if(promoCode!=null && promoCode.equals("BOGOHP")) {
			this.checkPromo();
		}
		
	}
	
	private void createProductTransactions() {
		for(Product p: this.t_basket.getBasket()) {
			ProductTransaction p_t = new ProductTransaction(p.getID(), lease_start, lease_end);
			p_transactions.add(p_t);
		}
	}
	
	private void calcTotalCost() {
		for(Product p: this.t_basket.getBasket()) {
			this.total_cost+=p.getPrice();
		}
	}
	
	private void checkPromo() {
		if(p_transactions.size()>1) {
			this.total_cost -= (this.findCheapestTree())/2;
			this.discount = (this.findCheapestTree())/2;
		}
	}
	
	private int findCheapestTree() {
		int min = 10000000;
		for(Product p: this.t_basket.getBasket()) {
			if(p.getPrice()<min) {
				min = p.getPrice();
			}
		}
		if(min==10000000) {
			return 0;
		}
		return min;
	}
	
	public int getTotalCost() {
		return this.total_cost;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public ArrayList<ProductTransaction> getProductTransactions(){
		return this.p_transactions;
	}
	
	public String getStartDate() {
		return this.lease_start;
	}
	public String getEndDate() {
		return this.lease_end;
	}
	public String getDeliverySlot() {
		return this.deliverySlot;
	}
	public String getReturnSlot() {
		return this.returnSlot;
	}
	public int getDiscount() {
		return this.discount;
	}
}	

