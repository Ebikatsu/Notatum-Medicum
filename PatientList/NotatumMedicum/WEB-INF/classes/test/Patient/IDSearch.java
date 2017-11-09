package test.Patient;

import model.*;

class IDSearch
{
	public static void main(String[] args)
	{
		Patient patient = Patient.idSearch(args[0]);
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
	}
}
