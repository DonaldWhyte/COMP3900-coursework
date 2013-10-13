package sc10dw.distributed.cw1;

public class Employee {

	public Employee(String fname, String sname, int avgNumberHours, double basePay) { 
		this.fname = fname;
		this.sname = sname;
		this.avgNumberHours = avgNumberHours;
		this.basePay = basePay;
	}
	
	public String forename() {
		return fname;
	}
	
	public String surname() {
		return sname;
	}
	
	public int averageHours() {
		return avgNumberHours;
	}
	
	public double basePayRate() {
		return basePay;
	}
	
	private String fname;
	private String sname;
	private int avgNumberHours;
	private double basePay;
	
}
