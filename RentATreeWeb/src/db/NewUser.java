package db;
import java.util.*;
public class NewUser {
	
	String[] params;
	DBConnect db;
	public NewUser(String[] params) {
		this.params = params;
		db = new DBConnect();
	}
	
	public int createUser() {
		int output = 0;
		try {
			String sql = "call createNewUser(?,?,?,?,?,?,?)";
			ArrayList<String> params_list = new ArrayList<String>();
			for(String s: params) {
				params_list.add(s);
				System.out.println(s);
			}
			output = db.execSP(params_list, sql);
			System.out.println(output);
		}catch(Exception e) {
			System.out.println(e);
		}
		return output;
	}
}
