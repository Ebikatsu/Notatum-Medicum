package test.Reception;

import java.util.ArrayList;
import model.*;

class GetAllReceptions
{
	public static void main(String[] args)
	{
		ArrayList<Reception> allReceptions = Reception.getAllReceptions();
		for(Reception reception : allReceptions)
		{
			Patient patient = Patient.idSearch(reception.getPatientID());
			System.out.println(reception.getReceptID());
			System.out.println(reception.getPatientID());
			System.out.println(patient.getLName());
			System.out.println(patient.getLNameKana());
			System.out.println(patient.getFName());
			System.out.println(patient.getFNameKana());
			System.out.println(patient.getSex());
			System.out.println(patient.getBirthday());
			System.out.println("");
		}
	}
}
