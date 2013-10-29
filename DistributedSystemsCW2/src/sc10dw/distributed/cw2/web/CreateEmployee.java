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

/**
 * @author sc10dw
 *
 */
public class CreateEmployee extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String title = "";
		String content = "";
		try {
			// Create employee and redirect client to that employee's information page
			String surname = request.getParameter("surname");
			double hourlyRate = Double.parseDouble( request.getParameter("hourlyRate") );
			int hoursPerWeek = Integer.parseInt( request.getParameter("hoursPerWeek") );
			
			createEmployee(request.getParameter("forename"),
					surname, hourlyRate, hoursPerWeek);
			title = "Employee Created";
			content = "<p>Employee with given details has been successfully created.</p>";
			content += "\t\t<a href='" + Config.ROOT_URL + "'><button type='button'>Back to Main Page</button></a>";
		} catch (Exception ex) {
			// Return error message if they employee could not be created
			title = "Employee Could Not Be Created";
			content = "\t\t<p>A server-side error occurred when attempting to create the specified employee:</p>";
			content += "\t\t<p>" + ex.getMessage() + "</p>";
			content += "\t\t<a href='" + Config.ROOT_URL + "'><button type='button'>Back to Main Page</button></a>";
		}
		
		response.setContentType(Config.HTTP_CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println("<html>\n\n<head>\n\t<title>" + title + "</title>\n</head>\n\n");
		out.println("\t<body><h1>" + title + "</h1>");
		out.println(content);
		out.println("\t</body>\n\n</html>");
		out.close();
	}
	
	/**
	 * @param forename
	 * @param surname
	 * @param hourlyRate
	 * @param hoursPerWeek
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	/**
	 * @param forename
	 * @param surname
	 * @param hourlyRate
	 * @param hoursPerWeek
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private Employee createEmployee(String forename, String surname,
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
