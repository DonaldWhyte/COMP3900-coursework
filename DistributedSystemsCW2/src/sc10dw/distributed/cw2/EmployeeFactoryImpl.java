package sc10dw.distributed.cw2;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EmployeeFactoryImpl extends UnicastRemoteObject implements EmployeeFactory {

	public EmployeeFactoryImpl() throws RemoteException {
		employees = new HashMap<String, Employee>();
	}
	
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
	
	@Override
	public List<Employee> getAllEmployees() throws RemoteException {
		return (List<Employee>)employees.values();
	}
	
	private String generateID() {
		return new UID().toString();
	}
	
	private Map<String, Employee> employees;
	
}
