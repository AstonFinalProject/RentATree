package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.ProductDBHandler;
import models.Product;
import models.ProductFilter;

class ProductTest {

	int id;
	int height;
	int price;
	String type;
	String material;
	String desc;
	String Supplier;
	Product product;
	
	@BeforeEach
    public void setUp() {
		id = 1;
		height = 5;
		price = 10;
		type = "Fir";
		material = "PVC";
		desc = "A beautiful tree :)";
		Supplier = "Harisons Really Nice Trees";
		product = new Product(id, height, price, type, material, desc, Supplier);
    }
	
	@Test
	public void getIDTest() {
	assertEquals(1, product.getID());
	}
	
	@Test
	public void getHeightTest() {
	assertEquals(5, product.getHeight());
	}
	
	@Test
	public void getPriceTest() {
	assertEquals(10, product.getPrice());
	}
	
	@Test
	public void getTypeTest() {
	assertEquals("Fir", product.getType());
	}
	
	@Test
	public void getMaterialTest() {
	assertEquals("PVC", product.getMaterial());
	}
	
	@Test
	public void getTreeDescriptionTest() {
	assertEquals("A beautiful tree :)", product.getTreeDescription());
	}
	
	@Test
	public void getSupplierNameTest() {
	assertEquals("Harisons Really Nice Trees", product.getSupplierName());
	}
	
	@Test
	void multiProductTest() {
		
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
		
		assertEquals(
				"ID: 1 Height: 5 Material: PVC Desc: A beautiful tree :) Supplier: Harisons Really Nice Trees Type: Fir"
				,product.toString());
	}

}
