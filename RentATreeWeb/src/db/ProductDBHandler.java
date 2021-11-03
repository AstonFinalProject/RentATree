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
}