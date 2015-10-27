/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ywca_database.UseSQL;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ywca_database.YWCA_DatabaseZeus;

/**
 *
 * @author Kyle
 */
public class SQL_Access {

    private static String Query;

    public static void viewTable(Connection con, String Query, int passNum)
            throws SQLException {
        try {
            SQL_Access.Query = Query;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_Access.Query);
            String pass = "";
            ResultSetMetaData rsmd = rs.getMetaData();
            int cCount = rsmd.getColumnCount();
            for (int i = 1; i <= cCount; i++) {
                pass += rsmd.getColumnName(i);
                pass += " ,,,NC,,, ";
            }
            System.out.println(pass);
            YWCA_DatabaseZeus.QueryResults(pass);
            while (rs.next()) {
                pass = "";
                for (int i = 1; i < passNum + 2; i++) {
                    pass += rs.getString(i);
                    pass += " ,,,NC,,, ";
                }
                System.out.println(pass);
                YWCA_DatabaseZeus.QueryResults(pass);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
