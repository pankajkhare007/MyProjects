package com.demo.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MSAccessConnection {
	static ResultSet rs=null;
	public static ResultSet getRecords(String sQuery)
	{
		rs=null;
		try
		{
			String dBLocation = System.getProperty("user.dir")+"\\TestData\\AutomationPractice.accdb";
			Connection con= DriverManager.getConnection("jdbc:ucanaccess://"+dBLocation);
			System.out.println("Database Connection successfull");
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			 rs=stmt.executeQuery(sQuery);  
			 if(con!=null)
				 con.close();
			
			rs.next();
			//System.out.println(rs.getString("UserName"));
			
			return rs;
			

		}
		catch(Exception e)
		{
			System.out.println("Database Connection unsuccessfull");
			e.printStackTrace();
			return rs;
		}
		
	}


}
