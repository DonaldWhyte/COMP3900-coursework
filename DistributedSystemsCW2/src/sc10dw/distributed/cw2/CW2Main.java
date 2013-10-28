package sc10dw.distributed.cw2;

/* Class which solely contains entry point to server factory application. */
public class CW2Main {

	public static void main(String[] args) {
		EmployeeServerFactory serverFactory = new EmployeeServerFactoryImpl();
		EmployeeServer serverOne = serverFactory.createEmployeeServer("don", "Whyte");
		EmployeeServer serverTwo = serverFactory.createEmployeeServer("andy", "Campbell");
		serverOne.start();
		serverTwo.start();
	}
	
}