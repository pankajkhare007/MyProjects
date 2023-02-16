package utilities.dataManagers;

import utilities.web.WebRunSettings;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DataBaseManager {

    public static Connection connectionWithOracle() {

        Connection con;
        try {
            Class.forName(WebRunSettings.driverClassName);
            con= DriverManager.getConnection(WebRunSettings.dbUrl,WebRunSettings.dbUsername,WebRunSettings.dbPassword);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("connection successfully");

        return con;
    }
    public static void executeQuery(String Query) {
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
}
