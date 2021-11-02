package controller;
import db.ProductDBHandler;
import models.Product;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		ProductDBHandler h = new ProductDBHandler();
		ArrayList<Product> p = h.getProducts();
		for(Product tree: p) {
			System.out.println(tree);
		}
	}
}
