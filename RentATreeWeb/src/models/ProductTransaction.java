package models;

public class ProductTransaction {
	int product_id;
	String lease_start, lease_end;
	
	public ProductTransaction(int product_id, String lease_start, String lease_end) {
		this.product_id = product_id;
		this.lease_end = lease_end;
		this.lease_start = lease_start;
	}
	
	public String getLeaseStart() {
		return this.lease_start;
	}
	
	public String getLeaseEnd() {
		return this.lease_end;
	}
	
	public int getProductID() {
		return this.product_id;
	}
}
