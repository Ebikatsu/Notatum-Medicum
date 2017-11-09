package model;

import java.sql.*;
import java.util.ArrayList;

public class Reception
{
	private String receptID;
	private String patientID;

	public Reception()
	{
		receptID = "00000";
		patientID = "00000";
	}

	public String getReceptID()
	{
		return receptID;
	}

	public String getPatientID()
	{
		return patientID;
	}

	public void setReceptID(String newReceptID)
	{
		receptID = newReceptID;
	}

	public void setPatientID(String newPatientID)
	{
		patientID = newPatientID;
	}

	public static ArrayList<Reception> getAllReceptions()
	{
		ArrayList<Reception> result = new ArrayList<Reception>();
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
			String sql = "select * from reception;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Reception nextReception = new Reception();
				nextReception.receptID = rs.getString("receptid");
				nextReception.patientID = rs.getString("patientid");
				result.add(nextReception);
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

	public static ArrayList<Reception> idSearch(String id)
	{
		ArrayList<Reception> result = new ArrayList<Reception>();
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
			String sql = "select * from reception where patientid = '" + id +"';";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Reception nextReception = new Reception();
				nextReception.receptID = rs.getString("receptid");
				nextReception.patientID = rs.getString("patientid");
				result.add(nextReception);
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

	public static ArrayList<Reception> nameSearch(String name)
	{
		ArrayList<Reception> result = new ArrayList<Reception>();
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
			String sql = "select * from reception where patientid in (select patientid from patient where fname like '%" + name + "%' or lname like '%" + name + "%' or fnamekana like '%" + name + "%' or lnamekana like '%" + name +"%');";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Reception nextReception = new Reception();
				nextReception.receptID = rs.getString("receptid");
				nextReception.patientID = rs.getString("patientid");
				result.add(nextReception);
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

	public static ArrayList<Reception> symptomSearch(String symptom)
	{
		ArrayList<Reception> result = new ArrayList<Reception>();
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
			String sql = "select * from reception where patientid in (select patientid from karte where symptom like '%" + symptom + "%' or symptomnote like '%" + symptom + "%');";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Reception nextReception = new Reception();
				nextReception.receptID = rs.getString("receptid");
				nextReception.patientID = rs.getString("patientid");
				result.add(nextReception);
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
}
