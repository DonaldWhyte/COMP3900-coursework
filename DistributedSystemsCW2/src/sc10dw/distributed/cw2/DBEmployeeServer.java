package sc10dw.distributed.cw2;

import java.rmi.registry.*;
import java.sql.Connection;
import java.util.List;

/**
 * @author sc10dw
 * Version of EmployeeServer which looks up employees with given surname
 * in a JDBC database, then binds that employee as an object in the
 * RMI registry.
 * 
 * If more than one employee exist with given surname, first employee
 * found is used.
 */
public class DBEmployeeServer {

     /**
     * Entry point of application.
     * @param argv
     */
    public static void main(String[] argv)
       {
               // Retrieve command line arguments
               if (argv.length < 2) {
                       System.out.println("java DBEmployeeServer <EmployeeSurname> <EmployeeObjectName>");
                       return;
               }

               Connection dbConnection = null;
               try {           
                       // Establish connection with employee database
                       dbConnection = DBConnectionManager.getConnection();
                       // Retrieve all employees with given surname
                       DBEmployeeRetriever employeeRetriever = new DBEmployeeRetriever(dbConnection);
                       List<Employee> foundEmployees = employeeRetriever.getEmployees(argv[0]);
                       if (foundEmployees.isEmpty()) {
                               System.out.println("No employees were foudn with surname '" + argv[0] + "'. Could not bind employee.");
                       } else {
                               // Bind the first employee to the RMI registry
                               Employee employee = foundEmployees.get(0);
                               Registry registry = LocateRegistry.getRegistry();
                               registry.rebind(argv[1], employee); // rebind() so it doesn't matter if an object already exists with that name
                               // Inform invoker of server that object has been bound
                               System.out.println("Employee " + employee.getForename() + " "
                                       + employee.getSurname() + " has been bound with name "
                                       + argv[1] + ".");
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
