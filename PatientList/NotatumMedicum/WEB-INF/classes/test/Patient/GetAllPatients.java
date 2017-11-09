package test.Patient;

import java.util.ArrayList;
import model.*;

class GetAllPatients
{
	public static void main(String[] args)
	{
		ArrayList<Patient> allPatients = Patient.getAllPatients();
		for(Patient patient : allPatients)
		{
			System.out.println(patient.getPatientID());
			System.out.println(patient.getFName());
			System.out.println(patient.getLName());
			System.out.println(patient.getFNameKana());
			System.out.println(patient.getLNameKana());
			System.out.println(patient.getSex());
			System.out.println(patient.getBirthday());
			System.out.println(patient.getBloodType());
			System.out.println(patient.getZipCode());
			System.out.println(patient.getAddress());
			System.out.println(patient.getClinicalRecord());
			System.out.println("");
		}
	}
}
