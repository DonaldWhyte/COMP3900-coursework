package sc10dw.distributed.cw2;

import java.rmi.*;

/**
 * @(#)Employee.java
 * @author Karim Djemame
 * October 2013
 */

public interface Employee extends Remote {

   String getSurname() throws RemoteException;
   String getForename() throws RemoteException;
   int getNumberHours() throws RemoteException;
   double getHourlyRate() throws RemoteException;
   double getWeeklyEarning() throws RemoteException;
   void setForename(String s) throws RemoteException;
   void setNumberHours(int n) throws RemoteException;
   void setHourlyRate(double r) throws RemoteException;
}
   
