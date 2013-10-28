package sc10dw.distributed.cw2;

import java.rmi.*;
import java.rmi.server.*;

public class DBEmployee extends UnicastRemoteObject implements Employee {

	public DBEmployee(String fname, String sname, int hoursPerWeek, double hourlyRate) throws RemoteException { 
		this.fname = fname;
		this.sname = sname;
		this.hoursPerWeek = hoursPerWeek;
		this.hourlyRate = hourlyRate;
	}

	public String getSurname() throws RemoteException {
		return sname;
	}

	public String getForename() throws RemoteException {
		return fname;
	}

	public int getNumberHours() throws RemoteException
	{
		return hoursPerWeek;
	}

	public double getHourlyRate() throws RemoteException {
		return hourlyRate;
	}

	public double getWeeklyEarning() throws RemoteException {
		return hoursPerWeek * hourlyRate;
	}

	public void setForename(String s) throws RemoteException {
		fname = s;
	}

	public void setNumberHours(int n) throws RemoteException {
		hoursPerWeek = n;
	}

	public void setHourlyRate(double r) throws RemoteException {
		hourlyRate = r;
	}
	
	@Override
	public String toString() {
		return String.format("Employee (%s %s, %d, %.2f)",
			fname, sname, hoursPerWeek, hourlyRate);
	}
	
	private String fname;
	private String sname;
	private int hoursPerWeek;
	private double hourlyRate;
	
}
