package sc10dw.distributed.cw1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

public class CW1Main {


	public static void main(String[] args) {
		Connection dbConnection = null;
		
		try {
			// Establish connection and display information about the database
			System.out.println("Database Information\n-----------------------------");
			dbConnection = ConnectionManager.getConnection();
			ConnectionManager.displayDatabaseMetadata(dbConnection);
			System.out.println("");
			System.out.println("Database Tables\n-----------------------------");
			for (String tableName : ConnectionManager.retrieveDatabaseTables(dbConnection)) {
				System.out.println(tableName);	
			}
			System.out.println("");
			
			// Print out summary statistics
			EmployeeRetriever retriever = new EmployeeRetriever(dbConnection);
			System.out.println("Summary Statistics\n-----------------------------");
			System.out.println("Average Number of Hours Worked: " + retriever.averageNumHoursWorked());
			System.out.println("Average Base Pay Rate: " + retriever.averagePayRate());
			System.out.println("");
						
			// Ask user for surname of employee to search for
			System.out.print("Enter surname of employee to retrieve (or \"--ALL\" for all employees): ");
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
			String surname = inputReader.readLine();
			// Based on input, retrieve all employees or look for
			// employees with a specific surname
			List<Employee> employees = null;
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