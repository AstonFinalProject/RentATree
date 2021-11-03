package db;
import java.util.*;

public class Login {
	
	private String username, password;
	private DBConnect db;
	
	public Login(String username, String password) {
		db = new DBConnect();
		db.connect();
		this.username = username;
		this.password = password;
	}
	
	public int loginUser() {
		int output = 0;
		try {
			String loginSQL = "call login(?,?,?)";
			ArrayList<String> params = new ArrayList<String>();
			params.add(username);
			params.add(password);
			System.out.println(username);
			output = db.execSP(params, loginSQL);
			System.out.println(output);
		}catch(Exception e) {
			System.out.println(e);
		}
		return output;
	}
	
	public int loginAdmin() {
		int output = 0;
		try {
			String loginSQL = "call adminLogin(?,?,?)";
			ArrayList<String> params = new ArrayList<String>();
			params.add(username);
			params.add(password);
			System.out.println(username);
			output = db.execSP(params, loginSQL);
			System.out.println(output);
		}catch(Exception e) {
			System.out.println(e);
		}
		return output;
	}
}
