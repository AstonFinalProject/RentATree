package controller;
import db.*;
import models.Basket;
import models.Product;
import models.Transactions;
import java.util.*;

public class main {
	public static void main(String[] args) {
		ProductDBHandler h = new ProductDBHandler();
		ArrayList<Product> p = h.getProducts();
		for(Product tree: p) {
			System.out.println(tree);
		}
	
		Basket b = new Basket(p);
		b.addToBasket(3);
		Transactions t = new Transactions(b, "Test", "", "");
		TransactionDBHandler handler = new TransactionDBHandler(t);
		handler.enterTransaction();
	}
}
