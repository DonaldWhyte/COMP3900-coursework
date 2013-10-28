package sc10dw.distributed.cw2;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.sql.Connection;
import java.util.List;

public class EmployeeServer {

	public EmployeeServer(String empObjectName, String empSurname) throws RemoteException {
		objectName = empObjectName;
		employee = new EmployeeImpl(empSurname);
	}
	
	public void start() {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			registry.rebind(objectName, employee);
		} catch (RemoteException e) {
			System.err.println("Error in an employee server! Stacktrace:");
			e.printStackTrace();
		}    		
	}
	
	public Employee getEmployeeObject() {
		return employee;
	}
	
	private String objectName;
	private Employee employee;

}
