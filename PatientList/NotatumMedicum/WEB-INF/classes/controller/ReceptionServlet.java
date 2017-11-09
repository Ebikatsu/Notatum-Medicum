package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import model.*;

public class ReceptionServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Patient.idSearch(request.getParameterValues("patientid")[0]).get(0).reception();
		/*
		try
		{
			File file = new File("/home/taisei/git/Assignment/syspj3/NotatumMedicum/log.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(request.getParameterValues("patientid")[0]);
			fileWriter.close();
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
		*/
		request.getRequestDispatcher("/view/PatientList.jsp").forward(request, response);
	}
}
