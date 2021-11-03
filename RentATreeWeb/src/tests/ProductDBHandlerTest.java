package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import db.ProductDBHandler;
import models.Product;

class ProductDBHandlerTest {

	@Test
	void test() {
    	ArrayList<Product> prods = new ArrayList<Product>();
    	ProductDBHandler h = new ProductDBHandler();
    	
    	//Get products test
    	prods = h.getProducts();
    	assertEquals(prods.size(), 17);
    	//for(Product p: prods){
    		//System.out.println(p.toString());
    	//}
	}

}
