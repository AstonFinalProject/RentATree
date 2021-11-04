package models;
import java.util.*;

public class AvailableProducts {
	
	public static ArrayList<Product> getAvailableProducts(Basket b, ArrayList<Product> all) {
		ArrayList<Product> final_list = new ArrayList<Product>();
		ArrayList<Integer> basket_pids = AvailableProducts.getPIDs(b.getBasket());
		for(Product p: all) {
			if(!basket_pids.contains(new Integer(p.getID()))) {
				final_list.add(p);
			}
		}
		return final_list;
	}
	
	private static ArrayList<Integer> getPIDs(ArrayList<Product> products){
		ArrayList<Integer> pids = new ArrayList<Integer>();
		for(Product p: products) {
			pids.add(p.getID());
		}
		return pids;
	}
}
