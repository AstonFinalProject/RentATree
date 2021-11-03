package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.ProductDBHandler;
import models.Basket;
import models.Product;
import models.ProductFilter;

class ProductFilterTest {

	private ArrayList<Product> prods;
	private ProductDBHandler h;
	private ProductFilter pd;
	
	@BeforeEach
    public void setUp() {
    	this.prods = new ArrayList<Product>();
    	this.h = new ProductDBHandler();
    	this.prods = h.getProducts();
		this.pd = new ProductFilter(prods);
    }
	
	@Test
	void uniqueTypesTest() {
    	//ArrayList<Product> prods = new ArrayList<Product>();
    	//ProductDBHandler h = new ProductDBHandler();
    	//prods = h.getProducts();
		//ProductFilter pd = new ProductFilter(prods);
		
		assertEquals("Fir", ProductFilter.uniqueTypes().get(0));
		assertEquals(4, ProductFilter.uniqueTypes().size());
	}
	
	@Test
	void uniqueMaterialsTest() {
		assertEquals("PVC", ProductFilter.uniqueMaterials().get(0));
		assertEquals(3, ProductFilter.uniqueMaterials().size());
	}
	
	@Test
	void uniqueSuppliersTest() {
		assertEquals("GoGoTrees", ProductFilter.uniqueSuppliers().get(0));
		assertEquals(3, ProductFilter.uniqueSuppliers().size());
	}
	
	@Test
	void filterOnTypeTest() {
		ArrayList<String> types = new ArrayList<String>();
		types.add("Fir");
		
		ArrayList<Product> return_products = new ArrayList<Product>();
		return_products = pd.filterOnType(types);
		
		assertEquals(7, return_products.size()); // there are 7 fir trees
		assertEquals("Fir", return_products.get(0).getType());
		assertEquals("Fir", return_products.get(6).getType());
	}
	
	@Test
	void filterOn2TypeTest() {
		ArrayList<String> types = new ArrayList<String>();
		types.add("Fir");
		types.add("Pine");
		
		ArrayList<Product> return_products = new ArrayList<Product>();
		return_products = pd.filterOnType(types);
		
		assertEquals(9, return_products.size()); // there are 7 fir trees and 2 Pine Trees = 9
		
		//Should be ordered by id so check for fir and pine
		assertEquals("Fir", return_products.get(0).getType());
		assertEquals("Pine", return_products.get(5).getType());
		assertEquals("Fir", return_products.get(6).getType());
	}
	
	@Test
	void filterOnSupplierTest() {
		ArrayList<String> supplier = new ArrayList<String>();
		supplier.add("TreesRUs");
		
		ArrayList<Product> return_products = new ArrayList<Product>();
		return_products = pd.filterOnSupplier(supplier);
		
		assertEquals(6, return_products.size()); // there are 6 trees from TreesRUs
		assertEquals("TreesRUs", return_products.get(0).getSupplierName());
		assertEquals("TreesRUs", return_products.get(5).getSupplierName());
	}
	
	@Test
	void filterOn2SupplierTest() {
		ArrayList<String> supplier = new ArrayList<String>();
		supplier.add("TreesRUs");
		supplier.add("SuperTrees");
		
		ArrayList<Product> return_products = new ArrayList<Product>();
		return_products = pd.filterOnSupplier(supplier);
		
		assertEquals(11, return_products.size()); // there are 6 trees from TreesRUs and 5 from SuperTrees = 11
		
		//Should be ordered by id so check for TreesRUs and SuperTrees
		assertEquals("TreesRUs", return_products.get(0).getSupplierName());
		assertEquals("TreesRUs", return_products.get(5).getSupplierName());
		assertEquals("SuperTrees", return_products.get(10).getSupplierName());
	}
	
	@Test
	void filterOnMaterialTest() {
		ArrayList<String> material = new ArrayList<String>();
		material.add("PVC");
		
		ArrayList<Product> return_products = new ArrayList<Product>();
		return_products = pd.filterOnMaterial(material);
		
		assertEquals(7, return_products.size()); // there are 7 made trees from PVC
		assertEquals("PVC", return_products.get(2).getMaterial());
		assertEquals("PVC", return_products.get(5).getMaterial());
	}
	
	@Test
	void filterOn2MaterialTest() {
		ArrayList<String> material = new ArrayList<String>();
		material.add("PVC");
		material.add("Natural");
		
		ArrayList<Product> return_products = new ArrayList<Product>();
		return_products = pd.filterOnMaterial(material);
		
		assertEquals(12, return_products.size()); // there are 7 trees from PVC and 5 Natural trees = 12
		
		//Should be ordered by id so check for PVC and Natural
		assertEquals("PVC", return_products.get(0).getMaterial());
		assertEquals("Natural", return_products.get(5).getMaterial());
		assertEquals("Natural", return_products.get(10).getMaterial());
	}
	
	@Test
	void filterOnHeightRangeTest() {
		ArrayList<Product> return_products = new ArrayList<Product>();
		return_products = pd.filterOnHeightRange(100,110);
		
		assertEquals(1, return_products.size()); // there is 1 tree between 100-110
		
		ArrayList<Product> return_products2 = new ArrayList<Product>();
		return_products2 = pd.filterOnHeightRange(100,200);
		
		assertEquals(13, return_products2.size()); // there is 13 trees between 100-200
		
		ArrayList<Product> return_products3 = new ArrayList<Product>();
		return_products3 = pd.filterOnHeightRange(220,220);
		
		assertEquals(1, return_products3.size()); // there is 1 tree at 220
		
	}
	
	

}
