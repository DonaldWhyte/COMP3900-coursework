package sc10dw.distributed.cw2;

/* Interface for generating employee servers. */
public interface EmployeeServerFactory {

	public EmployeeServer createEmployeeServer(String objectName, String surname);
	
}
