package sc10dw.distributed.cw2;

import java.rmi.RemoteException;

public class EmployeeServerFactoryImpl implements EmployeeServerFactory {

	@Override
	public EmployeeServer createEmployeeServer(String objectName, String surname) {
		try {
			return new EmployeeServer(objectName, surname);
		} catch (RemoteException e) {
			System.err.println("Could not create employee server for object with name '" + objectName + "'");
			return null;
		}
	}
	
}
