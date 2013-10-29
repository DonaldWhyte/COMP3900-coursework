package sc10dw.distributed.cw2;

import java.rmi.*;
import java.rmi.registry.*;

/**
 * @author sc10dw
 * Program which retrieves a remote Employee object with
 * the given RMI object ID and updates its information
 * using the given command line parameters.
 * 
 * If no employee object with the given ID is found, then
 * an error message is displayed.
 * 
 * If the hourly rate or hours per week are not valid
 * decimal numbers or integers respectively, then an
 * error message is thrown.
 */
public class EmployeeUpdater
{

	public static void main(String[] args)
	{
		// Retrieve command line arguments
		if (args.length < 4)
		{
			System.out.println("Usage: java EmployeeUpdater <EmployeeObjectName> <Forename> <HourlyRate> <HoursPerWeek>");
			return;
		}
		// Validate command line arguments
		double hourlyRate = 0.0;
		int hoursPerWeek = 0;
		try {
			hourlyRate = Double.parseDouble(args[2]);
		} catch (NumberFormatException ex) {
			System.out.println("'" + args[2] + "' is not a valid decimal number");
			return;
		}
		try {
			hoursPerWeek = Integer.parseInt(args[3]);
		} catch (NumberFormatException ex) {
			System.out.println("'" + args[3] + "' is not a valid integer");
			return;
		}

		// Try and retrieve the RMI object with the given object name
		try {
			Registry registry = LocateRegistry.getRegistry();
			Employee employee = (Employee)registry.lookup(args[0]);
			employee.setForename(args[1]);
			employee.setHourlyRate(hourlyRate);
			employee.setNumberHours(hoursPerWeek);
		} catch (RemoteException ex) {
			System.out.println("Could not update employee with object name '" + args[0] + "'");
		} catch (NotBoundException ex) {
			System.out.println("Could not update employee with object name '" + args[0] + "'");
		}
	}

}
