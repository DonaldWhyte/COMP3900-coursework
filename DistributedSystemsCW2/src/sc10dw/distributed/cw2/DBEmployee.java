package sc10dw.distributed.cw2;

import java.rmi.*;
import java.rmi.server.*;

/**
 * @author sc10dw
 * Implementation of employee for emploees retrieved from JDBC database.
 */
public class DBEmployee extends UnicastRemoteObject implements Employee {

	/**
	 * Construct new instance of employee, defining all of
	 * their properties initially in the constructor.
	 * @param fname Forename of employee
	 * @param sname Surname of employee
	 * @param hoursPerWeek Hours per week employee works
	 * @param hourlyRate How much the employee is paid an hour
	 * @throws RemoteException if problem creating RMI object
	 */
	public DBEmployee(String fname, String sname, int hoursPerWeek, double hourlyRate) throws RemoteException { 
		this.fname = fname;
		this.sname = sname;
		this.hoursPerWeek = hoursPerWeek;
		this.hourlyRate = hourlyRate;
	}

	@Override
	public String getSurname() throws RemoteException {
		return sname;
	}

	@Override
	public String getForename() throws RemoteException {
		return fname;
	}

	@Override
	public int getNumberHours() throws RemoteException
	{
		return hoursPerWeek;
	}

	@Override
	public double getHourlyRate() throws RemoteException {
		return hourlyRate;
	}

	@Override
	public double getWeeklyEarning() throws RemoteException {
		return hoursPerWeek * hourlyRate;
	}

	@Override
	public void setForename(String s) throws RemoteException {
		fname = s;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#setNumberHours(int)
	 */
	public void setNumberHours(int n) throws RemoteException {
		hoursPerWeek = n;
	}

	@Override
	public void setHourlyRate(double r) throws RemoteException {
		hourlyRate = r;
	}
	
	@Override
	public String toString() {
		return String.format("Employee (%s %s, %d, %.2f)",
			fname, sname, hoursPerWeek, hourlyRate);
	}
	
	/**
	 * Forename of employee
	 */
	private String fname;
	/**
	 * Surname of employee
	 */
	private String sname;
	/**
	 * Hours per week employee works
	 */
	private int hoursPerWeek;
	/**
	 * How much the employee is paid an hour
	 */
	private double hourlyRate;
	
}
