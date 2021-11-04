package db;
import java.sql.*;
import java.util.*;

public class DeliveryHandler {
	
	private String housenumber, street, city,  postcode;
	private DBConnect db;
	int finaltransactionid;
	
	public DeliveryHandler(String housenumber, String street, String city, String postcode, int finaltransactionid) {
		this.housenumber = housenumber;
		this.street = street;
		this.city = city;
		this.postcode = postcode;
		this.finaltransactionid = finaltransactionid;
		this.db = new DBConnect();
	}
	
	public int performDeliveryRecordOperations() {
		int deliveryid = this.checkAddressExists();
		if(deliveryid==0) {
			deliveryid = this.insertAddress();
		}
		this.insertDeliveryJunctionRecord(deliveryid);
		return deliveryid;
	}
	
	// return the delivery address if it exists else 0
	private int checkAddressExists() {
		try {
			String sql = "SELECT DeliveryAddressID FROM DeliveryAddressTable WHERE housenameornumber=? AND streetname=? AND city=? AND postcode=?";
			String[] params = {this.housenumber, this.street, this.city, this.postcode};
			ResultSet rs = this.db.executePreparedStatement(sql, params);
			if(rs.next()) {
				int DeliveryAddressID = rs.getInt("DeliveryAddressID");
				return DeliveryAddressID;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	private int insertAddress() {
		try {
			String sp = "call newDeliveryAddress(?, ?, ?, ?, ?)";
			String[] params = {this.housenumber, this.street, this.city, this.postcode};
			ArrayList p_list = new ArrayList<>(Arrays.asList(params));
			int id = this.db.execSP(p_list, sp);
			return id;
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	private void  insertDeliveryJunctionRecord(int deliveryID) {
		try {
			String sql = "call insertTransactionJunction(?, ?)";
			CallableStatement cs = this.db.getCallableStatement(sql);
			cs.setInt(1, this.finaltransactionid);
			cs.setInt(2, deliveryID);
			this.db.executeCallableStatement(cs);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}

