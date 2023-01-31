package wandui.util;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import properties.RunSettings;
import wandui.dataOne.Person;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;
public class DatabaseManager {
	
	 public static Connection connectionWithOracle()  {
	        Connection con=null;
	        try {
	            Class.forName(RunSettings.driverClassName);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }


	        try {
	            con = DriverManager.getConnection(RunSettings.dbUrl,RunSettings.dbUsername,RunSettings.dbPassword);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        System.out.println("connection successfully");

	        return con;
	    }
	    public static void ExecuteQuery(String Query) throws ClassNotFoundException {
	        Statement stmt = null;

	        try {
	            stmt = connectionWithOracle().createStatement();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        try {
	            stmt.executeQuery(Query);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    public static HashMap<String, String> getRowData(String query) {

	        Statement stmt = null;

	        ResultSet rs = null;
	        HashMap<String, String> rData = new HashMap<String, String>();

	        try {

	            stmt = connectionWithOracle().createStatement();
	            rs = stmt.executeQuery(query);

	            if (rs.next()) {
	                //ResultSet data = rs;
	                int cCount = rs.getMetaData().getColumnCount();

	                if (cCount > 0) {
	                    for (int i = 1; i <= cCount; i++) {
	                        rData.put(rs.getMetaData().getColumnName(i), (String) (rs.getString(i)));
	                    }
	                }
	                rs.close();
	                return rData;
	            } else {
	                rs.close();
	                return null;
	            }

	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }

	        return null;
	    }

	    public static List<Map<String, String>> getRowsData(String query) {

	        Statement stmt = null;

	        ResultSet rs = null;
	        HashMap<String, String> rData = new HashMap<String, String>();

	        try {
	            List<Map<String, String>> rdatas = new ArrayList<Map<String, String>>();
	            stmt = connectionWithOracle().createStatement();
	            rs = stmt.executeQuery(query);

	            while (rs.next())
	            {
	                //ResultSet data = rs;

	                int cCount = rs.getMetaData().getColumnCount();
	                rData = new HashMap<String, String>();
	                if (cCount > 0)
	                {
	                    for (int i = 1; i <= cCount; i++) {
	                        rData.put(rs.getMetaData().getColumnName(i), (String) (rs.getString(i)));
	                    }
	                    rdatas.add(rData);
	                }



	            }
	            rs.close();
	            return rdatas;



	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }


	        return null;
	    }

	    public static String getQueryResultByParameters(String sqlQuery, String... parameters) {
	        String firstValue = "";
	        PreparedStatement statement = null;
	        Connection con=connectionWithOracle();


	        try {
	            statement =con.prepareStatement(sqlQuery);
	            for (int i = 0; i < parameters.length; i++) {
	                statement.setString(i + 1, parameters[i]);
	            }
	            ResultSet result = statement.executeQuery();
	            while (result.next()) {
	                firstValue = result.getString(1);
	                break;
	            }
	            if(con!=null)
	            {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }




	        return firstValue;
	    }
	    public static List<Person> getPersonListFromDatabas(String selectQuery, String queryTemplate, String clienid)
	    {
	        String query = format(queryTemplate, selectQuery);
	        List<Person> personList=null;
	        Connection con=null;
	        try
	        {
	            con=connectionWithOracle();
	            QueryRunner run =new QueryRunner();
	            ResultSetHandler<List<Person>> h =new BeanListHandler<>(Person.class);
	            personList=run.query(con,query,h,clienid);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally {

	            if (con != null) {
	                try {
	                    con.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return personList;
	    }

}
