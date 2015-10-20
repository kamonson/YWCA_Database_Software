/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ywca_database.UseSQL;

import java.sql.*;
import ywca_database.YWCA_DatabaseZeus;

/**
 *
 * @author Kyle
 */
public class SQL_Access {

    private static String Query;
    private static String Result;

    public static void viewTable(Connection con, String Query, int passNum)
            throws SQLException {
        try {
//            SQL_Access.Query = Query;
//            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            stmt.execute(SQL_Access.Query);
//            ResultSet rs;
//            rs = stmt.getResultSet();
//            while (rs.next() == true) {
//                //needs to be dynamic to results
//                Result = "<" + rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + ", " + rs.getString(5) + ", " + rs.getString(6) + ", " + rs.getString(7) + ">";
//                System.out.println(Result);
//                //results.add(a);
//                YWCA_DatabaseZeus.QueryResults(Result);
//            }
//            rs.close();
//            stmt.close();
//            con.close();

            SQL_Access.Query = Query;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_Access.Query);
            String pass = "";
            ResultSetMetaData rsmd = rs.getMetaData();
            int cCount = rsmd.getColumnCount();
            for (int i = 1; i <= cCount; i++){pass += rsmd.getColumnName(i); pass += "\t\t";}
            System.out.println(pass);
                    YWCA_DatabaseZeus.QueryResults(pass);
            while (rs.next()) {
                //needs to be dynamic to results
                pass = "";
                for (int i = 1; i < passNum+2; i++) {
                    pass += rs.getString(i);
                    pass += "\t\t";
//                pass = rs.getString(2);
//                System.out.println(pass);
//                YWCA_DatabaseZeus.QueryResults(pass);
//                pass = rs.getString(3);
//                System.out.println(pass);
//                YWCA_DatabaseZeus.QueryResults(pass);
//                pass = rs.getString(4);
//                System.out.println(pass);
//                YWCA_DatabaseZeus.QueryResults(pass);
//                pass = rs.getString(5);
//                System.out.println(pass);
//                YWCA_DatabaseZeus.QueryResults(pass);
//                pass = rs.getString(6);
//                System.out.println(pass);
//                YWCA_DatabaseZeus.QueryResults(pass);
//                pass = rs.getString(7);
//                System.out.println(pass);
//                YWCA_DatabaseZeus.QueryResults(pass);
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
