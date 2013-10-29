package sc10dw.distributed.cw2;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author sc10dw
 *
 */
public class EmployeeFactoryImpl extends UnicastRemoteObject implements EmployeeFactory {

	/**
	 * @throws RemoteException
	 */
	public EmployeeFactoryImpl() throws RemoteException {
		employees = new HashMap<String, Employee>();
	}
	
	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.EmployeeFactory#createEmployee(java.lang.String)
	 */
	@Override
	public Employee createEmployee(String surname) throws RemoteException {
		try {
			Employee newEmployee = new EmployeeImpl(surname);
			String id = generateID();
			// BIND HERE???
			employees.put(id, newEmployee);
			return newEmployee;
		} catch (RemoteException e) {
			System.err.println("Could not create employee: " + e.getMessage());
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.EmployeeFactory#getEmployee(java.lang.String)
	 */
	@Override
	public List<Employee> getEmployee(String surname) throws RemoteException {
		// Iterate through employees and find ones with matching surnames
		List<Employee> matches = new ArrayList<Employee>();
		for (Entry<String, Employee> entry : employees.entrySet()) {
			Employee emp = entry.getValue();
			if (emp.getSurname().equals(surname)) {
				matches.add(emp);
			}
		}
		return matches;
	}
	
	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.EmployeeFactory#getAllEmployees()
	 */
	@Override
	public List<Employee> getAllEmployees() throws RemoteException {
		return (List<Employee>)employees.values();
	}
	
	/**
	 * @return
	 */
	private String generateID() {
		return new UID().toString();
	}
	
	/**
	 * 
	 */
	private Map<String, Employee> employees;
	
}
