package db;
import java.sql.*;
import java.util.*;
import models.*;

public class ProductDBHandler {
	ArrayList<Product> products;
	DBConnect db = new DBConnect();
	private ResultSet getProductsQuery() {
		String sql = "SELECT ProductID, Height, Price, TreeDescriptionMaster.TreeType, TreeDescriptionMaster.TreeMaterial, TreeDescriptionMaster.TreeDescription,";
		sql+= "TreeSupplierMaster.SupplierName FROM ProductDescription INNER JOIN TreeDescriptionMaster ";
		sql+= "ON TreeDescriptionMaster.TreeID = ProductDescription.TreeID INNER JOIN TreeSupplierMaster ";
		sql+= "ON TreeSupplierMaster.SupplierID = ProductDescription.SupplierID ";
		sql+= "ORDER BY ProductID";
		System.out.println(sql);
		return db.runQuery(sql);
	}
	
	public ArrayList<Product> getProducts(){
		ArrayList<Product> prods = new ArrayList<Product>();
		try {
			ResultSet rs = getProductsQuery();
			while(rs.next()) {
				int id = rs.getInt("ProductID");
				int price = rs.getInt("Price");
				int height = rs.getInt("Height");
	
				String type = rs.getString("TreeDescriptionMaster.TreeType");
				String desc = rs.getString("TreeDescriptionMaster.TreeDescription");
				String material = rs.getString("TreeDescriptionMaster.TreeMaterial");
				String suppliername = rs.getString("TreeSupplierMaster.SupplierName");
				Product prod = new Product(id, height, price, type, material, desc, suppliername);
				prods.add(prod);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return prods;
	}
	
	private ResultSet getProductsDateFilteredQuery(String start, String end) {
		
		try {
		String sql = "\r\n"
				+ "SELECT ProductID, Height, Price, TreeDescriptionMaster.TreeType, TreeDescriptionMaster.TreeMaterial, TreeDescriptionMaster.TreeDescription,TreeSupplierMaster.SupplierName FROM ProductDescription \r\n"
				+ "INNER JOIN TreeDescriptionMaster ON \r\n"
				+ "TreeDescriptionMaster.TreeID = ProductDescription.TreeID \r\n"
				+ "INNER JOIN TreeSupplierMaster \r\n"
				+ "ON TreeSupplierMaster.SupplierID = ProductDescription.SupplierID\r\n"
				+ "WHERE ProductID not in\r\n"
				+ "(SELECT distinct(ProductID) FROM producttransactiontable where LeaseStart between ? and ? and  LeaseEnd between ? and ?)";
		String[] args = {start, end, start, end};
		ResultSet rs = this.db.executePreparedStatement(sql, args);
		return rs;
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public ArrayList<Product> getProductDateFiltered(String start, String end){
		ArrayList<Product> prods = new ArrayList<Product>();
		try {
			ResultSet rs = this.getProductsDateFilteredQuery(start, end);
			while(rs.next()) {
				int id = rs.getInt("ProductID");
				int price = rs.getInt("Price");
				int height = rs.getInt("Height");
	
				String type = rs.getString("TreeDescriptionMaster.TreeType");
				String desc = rs.getString("TreeDescriptionMaster.TreeDescription");
				String material = rs.getString("TreeDescriptionMaster.TreeMaterial");
				String suppliername = rs.getString("TreeSupplierMaster.SupplierName");
				Product prod = new Product(id, height, price, type, material, desc, suppliername);
				prods.add(prod);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return prods;
	}
}