package ie.dit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TuneFinder
{
	public TuneFinder()
	{
		
	}

	public void printTunes()
	
	{
		Connection conn=null;
		PreparedStatement statment=null;
		ResultSet results=null;
		
		try
		{		//ctrl+shit_b created break point
			Class.forName("org.sqlite.JDBC"); //searches for the connection
			conn=DriverManager.getConnection("jdbc:sqlite:tunes.sqlite");  //connect to DB file
			statment=conn.prepareStatement("Select * from tunes"); //SQL command.
			statment.executeQuery(); //exceute command
			results= statment.executeQuery();
			while(results.next())		//print results
			{
				Tune tune=new Tune();
				tune.setSearchKey(results.getString("search_key"));
				tune.setTitle(results.getString("title"));
				tunes.add(tune);
				//System.out.println(results.getString("title"));
				//System.out.println(results.getString("search_key"));

			}
			
		}
		catch(ClassNotFoundException e)  //file not found exception 
		{
		 System.out.println("cant load the driver");
		 e.printStackTrace();
		}
		catch(SQLException e)  //if the database file not found
		{
			System.out.println("cant find database");
			 e.printStackTrace();
		}
		finally  //closing connectiong
		{
			if(conn !=null)
			{
				try
				{
				conn.close();
				}
				catch(SQLException e)  //error in closing the connecting
				{
					System.out.println("cloud not close database");
					e.printStackTrace();
				}
				
			}
		}
	}
}

