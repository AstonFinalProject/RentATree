package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Product;

class ProductTest {

	@Test
	void test() {
		int id = 1;
		int height = 5;
		int price = 10;
		String type = "Fir";
		String material = "PVC";
		String desc = "A beautiful tree :)";
		String Supplier = "Harisons Really Nice Trees";
		Product product = new Product(id, height, price, type, material, desc, Supplier);
		
		// test getters
		assertEquals(1, product.getID());
		assertEquals(5, product.getHeight());
		assertEquals(10, product.getPrice());
		assertEquals("Fir", product.getType());
		assertEquals("PVC", product.getMaterial());
		assertEquals("A beautiful tree :)", product.getTreeDescription());
		assertEquals("Harisons Really Nice Trees", product.getSupplierName());
		
		
		//test 2nd product
		int id2 = 2;
		int height2 = 3;
		int price2 = 10;
		String type2 = "Fir";
		String material2 = "PVC";
		String desc2 = "A beautiful tree :)";
		String Supplier2 = "Harisons Really Nice Trees";
		Product product2 = new Product(id2, height2, price2, type2, material2, desc2, Supplier2);
		
		assertEquals(2, product2.getID());
		assertEquals(3, product2.getHeight());
		assertEquals(10, product2.getPrice());
		assertEquals("Fir", product2.getType());
		assertEquals("PVC", product2.getMaterial());
		assertEquals("A beautiful tree :)", product2.getTreeDescription());
		assertEquals("Harisons Really Nice Trees", product2.getSupplierName());
		
	}
	
	@Test
	void toStringTest() {
		int id = 1;
		int height = 5;
		int price = 10;
		String type = "Fir";
		String material = "PVC";
		String desc = "A beautiful tree :)";
		String Supplier = "Harisons Really Nice Trees";
		Product product = new Product(id, height, price, type, material, desc, Supplier);
		
		assertEquals(
				"ID: 1 Height: 5 Material: PVC Desc: A beautiful tree :) Supplier: Harisons Really Nice Trees Type: Fir"
				,product.toString());
	}

}
