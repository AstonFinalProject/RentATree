package db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Basket;
import models.Product;

class AdminDBHandlerTest {

	private AdminDBHandler h;
	
	@BeforeEach
    public void setUp() {
    	h = new AdminDBHandler();

    }
	
	@Test
	public void incrementHitTest() throws SQLException {
		h.incrementHit("Harison987");
		// check db for result
	}
	
	@Test
	public void incrementMissTest() throws SQLException {
		h.incrementMiss("Harison987");
		// check db for result
	}
	
	
	@Test
	public void insertTreeTest() throws SQLException {
		
		String TreeType , TreeMaterial, SupplierName;
		double Height, Price;
		TreeType = "Fir";
		TreeMaterial = "PVC";
		SupplierName = "GoGoTrees";
		Height = 1234.5;
		Price = 4321.99;
		
		h.insertTree(TreeType, TreeMaterial, SupplierName, Height, Price);
		// check db for entry
	
	}
	
	public void deleteTreeTest() throws SQLException {

		h.deleteTree(1);
		// check that tree 1 was delted

	}
	
	public void insertTreeTypeTest() throws SQLException {
		
		String TreeType , TreeMaterial, SupplierName;

		TreeType = "Acorn";
		TreeMaterial = "Natural";
		SupplierName = "GoGoTrees";

		
		h.insertTreeType(TreeType, TreeMaterial, SupplierName);
		// check db for entry
	}
	
	public void deleteTreeTypeTest() throws SQLException {
		
		h.deleteTreeType(1);
		// check tree num 1 has been deleted
		
	}

}
