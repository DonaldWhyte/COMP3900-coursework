package sc10dw.distributed.cw2;

import java.rmi.*;

/* Interface for generating employee servers. */
public interface EmployeeFactory extends Remote {

	public Employee createEmployee(String surname) throws RemoteException;
	public Employee getEmployee(String surname) throws RemoteException;
	public Employee deleteEmloyee(String surname) throws RemoteException;
	
}
