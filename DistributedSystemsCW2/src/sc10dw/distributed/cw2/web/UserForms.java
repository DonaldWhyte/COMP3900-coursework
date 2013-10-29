package sc10dw.distributed.cw2.web;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author sc10dw
 *
 */
public class UserForms extends HttpServlet {


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType(Config.HTTP_CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		out.println("<html>\n\n<head>\n\t<title>Employee Payroll System</title>\n</head>\n\n");
		out.println("\t<body>");
		out.println("\t\t<h1>Search Employees</h1>");
		out.println("\t\t<form action='" + Config.ROOT_URL + "/employee' method='GET'>");
		out.println("\t\tEmployee Surname: <input type='text' name='surname' /><br />");
		out.println("\t\t\t<input type='submit' value='Search' />");
		out.println("\t\t</form>");
		out.println("\t\t<h1>Create Employee</h1>");
		out.println("\t\t<form action='" + Config.ROOT_URL + "/create' method='POST'>");
		out.println("\t\t\t<table>");
		out.println("\t\t\t<tr><td>Forename</td><td><input type='text' name='forename' /></td></tr>");
		out.println("\t\t\t<tr><td>Surname</td><td><input type='text' name='surname' /></td></tr>");
		out.println("\t\t\t<tr><td>Hourly Pay Rate</td><td><input type='text' name='hourlyRate' /></td></tr>");
		out.println("\t\t\t<tr><td>Number of Hours per Week</td><td><input type='text' name='hoursPerWeek' /></td></tr>");
		out.println("\t\t\t<tr><td><input type='submit' value='Create' /></td></tr>");
		out.println("\t\t\t</table>");
		out.println("\t\t</form>");
		out.println("</body>\n\n</html>");
		
		out.close();
	}
	
}
