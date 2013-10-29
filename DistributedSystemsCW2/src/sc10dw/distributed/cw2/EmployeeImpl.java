package sc10dw.distributed.cw2;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

/**
 * @(#)EmployeeImpl.java
 * @author Karim Djemame
 * October 2013
 */
public class EmployeeImpl extends UnicastRemoteObject implements Employee {

  /**
   * Create new instance of Employee
   * @param str Surname to give to newly created employee
   * @throws RemoteException
   */
   public EmployeeImpl(String str) throws RemoteException {
	surname = str; 
   }

   public String getSurname() throws RemoteException {
	return surname;
   }

   public String getForename() throws RemoteException {
	return forename;
   }

   public int getNumberHours() throws RemoteException {
	return numberHours;
   }

   public double getHourlyRate() throws RemoteException {
	return hourlyRate;
   }

   public double getWeeklyEarning() throws RemoteException {
	return hourlyRate * numberHours;
   }

   public void setForename(String s) throws RemoteException {
	forename = s;
   }

   public void setNumberHours(int n) throws RemoteException {
	numberHours = n;
   }

   public void setHourlyRate(double r) throws RemoteException {
	hourlyRate = r;	
   }

   private String surname = "";         // employee surname    
   private String forename = "";         // employee forename
   private int numberHours = 0;   // number of weekly hours
   private double hourlyRate = 0.0;   // hourly rate
   
}
