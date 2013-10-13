package sc10dw.distributed.cw1;

import java.sql.Connection;
import java.util.List;

public class CW1Main {


	public static void main(String[] args) {
		Connection dbConnection = null;
		
		try {
			dbConnection = ConnectionManager.getConnection();
			
			System.out.println("All Employees\n------------------------");
			
			EmployeeRetriever retriever = new EmployeeRetriever(dbConnection);
			List<Employee> employees = retriever.allEmployees();
			for (Employee employee : employees) {
				System.out.println(employee);
			}
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
	}
	
}