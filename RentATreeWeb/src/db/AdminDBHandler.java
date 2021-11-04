package db;


public class AdminDBHandler {

	DBConnect db;
	
	public AdminDBHandler() {
		db = new DBConnect();
	}
	
	public void incrementHit(String Username) {
		db.runQuery("CALL incrementHit "+Username);
	}
	
	public void incrementMiss(String Username) {
		db.runQuery("CALL incrementMiss "+Username);
	}
	
	public void insertTree(String TreeType, String SupplierName, double Height, double Price) {
		db.runQuery("CALL insertNewProduct '"+TreeType+"', '"+SupplierName+"', "+Height+", "+Price);
	}
	
	public void deleteTree(int ProductID) {
		db.runQuery("CALL deleteTree "+ProductID);
	}
	
	public void insertTreeType(String TreeType, String TreeMaterial, String TreeDescription) {
		db.runQuery("CALL newTreeDescriptionMaster '"+TreeDescription+"', '"+TreeType+"', '"+TreeMaterial+"', 0");
	}
	
	public void deleteTreeType(String TreeType) {
		db.runQuery("CALL deleteTreeType "+TreeType);
	}
	
}
