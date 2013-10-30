package sc10dw.distributed.cw2;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.sql.Connection;
import java.util.List;

/**
 * @author sc10dw
 * Program which starts up a server that creates an employee
 * factory and registers it with the RMI registry.
 * 
 * Two test employees are also created and stored in this
 * factory.
 */
public class EmployeeServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EmployeeFactory employeeFactory = new EmployeeFactoryImpl();
			Employee emp1 = employeeFactory.createEmployee("Whyte");
			emp1.setForename("Donald");
			emp1.setNumberHours(40);
			emp1.setHourlyRate(10.5);
			Employee emp2 = employeeFactory.createEmployee("Johnson");
			emp2.setNumberHours(17);
			emp2.setHourlyRate(6.5);			
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("employee_factory", employeeFactory);		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	

}
