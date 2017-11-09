package test.Patient;

import model.*;

class Reception
{
	public static void main(String[] args)
	{
		Patient.idSearch(args[0]).get(0).reception();
	}
}
