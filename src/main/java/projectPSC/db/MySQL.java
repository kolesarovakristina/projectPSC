package projectPSC.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySQL
{
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/projectpsc";
    private String username = "root";
    String password = "";

    public List<String> getCity(String City) throws SQLException {
        List<String> list = new ArrayList<String>();
        try {
            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT PSC FROM psc WHERE City like  '" + City + "'";
            System.out.println(query);
            PreparedStatement ps = conn.prepareStatement(query);


            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                String result = rs.getString("PSC");

                list.add(result);


                System.out.println(result);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }


        return list;

    }

    public List<String> getCityFromPSC(String PSC) throws SQLException {
        List<String> list2 = new ArrayList<String>();
        try {
            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(url, username, password);
        ;



            String query = "SELECT City FROM psc WHERE PSC like '" +PSC+ "'";
            System.out.println(query);
            PreparedStatement ps = conn.prepareStatement(query);


            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                String result2 = rs.getString("City");

                list2.add(result2);


                System.out.println(result2);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }


        return list2;

    }




}
