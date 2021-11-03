package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import db.ProductDBHandler;
import models.Product;

class ProductDBHandlerTest {

	@Test
	void getProductsTest() {
    	ArrayList<Product> prods = new ArrayList<Product>();
    	ProductDBHandler h = new ProductDBHandler();
    	
    	//Get products test
    	prods = h.getProducts();
    	assertEquals(prods.size(), 17); // check all 17 products have been imported
    	assertNotNull(prods.get(5)); // check its not empty
    	//for(Product p: prods){
    		//System.out.println(p.toString());
    	//}
	}

}
