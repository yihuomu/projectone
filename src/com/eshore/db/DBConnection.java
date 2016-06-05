package com.eshore.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:sqlserver://localhost:1433;DataBaseName=shop1";
	private static final String USER = "sa";
	private static final String PASSWORD = "123456";
	private Connection conn = null;

	public DBConnection() throws Exception { // 杩涜鏁版嵁搴撹繛鎺�
		try {
			Class.forName(Driver); // 鐢ㄥ弽灏勫姞杞芥暟鎹簱椹卞姩
			this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			throw e; // 鎶涘嚭寮傚父
		}
	}

	public Connection getConnection() {
		return this.conn; // 鍙栧緱鏁版嵁搴撶殑杩炴帴
	}

	public void close() throws Exception { // 鍏抽棴鏁版嵁搴�
		if (this.conn != null) {  
			try {
				this.conn.close(); // 鏁版嵁搴撳叧闂�
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
