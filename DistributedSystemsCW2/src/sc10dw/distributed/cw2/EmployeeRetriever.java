package sc10dw.distributed.cw2;

import java.rmi.*;
import java.rmi.registry.*;

public class EmployeeRetriever
{

	public static void main(String[] args)
	{
		// Retrieve command line argumens
		if (args.length < 1)
		{
			System.out.println("Usage: java EmployeeRetriever <EmployeeObjectName>");
			return;
		}

		try {
			// Try and retrieve the RMI object with the given object name
			Registry registry = LocateRegistry.getRegistry();
			Employee employee = (Employee)registry.lookup(args[0]);
			// Print off employee information
			System.out.println("Employee: " + employee.getForename() + " " + employee.getSurname());
			System.out.println("Works " + employee.getNumberHours() + " hours a week");
			System.out.println("Hourly rate is £" + employee.getHourlyRate());
			System.out.println("Total earnings per week is £" + employee.getWeeklyEarning());
		} catch (RemoteException ex) {
			System.out.println("Could not retrieve employee with object name '" + args[0] + "'");
		} catch (NotBoundException ex) {
			System.out.println("Could not retrieve employee with object name '" + args[0] + "'");
		}
	}

}
