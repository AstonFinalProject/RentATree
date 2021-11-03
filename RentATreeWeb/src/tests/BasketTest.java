package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import db.ProductDBHandler;
import models.Basket;
import models.Product;

class BasketTest {

	@Test
	void test() {
    	ArrayList<Product> prods = new ArrayList<Product>();
    	ProductDBHandler h = new ProductDBHandler();
    	prods = h.getProducts();
		Basket basket = new Basket(prods);
		
		basket.addToBasket(2);
		assertEquals(1, basket.getBasket().size()); //check item was added to basket
		
		basket.remove(2);
		assertEquals(0, basket.getBasket().size()); //check item was removed to basket
		
		basket.addToBasket(2);
		basket.addToBasket(5);
		assertEquals(2, basket.getBasket().size()); //check items were added to basket
		
	}

}
