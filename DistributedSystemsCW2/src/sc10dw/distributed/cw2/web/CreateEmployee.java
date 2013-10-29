package sc10dw.distributed.cw2.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.servlet.http.*;
import javax.servlet.ServletException;

import sc10dw.distributed.cw2.*;

public class CreateEmployee extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {		
		try {
			// Create employee and redirect client to that employee's information page
			String employeeObjectName = request.getParameter("id");
			double hourlyRate = Double.parseDouble( request.getParameter("hourlyRate") );
			int hoursPerWeek = Integer.parseInt( request.getParameter("hoursPerWeek") );
			
			createEmployee(employeeObjectName,
					request.getParameter("forename"),
					request.getParameter("surname"),
					hourlyRate,
					hoursPerWeek);
			response.sendRedirect("/employee?id=" + employeeObjectName);
		} catch (Exception ex) {
			// Return error message if they employee could not be created
			response.setContentType(Config.HTTP_CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println("<html>\n\n<head>\n\t<title>Employee Could Not Be Created</title>\n</head>\n\n");
			out.println("\t<body>\t\t<h1>Employee Could Not Be Created</h1>");
			out.println("\t\t<p>A server-side error occurred when attempting to create the specified employee:</p>");
			out.println("\t\t<p>" + ex.getMessage() + "</p>");
			out.println("\t\t<a href='" + Config.ROOT_URL + "'><button type='button'>Back to Main Page</button></a>");
			
			out.println("\t</body>\n\n</html>");
			out.close();
		}
	}
	
	private Employee createEmployee(String objectName, String forename, String surname,
		double hourlyRate, int hoursPerWeek) throws RemoteException, NotBoundException {
		// Get employee factory from server using RMI
		Registry registry = LocateRegistry.getRegistry();
		EmployeeFactory employeeFactory = (EmployeeFactory)registry.lookup("employee_factory");
		Employee newEmployee = employeeFactory.createEmployee(surname);
		
		newEmployee.setForename(forename);
		newEmployee.setHourlyRate(hourlyRate);
		newEmployee.setNumberHours(hoursPerWeek);
		
		return newEmployee;
	}
	
}
