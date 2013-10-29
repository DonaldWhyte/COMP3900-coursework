package sc10dw.distributed.cw2;

import java.rmi.*;
import java.util.List;

/* Interface for generating employee servers. */
public interface EmployeeFactory extends Remote {

	public Employee createEmployee(String surname) throws RemoteException;
	public List<Employee> getEmployee(String surname) throws RemoteException;
	public List<Employee> getAllEmployees() throws RemoteException;
	
}
