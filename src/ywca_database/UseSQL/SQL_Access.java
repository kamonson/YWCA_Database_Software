/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ywca_database.UseSQL;

import java.sql.*;

/**
 *
 * @author Kyle
 */
public class SQL_Access {

    private static String Query;

    public static void viewTable(Connection con, String Query)
            throws SQLException {
        try {
            SQL_Access.Query = Query;
            Statement stmt;
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(SQL_Access.Query);
            ResultSet rs;
            rs = stmt.getResultSet();
            while (rs.next() == true) {
                System.out.println("<" + rs.getString(1) + ", " + rs.getString(2) + ">");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
