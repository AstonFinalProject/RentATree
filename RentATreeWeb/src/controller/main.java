package controller;
import db.*;
import models.Basket;
import models.Product;
import models.Transactions;
import java.util.*;

public class main {
	public static void main(String[] args) {
		ProductDBHandler h = new ProductDBHandler();
		ArrayList<Product> p = h.getProductDateFiltered("2021-11-01", "2021-11-05");
		System.out.println(p);
	}
}
