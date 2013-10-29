package sc10dw.distributed.cw2;

import java.rmi.*;
import java.rmi.server.*;

/**
 * @author sc10dw
 *
 */
public class DBEmployee extends UnicastRemoteObject implements Employee {

	/**
	 * @param fname
	 * @param sname
	 * @param hoursPerWeek
	 * @param hourlyRate
	 * @throws RemoteException
	 */
	public DBEmployee(String fname, String sname, int hoursPerWeek, double hourlyRate) throws RemoteException { 
		this.fname = fname;
		this.sname = sname;
		this.hoursPerWeek = hoursPerWeek;
		this.hourlyRate = hourlyRate;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#getSurname()
	 */
	public String getSurname() throws RemoteException {
		return sname;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#getForename()
	 */
	public String getForename() throws RemoteException {
		return fname;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#getNumberHours()
	 */
	public int getNumberHours() throws RemoteException
	{
		return hoursPerWeek;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#getHourlyRate()
	 */
	public double getHourlyRate() throws RemoteException {
		return hourlyRate;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#getWeeklyEarning()
	 */
	public double getWeeklyEarning() throws RemoteException {
		return hoursPerWeek * hourlyRate;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#setForename(java.lang.String)
	 */
	public void setForename(String s) throws RemoteException {
		fname = s;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#setNumberHours(int)
	 */
	public void setNumberHours(int n) throws RemoteException {
		hoursPerWeek = n;
	}

	/* (non-Javadoc)
	 * @see sc10dw.distributed.cw2.Employee#setHourlyRate(double)
	 */
	public void setHourlyRate(double r) throws RemoteException {
		hourlyRate = r;
	}
	
	/* (non-Javadoc)
	 * @see java.rmi.server.RemoteObject#toString()
	 */
	@Override
	public String toString() {
		return String.format("Employee (%s %s, %d, %.2f)",
			fname, sname, hoursPerWeek, hourlyRate);
	}
	
	/**
	 * 
	 */
	private String fname;
	/**
	 * 
	 */
	private String sname;
	/**
	 * 
	 */
	private int hoursPerWeek;
	/**
	 * 
	 */
	private double hourlyRate;
	
}
