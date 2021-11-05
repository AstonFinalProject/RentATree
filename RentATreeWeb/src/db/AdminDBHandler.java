package db;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AdminDBHandler {

	DBConnect db;
	
	public AdminDBHandler() {
		db = new DBConnect();
	}
	
	//public void incrementHit(String Username) {
		//db.runQuery("CALL incrementHit "+Username);
	//}
	
	public void incrementHit(String Username) throws SQLException {
		String sp = "CALL incrementHit (?)";
		CallableStatement cs = this.db.getCallableStatement(sp);
		
		cs.setString(1, Username);
		this.db.executeCallableStatement(cs);
		
		//db.runQuery("CALL incrementHit "+Username);
	}

	
	public void incrementMiss(String Username) throws SQLException {
		String sp = "CALL incrementMiss (?)";
		CallableStatement cs = this.db.getCallableStatement(sp);
		
		cs.setString(1, Username);
		this.db.executeCallableStatement(cs);
		
		//db.runQuery("CALL incrementMiss "+Username);
	}
	
	public void insertTree(String TreeType, String TreeMaterial, String SupplierName, double Height, double Price) throws SQLException {
		String sp = "call insertTree (?,?,?,?,?)";
		CallableStatement cs = this.db.getCallableStatement(sp);
		
		cs.setString(1, TreeType);
		cs.setString(2, TreeMaterial);
		cs.setString(3, SupplierName);
		cs.setDouble(4, Height);
		cs.setDouble(5, Price);
		this.db.executeCallableStatement(cs);
		
		//db.runQuery("CALL insertTree '"+TreeType+"', '"+TreeMaterial+"', '"+SupplierName+"', "+Height+", "+Price);
	}
	
	public void deleteTree(int ProductID) throws SQLException {
		String sp = "CALL deleteTree (?)";
		CallableStatement cs = this.db.getCallableStatement(sp);
		
		cs.setInt(1, ProductID);
		this.db.executeCallableStatement(cs);
		
		//db.runQuery("CALL deleteTree "+ProductID);
	}
	
	public void insertTreeType(String TreeType, String TreeMaterial, String TreeDescription) throws SQLException {
		String sp = "CALL newTreeDescriptionMaster (?,?,?)";
		CallableStatement cs = this.db.getCallableStatement(sp);
		
		cs.setString(1, TreeType);
		cs.setString(2, TreeMaterial);
		cs.setString(3, TreeDescription);
		this.db.executeCallableStatement(cs);
		
		//db.runQuery("CALL newTreeDescriptionMaster '"+TreeDescription+"', '"+TreeType+"', '"+TreeMaterial+"', 0");
	}
	
	public void deleteTreeType(int TreeID) throws SQLException {
		String sp = "CALL deleteTreeType (?)";
		CallableStatement cs = this.db.getCallableStatement(sp);
		
		cs.setInt(1, TreeID);
		this.db.executeCallableStatement(cs);
		
		//db.runQuery("CALL deleteTreeType "+TreeType);
	}
	
}
