package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.ProductDBHandler;
import models.Basket;
import models.Product;

class BasketTest {

	ArrayList<Product> prods;
	ProductDBHandler h;
	Basket basket;
	
	@BeforeEach
    public void setUp() {
    	prods = new ArrayList<Product>();
    	h = new ProductDBHandler();
    	prods = h.getProducts();
		basket = new Basket(prods);
    }
	
	
	@Test
	void addToBasketTest() {
		basket.addToBasket(2);
		assertEquals(1, basket.getBasket().size()); //check item was added to basket
		
		basket.remove(2);
		assertEquals(0, basket.getBasket().size()); //check item was removed to basket
		
		basket.addToBasket(2);
		basket.addToBasket(5);
		assertEquals(2, basket.getBasket().size()); //check items were added to basket
		
	}
	
	@Test
	void removeTest() {
		basket.addToBasket(2);
		assertEquals(1, basket.getBasket().size()); //check item was added to basket
		
		basket.remove(2);
		assertEquals(0, basket.getBasket().size()); //check item was removed to basket
	}
	
	@Test
	void addToBasketMultiTest() {
		basket.addToBasket(2);
		basket.addToBasket(5);
		assertEquals(2, basket.getBasket().size()); //check items were added to basket
	}
}
