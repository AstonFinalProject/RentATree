package models;
import 	java.util.*;
import db.*;
import java.sql.*;

public class ProductFilter {
	
	ArrayList<Product> prods;
	
	public ProductFilter(ArrayList<Product> products) {
		this.prods = products;
	}
	
	public static ArrayList<String> uniqueTypes(){
		DBConnect db = new DBConnect();
		ArrayList<String> types = new ArrayList<String>();
		try {
		ResultSet rs = db.runQuery("SELECT distinct(TreeType) FROM TreeDescriptionmaster");
		while(rs.next()) {
			types.add(rs.getString("TreeType"));
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		return types;
	}
	
	public static ArrayList<String> uniqueMaterials(){
		DBConnect db = new DBConnect();
		ArrayList<String> types = new ArrayList<String>();
		try {
		ResultSet rs = db.runQuery("SELECT distinct(TreeMaterial) FROM TreeDescriptionmaster");
		while(rs.next()) {
			types.add(rs.getString("TreeMaterial"));
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		return types;
	}
	
	public static ArrayList<String> uniqueSuppliers(){
		DBConnect db = new DBConnect();
		ArrayList<String> types = new ArrayList<String>();
		try {
		ResultSet rs = db.runQuery("SELECT distinct(SupplierName) FROM TreeSupplierMaster");
		while(rs.next()) {
			types.add(rs.getString("SupplierName"));
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		return types;
	}
	
	public ArrayList<Product> filterOnType(ArrayList<String> types){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(types.contains(p.getType())) {
				return_products.add(p);
			}
		}
		return return_products;
	}
	
	public ArrayList<Product> filterOnMaterial(ArrayList<String> materials){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(materials.contains(p.getMaterial())) {
				return_products.add(p);
			}
		}
		return return_products;
	}
	
	public ArrayList<Product> filterOnSupplier(ArrayList<String> suppliers){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(suppliers.contains(p.getSupplierName())) {
				return_products.add(p);
			}
		}
		return return_products;
	}
	
	public ArrayList<Product> filterOnHeightRange(int min, int max){
		ArrayList<Product> return_products = new ArrayList<Product>();
		for(Product p: this.prods) {
			if(min<=p.getHeight() && p.getHeight()<=max) {
				return_products.add(p);
			}
		}
		return return_products;
	}
}
