package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import model.*;

public class PatientSearchServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		ArrayList<Patient> patients;
		ArrayList<Reception> receptions;
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		patients = new ArrayList<Patient>();
		receptions = new ArrayList<Reception>();
		switch(request.getParameterValues("attribute")[0])
		{
		case "number":
			patients = Patient.idSearch(request.getParameterValues("keyword")[0]);
			receptions = Reception.idSearch(request.getParameterValues("keyword")[0]);
		        break;
		case "name":
			patients = Patient.nameSearch(request.getParameterValues("keyword")[0]);
			receptions = Reception.nameSearch(request.getParameterValues("keyword")[0]);
			break;
		case "symptom":
			patients = Patient.symptomSearch(request.getParameterValues("keyword")[0]);
			receptions = Reception.symptomSearch(request.getParameterValues("keyword")[0]);
			break;
		}
		request.setAttribute("patients",patients);
		request.setAttribute("receptions",receptions);
		request.getRequestDispatcher("/view/PatientSearch.jsp").forward(request, response);
	}
}
