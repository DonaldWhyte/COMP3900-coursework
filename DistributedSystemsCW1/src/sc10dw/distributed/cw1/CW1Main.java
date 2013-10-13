package sc10dw.distributed.cw1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;

public class CW1Main {


	public static void main(String[] args) {
		Connection dbConnection = null;
		
		try {
			dbConnection = ConnectionManager.getConnection();
			EmployeeRetriever retriever = new EmployeeRetriever(dbConnection);
			List<Employee> employees = null;
		
			// Ask user for surname of employee to such for
			System.out.print("Enter surname of employee to retrieve (or \"--ALL\" for all employees): ");
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
			String surname = inputReader.readLine();
			
			// Based on input, retrieve all employees or look for
			// employees with a specific surname
			if (surname.equals("--ALL")) {
				employees = retriever.allEmployees();
			} else {
				employees = retriever.getEmployees(surname);
			}
			
			// Output result of query
			System.out.println("Found Employees\n-----------------------------");
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