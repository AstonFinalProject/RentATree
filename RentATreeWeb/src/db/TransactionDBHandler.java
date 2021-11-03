package db;
import models.*;
import java.sql.*;

public class TransactionDBHandler {
	
	Transactions t;
	int userid, fid;
	DBConnect db;
	
	public TransactionDBHandler(Transactions t) {
		this.t = t;
		db = new DBConnect();
	}
	
	
	private void InsertFinalTransaction() {
		try {
			String sp = "call userTransaction(?,?,?)";
			CallableStatement cs = this.db.getCallableStatement(sp);
			cs.setString(1, this.t.getUsername());
			cs.setInt(2, this.t.getTotalCost());
			cs.registerOutParameter(3, Types.INTEGER);
			this.db.executeCallableStatement(cs);
			this.fid = cs.getInt(3);
			System.out.println(this.fid);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private void InsertProductTransactions() {
		String sp = "call productTransaction(?,?,?,?)";
		try {
			for(ProductTransaction p_t: this.t.getProductTransactions()) {
				Date lease_start = Date.valueOf("2021-11-03");
				Date lease_end = Date.valueOf("2021-11-6");
				System.out.println(lease_start);
				int p_id = p_t.getProductID();
				CallableStatement cs = this.db.getCallableStatement(sp);
				cs.setInt(1,  p_id);
				cs.setInt(2, this.fid);
				cs.setDate(3, lease_start);
				cs.setDate(4, lease_end);
				this.db.executeCallableStatement(cs);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void enterTransaction() {
		try {
			System.out.println("inserting transaction into table");
			this.InsertFinalTransaction();
			this.InsertProductTransactions();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
