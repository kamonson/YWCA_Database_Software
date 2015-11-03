/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import ywca_database.UseSQL.SQL_Access;
import ywca_database.YWCA_DatabaseZeus;

/**
 *
 * @author Kat
 */
public class Reports extends javax.swing.JFrame {

    /**
     * Creates new form Reports
     */
    public Reports() {
        initComponents();
        
        Run.setOpaque(false);
        Run.setContentAreaFilled(false);
        Run.setBorderPainted(false);
        
        Return.setOpaque(false);
        Return.setContentAreaFilled(false);
        Return.setBorderPainted(false);
    }


    private int passNum;
    private String Query;
    private String select = "select ";
    private String from = " from ";
    private String where = " where ";
    private static ArrayList<String> Result = new ArrayList<>();
    private static ArrayList<String> ResultHeadings = new ArrayList<>();

    //Function to sort results from SQL_Access into arraylist of strings
    public static void QueryResults(String result) {
        Result.add(result);
    }
//connector to Access

    public void RunAccessQuery(String Query, int passNum) {
        try {
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Connection accessDB;
            //jdbc:odbcDriver{Microsoft Access Driver (*.mdb, *.accdb)} must be installed check data sources to ensure it is
            //DBQ=<path to db>
            //UID = Admin or username
            //PWD= <blank> or password
            String DBQ = "";

            try {
                InputStream DBLoc = new FileInputStream("locationData.txt");

                java.util.Scanner s = new java.util.Scanner(DBLoc).useDelimiter("\\A");
                DBQ = s.hasNext() ? s.next() : "";

            } catch (IOException ex) {
                System.out.print("Check and make sure you use the DBLocation tab");
            }

            String UID = "Admin";
            String PWD = ";";
            String database = String.format("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=%s\\YWCA_Core.mdb;UID =%s; PWD =%s", DBQ, UID, PWD);
            accessDB = DriverManager.getConnection(database, "", "");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            this.passNum = passNum;
            SQL_Access.viewTable(accessDB, Query, this.passNum);
        } catch (SQLException ex) {
            Logger.getLogger(YWCA_DatabaseZeus.class.getName()).log(Level.SEVERE, null, ex);
            //clear old stuff
            Result.clear();
            ResultHeadings.clear();
            this.select = "select ";
            this.from = " from ";
            this.where = " where ";
            this.Query = "";
            this.passNum = 0;
        } 
    }
     //run SQLQuerry on button press 
    public void GoBabyGo(JTable table) {
        //establish select, from, where
      
        String in = this.select;
        this.passNum = 0;
        Pattern p = Pattern.compile(",");
        Matcher m = p.matcher(in);
        while (m.find()) {
            passNum++;
        }
        
        this.Query = this.select + this.from + this.where + ";";
        this.RunAccessQuery(Query, passNum);

        //Make Column titles
        DefaultTableModel tableModel = new DefaultTableModel();
        String cin = Result.get(0);
        List<String> items = Arrays.asList(cin.split(",,,NC,,,"));
        for (int r = 0; r < items.size(); r++) {
            ResultHeadings.add(items.get(r));
        }
        Result.remove(0);
        for (String columnName : ResultHeadings) {
            tableModel.addColumn(columnName);
        }

        //Add rows
        ArrayList<String[]> rowArray = new ArrayList();
        for (int i = 0; i < Result.size(); i++) {
            cin = Result.get(i);
            items = Arrays.asList(cin.split(",,,NC,,,"));
            String[] s = new String[items.size()];
            s = items.toArray(s);
            rowArray.add(s);
        }
        for (int i = 0; i < rowArray.size(); i++) {
            tableModel.addRow(rowArray.get(i));
        }
        table.setModel(tableModel);

        //clear old stuff
        Result.clear();
        ResultHeadings.clear();
        this.select = "select ";
        this.from = " from ";
        this.where = " where ";
        this.Query = "";
        this.passNum = 0;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Table = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Run = new javax.swing.JButton();
        Return = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        reports_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("YWCA Database Reports");
        setMaximumSize(new java.awt.Dimension(900, 715));
        setMinimumSize(new java.awt.Dimension(900, 715));
        getContentPane().setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Results Will Show Here"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout TableLayout = new javax.swing.GroupLayout(Table);
        Table.setLayout(TableLayout);
        TableLayout.setHorizontalGroup(
            TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        TableLayout.setVerticalGroup(
            TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        getContentPane().add(Table);
        Table.setBounds(31, 148, 840, 450);

        Run.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Run.setForeground(new java.awt.Color(255, 102, 0));
        Run.setText("Run");
        Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunActionPerformed(evt);
            }
        });
        getContentPane().add(Run);
        Run.setBounds(526, 76, 90, 40);

        Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        getContentPane().add(Return);
        Return.setBounds(10, 630, 230, 40);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Groups", "Counseling 2015", "Wrap Around Wednesday", "HHAA" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(292, 84, 220, 26);

        reports_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkginterface/ywca_reports.png"))); // NOI18N
        getContentPane().add(reports_bg);
        reports_bg.setBounds(0, 0, 900, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        this.Query = "";
        if (this.jComboBox1.getSelectedIndex() == 0) {
            this.select = "SELECT Month, Isabellas, Isabellas_Unduplicated, Support_Groups, Support_Groups_Unduplicated" + " ";
            this.from = "FROM Groups" + " ";
            this.where = "";
        } else if (this.jComboBox1.getSelectedIndex() == 1) {
            this.select = "SELECT Month, Teri_Intakes, Emma_Intakes, Melva_Intakes, Teri_OnGoing, Emma_OnGoing, Melva_OnGoing, Teri_Walkins, Emma_Walkins, Melva_Walkins" + " ";
            this.from = "FROM Counseling_2015" + " ";
            this.where = "";
        } else if (this.jComboBox1.getSelectedIndex() == 2) {
            this.select = "SELECT Week, FamilyLaw, CSOAdvocate, HousingAdvocate, Counseling, ChildAdvocate, CommunityPartners, TotalPeople" + " ";
            this.from = "FROM WrapAroundWednesday" + " ";
            this.where = "";
        } else if (this.jComboBox1.getSelectedIndex() == 3) {
            this.select = "SELECT ClientID, HoH_Relationship, Num_Case_Members, DOB, DOB_Quality, Race, Ethnicity, Gender, Veteran_Status, Disabling_Condition, Prior_Residence, Length_of_Stay, Entry_Housing_Status, Enroll_Date, Exit_Date, Bednights, Destination_at_Exit, Program" + " ";
            this.from = "FROM HHAA_BednightsEmployment" + " ";
            this.where = "";
        } 
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunActionPerformed
        // TODO add your handling code here:
                //Select reports
        this.GoBabyGo(this.jTable1);
        Result.clear();
        ResultHeadings.clear();
    }//GEN-LAST:event_RunActionPerformed

    private void ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnActionPerformed
        String[] args = null;
        Main_Menu.main(args);// TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_ReturnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reports().setVisible(true);
             }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Return;
    private javax.swing.JButton Run;
    private javax.swing.JPanel Table;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel reports_bg;
    // End of variables declaration//GEN-END:variables
}
