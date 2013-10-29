package sc10dw.distributed.cw2;

import java.rmi.*;
import java.util.List;

/**
 * @author sc10dw
 * Represents factory which can create new instances
 * of employees as well as retrieve employees based
 * on their surname.
 */
public interface EmployeeFactory extends Remote {

	/**
	 * @param surname Surname of new employeee
	 * @return Newly created Employee instance with given surname.
	 *         The other properties of the instance are just blank defaults
	 *         that must be set manually.
	 * @throws RemoteException if problem accessing remote object
	 */
	public Employee createEmployee(String surname) throws RemoteException;
	/**
	 * @param surname Surname of employees to search for
	 * @return List of employees with given name
	 * @throws RemoteException if problem accessing remote object
	 */
	public List<Employee> getEmployee(String surname) throws RemoteException;
	/**
	 * @return List of all employees created by this factory
	 * @throws RemoteException if problem accessing remote object
	 */
	public List<Employee> getAllEmployees() throws RemoteException;
	
}
