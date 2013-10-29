package sc10dw.distributed.cw2.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;
import javax.servlet.ServletException;

import sc10dw.distributed.cw2.*;

public class EmployeeInformation extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType(Config.HTTP_CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		
		String employeeObjectName = request.getParameter("surname");
		List<Employee> employees = getEmployees(employeeObjectName);
		if (employees.size() > 0) {
			out.println("<html>\n\n<head>\n\t<title>Employee Information</title>\n</head>\n\n");
			out.println("\t<body>");
			out.println("\t\t<h1>" + employees.size() + " Employee(s) Found</h1>");
			out.println("\t\t<table>");
			for (Employee emp : employees) {
				out.println("\t\t<tr><th>Name</th><td>" + emp.getForename() + " " + emp.getSurname() + "</td></tr>");
				out.println("\t\t<tr><th>Hourly Rate</th><td>" + emp.getHourlyRate() + "</td></tr>");
				out.println("\t\t<tr><th>Hours per Week</th><td>" + emp.getNumberHours() + "</td></tr>");
				out.println("\t\t<tr><th>Total Earnings per Week</th><td>" + emp.getWeeklyEarning() + "</td></tr>");
				out.println("\t\t<tr><tr>");
			}
			out.println("\t\t</table>");
			out.println("\t\t<a href='" + Config.ROOT_URL + "'><button type='button'>Back to Main Page</button></a>");
			out.println("\t</body>\n\n</html>"); 
		} else {
			out.println("<html>\n\n<head>\n\t<title>Employee Not Found</title>\n</head>\n\n");
			out.println("\t<body>\n\t\t<h1>Employee Not Found</h1>");
			out.println("\t\t<p>Employee with object name '" + employeeObjectName + "' not found.</p>");
			out.println("\t\t<a href='" + Config.ROOT_URL + "'><button type='button'>Back to Main Page</button></a>");
			out.println("\t</body>\n\n</html>");
		}
		
		out.close();
	}
	
	private List<Employee> getEmployees(String surname) {
		try {
			Registry registry = LocateRegistry.getRegistry();
			EmployeeFactory employeeFactory = (EmployeeFactory)registry.lookup("employee_factory");
			return employeeFactory.getEmployee(surname);
		} catch (Exception e) {
			return new ArrayList<Employee>();
		}
		
	}
	
}
