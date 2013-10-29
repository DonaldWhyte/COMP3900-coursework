package sc10dw.distributed.cw2;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeeFactoryImpl extends UnicastRemoteObject implements EmployeeFactory {

	public EmployeeFactoryImpl() throws RemoteException {
		employees = new HashMap<String, Employee>();
		idCounter = 0;
	}
	
	@Override
	public Employee createEmployee(String surname) throws RemoteException {
		try {
			Employee newEmployee = new EmployeeImpl(surname);
			employees.put(generateID(), newEmployee);
			return newEmployee;
		} catch (RemoteException e) {
			System.err.println("Could not create employee: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Employee getEmployee(String surname) throws RemoteException {
		//
		return null;
	}

	@Override
	public Employee deleteEmloyee(String surname) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String generateID() {
		idCounter += 1;
		return String.valueOf(idCounter);
	}
	
	private Map<String, Employee> employees;
	private int idCounter;

	
}
