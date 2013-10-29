package sc10dw.distributed.cw2;

import java.rmi.*;

/**
 * @author Karim Djemame
 * October 2013
 * Represents single employee in payroll system.
 */
public interface Employee extends Remote {

	/**
  	* @return Surname of employee 
 	* @throws RemoteException if problem accessing remote object
 	*/
	String getSurname() throws RemoteException;
   /**
    * @return Forename of employee
 	* @throws RemoteException if problem accessing remote object
 	*/
	String getForename() throws RemoteException;
   /**
    * @return Number of hours employee works per week
 	* @throws RemoteException if problem accessing remote object
 	*/
	int getNumberHours() throws RemoteException;
   /**
    * @return Amount employee gets paid per hour
 	* @throws RemoteException if problem accessing remote object
 	*/
	double getHourlyRate() throws RemoteException;
   /**
    * @return Amount of money employee earns per week
    * @throws RemoteException if problem accessing remote object
 		*/
	double getWeeklyEarning() throws RemoteException;
   /**
    * @param s New forename to give employee
    * @throws RemoteException if problem accessing remote object
 	*/
	void setForename(String s) throws RemoteException;
   /**
    * @param n New amount of hours per week employee works
    * @throws RemoteException if problem accessing remote objectn
    */
	void setNumberHours(int n) throws RemoteException;
   /**
    * @param r New hourly pay rate employee has
    * @throws RemoteException if problem accessing remote object
    */
	void setHourlyRate(double r) throws RemoteException;

}
   
