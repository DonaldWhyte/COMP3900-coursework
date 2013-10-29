package sc10dw.distributed.cw2;

import java.rmi.*;

/**
 * @(#)Employee.java
 * @author Karim Djemame
 * October 2013
 */

/**
 * @author sc10dw
 *
 */
public interface Employee extends Remote {

   /**
 * @return
 * @throws RemoteException
 */
String getSurname() throws RemoteException;
   /**
 * @return
 * @throws RemoteException
 */
String getForename() throws RemoteException;
   /**
 * @return
 * @throws RemoteException
 */
int getNumberHours() throws RemoteException;
   /**
 * @return
 * @throws RemoteException
 */
double getHourlyRate() throws RemoteException;
   /**
 * @return
 * @throws RemoteException
 */
double getWeeklyEarning() throws RemoteException;
   /**
 * @param s
 * @throws RemoteException
 */
void setForename(String s) throws RemoteException;
   /**
 * @param n
 * @throws RemoteException
 */
void setNumberHours(int n) throws RemoteException;
   /**
 * @param r
 * @throws RemoteException
 */
void setHourlyRate(double r) throws RemoteException;
}
   
