package model;

import java.lang.Boolean;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Patient
{
	private String patientID;	//患者ID
	private String fName;		//名
	private String lName;		//氏
	private String fNameKana;	//名フリガナ
	private String lNameKana;	//氏フリガナ
	private int sexID;		//性別ID
	private Calendar birthday;	//生年月日
	private int bloodTypeID;	//血液型ID
	private String zipCode;		//郵便番号
	private String address;		//住所
	private String clinicalRecord;	//病歴

	public Patient()
	{
		patientID = "00000";
		fName = "";
		lName = "";
		fNameKana = "";
		lNameKana = "";
		sexID = 0;
		birthday = Calendar.getInstance();
		bloodTypeID = 0;
		zipCode = "";
		address = "";
		clinicalRecord = "";
	}

	public String getPatientID()
	{
		return patientID;
	}

	public String getFName()
	{
		return fName;
	}

	public String getLName()
	{
		return lName;
	}

	public String getFNameKana()
	{
		return fNameKana;
	}

	public String getLNameKana()
	{
		return lNameKana;
	}

	public Sex getSex()
	{
		switch(sexID)
		{
		case 1:
			return Sex.male;
		default:
			return Sex.female;
		}
	}

	public Calendar getBirthday()
	{
		return birthday;
	}

	public BloodType getBloodType()
	{
		switch(bloodTypeID)
		{
		case 1:
			return BloodType.A;
		case 2:
			return BloodType.B;
		case 3:
			return BloodType.O;
		default:
			return BloodType.AB;
		}
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public String getAddress()
	{
		return address;
	}

	public String getClinicalRecord()
	{
		return clinicalRecord;
	}

	public void setPatientID(String newPatientID)
	{
		patientID = newPatientID;
	}

	public void setFName(String newFName)
	{
		fName = newFName;
	}

	public void setLName(String newLName)
	{
		lName = newLName;
	}

	public void setFNameKana(String newFNameKana)
	{
		fNameKana = newFNameKana;
	}

	public void setLNameKana(String newLNameKana)
	{
		lNameKana = newLNameKana;
	}

	public void setSex(Sex newSex)
	{
		switch(newSex)
		{
		case male:
			sexID = 1;
			break;
		case female:
			sexID = 2;
		}
	}

	public void setBirthday(Calendar newBirthday)
	{
		birthday = newBirthday;
	}

	public void setBloodType(BloodType newBloodType)
	{
		switch(newBloodType)
		{
		case A:
			bloodTypeID = 1;
		case B:
			bloodTypeID = 2;
		case O:
			bloodTypeID = 3;
		case AB:
			bloodTypeID = 4;
		}
	}

	public void setZipCode(String newZipCode)
	{
		zipCode = newZipCode;
	}

	public void setAddress(String newAddress)
	{
		address = newAddress;
	}

	public void setClinicalRecord(String newClinicalRecord)
	{
		clinicalRecord = newClinicalRecord;
	}

	public static ArrayList<Patient> getAllPatients()
	{
		ArrayList<Patient> result = new ArrayList<Patient>();
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/notatummedicum";
		String user = "notatummedicum";
		String password = "notatummedicum";
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
		try
		{
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "select * from patient;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Patient nextPatient = new Patient();
				nextPatient.patientID = rs.getString("patientid");
				nextPatient.fName = rs.getString("fname");
				nextPatient.lName = rs.getString("lname");
				nextPatient.fNameKana = rs.getString("fnamekana");
				nextPatient.lNameKana = rs.getString("lnamekana");
				nextPatient.sexID = rs.getInt("sexid");
				nextPatient.birthday.setTime(rs.getDate("birthday"));
				nextPatient.bloodTypeID = rs.getInt("bloodtypeid");
				nextPatient.zipCode = rs.getString("zipcode");
				nextPatient.address = rs.getString("address");
				nextPatient.clinicalRecord = rs.getString("clinicalrecord");
				result.add(nextPatient);
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
		return result;
	}

	public static ArrayList<Patient> idSearch(String id)
	{
		ArrayList<Patient> result = new ArrayList<Patient>();
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/notatummedicum";
		String user = "notatummedicum";
		String password = "notatummedicum";
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
		try
		{
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "select * from patient where patientid = '"+id+"';";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Patient nextPatient = new Patient();
				nextPatient.patientID = rs.getString("patientid");
				nextPatient.fName = rs.getString("fname");
				nextPatient.lName = rs.getString("lname");
				nextPatient.fNameKana = rs.getString("fnamekana");
				nextPatient.lNameKana = rs.getString("lnamekana");
				nextPatient.sexID = rs.getInt("sexid");
				nextPatient.birthday.setTime(rs.getDate("birthday"));
				nextPatient.bloodTypeID = rs.getInt("bloodtypeid");
				nextPatient.zipCode = rs.getString("zipcode");
				nextPatient.address = rs.getString("address");
				nextPatient.clinicalRecord = rs.getString("clinicalrecord");
				result.add(nextPatient);
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
		return result;
	}

	public static ArrayList<Patient> nameSearch(String name)
	{
		ArrayList<Patient> result = new ArrayList<Patient>();
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/notatummedicum";
		String user = "notatummedicum";
		String password = "notatummedicum";
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
		try{
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "select * from patient where fname like '%" + name + "%' or lname like '%" + name + "%' or fnamekana like '%" + name + "%' or lnamekana like '%" + name +"%';";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Patient nextPatient = new Patient();
				nextPatient.patientID = rs.getString("patientid");
				nextPatient.fName = rs.getString("fname");
				nextPatient.lName = rs.getString("lname");
				nextPatient.fNameKana = rs.getString("fnamekana");
				nextPatient.lNameKana = rs.getString("lnamekana");
				nextPatient.sexID = rs.getInt("sexid");
				nextPatient.birthday.setTime(rs.getDate("birthday"));
				nextPatient.bloodTypeID = rs.getInt("bloodtypeid");
				nextPatient.zipCode = rs.getString("zipcode");
				nextPatient.address = rs.getString("address");
				nextPatient.clinicalRecord = rs.getString("clinicalrecord");
				result.add(nextPatient);
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
		return result;
	}

	public static ArrayList<Patient> symptomSearch(String symptom)
	{
		ArrayList<Patient> result = new ArrayList<Patient>();
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/notatummedicum";
		String user = "notatummedicum";
		String password = "notatummedicum";
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
		try{
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "select * from patient where patientid in (select patientid from karte where symptom like '%" + symptom + "%' or symptomnote like '%" + symptom + "%');";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Patient nextPatient = new Patient();
				nextPatient.patientID = rs.getString("patientid");
				nextPatient.fName = rs.getString("fname");
				nextPatient.lName = rs.getString("lname");
				nextPatient.fNameKana = rs.getString("fnamekana");
				nextPatient.lNameKana = rs.getString("lnamekana");
				nextPatient.sexID = rs.getInt("sexid");
				nextPatient.birthday.setTime(rs.getDate("birthday"));
				nextPatient.bloodTypeID = rs.getInt("bloodtypeid");
				nextPatient.zipCode = rs.getString("zipcode");
				nextPatient.address = rs.getString("address");
				nextPatient.clinicalRecord = rs.getString("clinicalrecord");
				result.add(nextPatient);
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
		return result;
	}

	public int getAge()
	{
		Calendar now = Calendar.getInstance();
		boolean beforeBirthday;
		if(now.get(Calendar.MONTH) < birthday.get(Calendar.MONTH))beforeBirthday = true;
		else if(now.get(Calendar.MONTH) > birthday.get(Calendar.MONTH))beforeBirthday = false;
		else if(now.get(Calendar.DATE) < birthday.get(Calendar.DATE))beforeBirthday = true;
		else if(now.get(Calendar.DATE) > birthday.get(Calendar.DATE))beforeBirthday = false;
		else beforeBirthday = false;
		return Calendar.getInstance().get(Calendar.YEAR) - birthday.get(Calendar.YEAR) - (beforeBirthday ? 1 : 0);
	}

	public void reception()
	{
		String receptID;
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/notatummedicum";
		String user = "notatummedicum";
		String password = "notatummedicum";
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
		try{
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			ResultSet rs;
			String sql;
			for(int id1 = 0; id1 < 9; id1++)for(int id2 = 0; id2 < 9; id2++)for(int id3 = 0; id3 < 9; id3++)for(int id4 = 0; id4 < 9; id4++)for(int id5 = 0; id5 < 9; id5++)
			{
				receptID = String.valueOf(id1) + String.valueOf(id2) + String.valueOf(id3) + String.valueOf(id4) + String.valueOf(id5);
				if(receptID.equals("00000"))continue;
				sql = "select receptid from reception where receptid = '" + receptID + "';";
				rs = stmt.executeQuery(sql);
				if(!rs.next())
				{
					sql = "insert into reception values('" + receptID + "','" + patientID + "');";
					stmt.executeUpdate(sql);
					return;
				}
				rs.close();
			}
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
	}
}
