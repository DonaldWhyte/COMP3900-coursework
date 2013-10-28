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
    private String surname = "";         // employee surname    
    private String forename = "";         // employee forename
    private int numberHours = 0;   // number of weekly hours
    private double hourlyRate = 0.0;   // hourly rate


    // Create a new Employee with the given surname

   public EmployeeImpl(String str) throws RemoteException {
	surname = str; 
   }

    // return the Employee's surname
   public String getSurname() throws RemoteException {
	return surname;
   }

    // return the Employee's forename

   public String getForename() throws RemoteException {
	return forename;
   }

      // return the Employee's number of weekly hours

   public int getNumberHours() throws RemoteException {
	return numberHours;
   }

      // return the Employee's hourly rate

   public double getHourlyRate() throws RemoteException {
	return hourlyRate;
   }

      // return the Employee's weekly earning

   public double getWeeklyEarning() throws RemoteException {
	return hourlyRate * numberHours;
   }

      // set the Employee's forename

   public void setForename(String s) throws RemoteException {
	forename = s;
   }

      // set the Employee's number of weekly hours

   public void setNumberHours(int n) throws RemoteException {
	numberHours = n;
   }

      // set the Employee's hourly rate

   public void setHourlyRate(double r) throws RemoteException {
	hourlyRate = r;	
   }

}
