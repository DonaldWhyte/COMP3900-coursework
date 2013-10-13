package sc10dw.distributed.cw1;

import java.sql.Connection;

public class CW1Main {


	public static void main(String[] args) {
		Connection dbConnection = null;
		
		try {
			dbConnection = ConnectionManager.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Ensure the connection to the database is closed 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (Exception closeError) {
					// silently ignore connection close error
				}
			}
		}
		System.out.println("Hello, World!");
	}
	
}