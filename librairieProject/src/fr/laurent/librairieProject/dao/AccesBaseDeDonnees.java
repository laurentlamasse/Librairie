package fr.laurent.librairieProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesBaseDeDonnees {

	private static String url = "jdbc:mysql://localhost/librairie?autoReconnect=true&useSSL=false";
	private static String login = "root";
	private static String passwd = "";
	private Connection cn;
	private Statement st;

	public AccesBaseDeDonnees()
	{
		
	}
	
	private boolean initConnection()
	{
		try
		{
			//ETAPE 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			//ETAPE 2 : Recuperation de la connexion
			cn = DriverManager.getConnection(url, login, passwd);
			//ETAPE 3 : Creation d'un statement
			st = cn.createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean closeConnection()
	{
		try
		{
			//ETAPE 5 : liberer les ressources de la memoire
			cn.close();
			st.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ResultSet executeSelect(String query)
	{
		ResultSet rs = null;
		if(initConnection())
		{
			try
			{
				//ETAPE 4 : Execution requete
				rs = st.executeQuery(query);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				closeConnection();
			}
		}
		return rs;
	}

	
	public boolean executeInsert(String table, ArrayList<String> champs, ArrayList<Object> values)
	{
		if(table != null && table != "" && values != null && !values.isEmpty())
		{
			String query = createQueryInsert(table, champs, values);
			if(initConnection())
			{
				try
				{
					//ETAPE 4 : Execution requete
					st.executeUpdate(query);
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				finally
				{
					closeConnection();
					return true;
				}
			}
		}
		return false;
	}
	
	private String createQueryInsert(String table, ArrayList<String> champs, ArrayList<Object> values)
	{
		String query = "INSERT INTO `"+table+"` ";
		if(champs != null && !champs.isEmpty())
		{
			query+= "("+champs.get(0);
			for(int i=1; i<champs.size(); i++)
			{
				query += ", "+champs.get(i);
			}
			query+= ") ";
		}
		query += "VALUES (" + values.get(0).toString();
		for(int i=1; i<values.size(); i++)
		{
			query += ", "+champs.get(i).toString();
		}
		query+= ");";
		return query;
	}

}
