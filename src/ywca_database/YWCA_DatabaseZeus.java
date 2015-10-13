/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ywca_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ywca_database.UseSQL.SQL_Access;

/**
 *
 * @author Kyle
 */
public class YWCA_DatabaseZeus extends javax.swing.JFrame {

    /**
     * Creates new form YMCA_Database
     */
    public YWCA_DatabaseZeus() {
        initComponents();
    }
    private int passNum;
    private String Query;
    private String select = "select ";
    private String from = " from ";
    private String where = " where ";
    private static ArrayList <String> Result = new ArrayList<>();

    public static void QueryResults(String result) {

        Result.add(result);
    }

    public void RunSQLSrvrQuery(String Query, int passNum) {
        try {
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Connection SQLSrvr;
            SQLSrvr = DriverManager.getConnection(
                    "jdbc:odbc:Driver={SQL Server};"
                    + "Server=CS1;Database=Northwind;Trusted_Connection=yes;");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
                       
            this.passNum = passNum;  
            SQL_Access.viewTable(SQLSrvr, Query, this.passNum);
        } catch (SQLException ex) {
            Logger.getLogger(YWCA_DatabaseZeus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RunAccessQuery(String Query, int passNum) {
        try {
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Connection accessDB;
            //jdbc:odbcDriver{Microsoft Access Driver (*.mdb, *.accdb)} must be installed check data sources to ensure it is
            //DBQ=<path to db>
            //UID = Admin or username
            //PWD= <blank> or password
            String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\\Users\\Kyle\\Documents\\GitHub\\YWCA_Database_Software\\src\\ywca_database\\UseSQL\\Northwind.accdb;UID = Admin; PWD =;";
            accessDB = DriverManager.getConnection(database, "", "");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            this.passNum = passNum;  
            SQL_Access.viewTable(accessDB, Query, this.passNum);
        } catch (SQLException ex) {
            Logger.getLogger(YWCA_DatabaseZeus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButtonAccesRun = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldFROM = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldSELECT = new javax.swing.JTextField();
        jTextFieldWHERE = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaResults = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBoxAccessHW = new javax.swing.JComboBox();
        jToggleButtonRunAccessAssignment = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaResultHW5Access = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jButtonRunMySQL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Person To Look Up");

        jButton1.setText("Run Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 518, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 569, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane6.addTab("Report 1", jPanel1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Information");

        jTextField2.setText("jTextField2");

        jLabel3.setText("First Name");

        jTextField3.setText("jTextField3");

        jLabel4.setText("Middle Initial");

        jTextField4.setText("jTextField4");

        jLabel5.setText("Last Name");

        jButton2.setText("Add Person");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jTabbedPane6.addTab("Client Data", jPanel2);

        jTextField5.setText("jTextField5");

        jLabel6.setText("New User Name");

        jTextField6.setText("jTextField6");

        jLabel7.setText("New User Password");

        jRadioButton1.setText("Report 1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Add Client Data 1");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Add User");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField6)
                        .addComponent(jTextField5))
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addContainerGap(730, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 459, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        jTabbedPane6.addTab("Add User", jPanel3);

        jLabel9.setFont(new java.awt.Font("AR BONNIE", 1, 36)); // NOI18N
        jLabel9.setText("Query ");

        jButtonAccesRun.setText("Run");
        jButtonAccesRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccesRunActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("AR BONNIE", 1, 18)); // NOI18N
        jLabel8.setText("FROM");

        jLabel10.setFont(new java.awt.Font("AR BONNIE", 1, 18)); // NOI18N
        jLabel10.setText("SELECT");

        jTextFieldSELECT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSELECTActionPerformed(evt);
            }
        });

        jTextFieldWHERE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWHEREActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("AR BONNIE", 1, 18)); // NOI18N
        jLabel11.setText("WHERE");

        jLabel12.setFont(new java.awt.Font("AR BONNIE", 1, 36)); // NOI18N
        jLabel12.setText("Results");

        jTextAreaResults.setColumns(20);
        jTextAreaResults.setRows(5);
        jScrollPane2.setViewportView(jTextAreaResults);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSELECT))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonAccesRun))
                            .addComponent(jTextFieldFROM)
                            .addComponent(jTextFieldWHERE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jButton4))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldSELECT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFROM, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldWHERE, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAccesRun)
                .addContainerGap())
        );

        jTabbedPane6.addTab("SQL Access", jPanel4);

        jComboBoxAccessHW.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "HW 5.1", "HW 5.2", "HW 5.3", "HW 5.4", "HW 5.5", "HW 5.6", "HW 5.7", "HW 5.8", "HW 5.9", "HW 5.10" }));
        jComboBoxAccessHW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAccessHWActionPerformed(evt);
            }
        });

        jToggleButtonRunAccessAssignment.setText("Run Access");
        jToggleButtonRunAccessAssignment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRunAccessAssignmentActionPerformed(evt);
            }
        });

        jTextAreaResultHW5Access.setColumns(20);
        jTextAreaResultHW5Access.setRows(5);
        jScrollPane3.setViewportView(jTextAreaResultHW5Access);

        jLabel13.setText("Result:");

        jButtonRunMySQL.setText("Run MySQL");
        jButtonRunMySQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunMySQLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jComboBoxAccessHW, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButtonRunAccessAssignment)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRunMySQL)))
                        .addGap(0, 372, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAccessHW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonRunAccessAssignment)
                    .addComponent(jButtonRunMySQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane6.addTab("HW5 1-5", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonAccesRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccesRunActionPerformed
        this.select = "select ";
        this.from = " from ";
        this.where = " where ";
        this.Query = "";

        if (this.jTextFieldSELECT.getText().length() < 1) {
            this.select = "";
        } else {
            this.select += this.jTextFieldSELECT.getText();
        }
        String in = this.select;
        this.passNum = 0;
        Pattern p = Pattern.compile(",");
        Matcher m = p.matcher(in);
        while (m.find())passNum ++;
        if (this.jTextFieldFROM.getText().length() < 1) {
            this.from = "";
        } else {
            this.from += this.jTextFieldFROM.getText();
        }
        if (this.jTextFieldWHERE.getText().length() < 1) {
            this.where = "";
        } else {
            this.where += this.jTextFieldWHERE.getText();
        }
        this.Query = this.select + this.from + this.where + ";";
        this.RunAccessQuery(Query, passNum);
        if (this.jTextAreaResults.getText() != "") {
            this.jTextAreaResults.setText("");
        }
        for (int i = 0; i < Result.size(); i++) {
            this.jTextAreaResults.append(Result.get(i) + "\n");
        }
    }//GEN-LAST:event_jButtonAccesRunActionPerformed

    private void jTextFieldSELECTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSELECTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSELECTActionPerformed

    private void jTextFieldWHEREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWHEREActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWHEREActionPerformed

    private void jToggleButtonRunAccessAssignmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRunAccessAssignmentActionPerformed
        // TODO add your handling code here:
        this.RunAccessQuery(Query, this.passNum);
        if (this.jTextAreaResultHW5Access.getText() != "") {
            this.jTextAreaResultHW5Access.setText("");
        }
        for (int i = 0; i < Result.size(); i++) {
            this.jTextAreaResultHW5Access.append(Result.get(i) + "\n");
        }
    }//GEN-LAST:event_jToggleButtonRunAccessAssignmentActionPerformed

    private void jComboBoxAccessHWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAccessHWActionPerformed
        // TODO add your handling code here:
        this.Query = "";
        if (this.jComboBoxAccessHW.getSelectedIndex() == 0) {
            this.Query
                    = "SELECT p.[Product Name], (p.[List Price] * 1.1)  AS \"10%_More\" FROM Products p Group By p.[Product Name],  (p.[List Price] * 1.1);";
                    this.passNum = 1;
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 1) {
            this.Query
                    = "SELECT p.[Product Name], Min(p.[List Price]) AS \"Price Min\", Max(p.[List Price]) AS \"Price Max\", AVG(p.[List Price]) AS \"Price AVG\" FROM Products p GROUP BY p.[Product Name];";
       this.passNum = 3;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 2) {
            this.Query
                    = "SELECT p.[Product Name] AS \"Discontinued Products\" FROM Products p WHERE p.Discontinued = 1;";
        this.passNum = 0;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 3) {
            this.Query
                    = "SELECT  p.[Product Name] FROM Products p WHERE p.[Product Name] LIKE 'Northwind Traders Dried *';";
       this.passNum = 1;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 4) {
            this.Query
                    = "SELECT  p.[Product Name] FROM Products p WHERE p.Category LIKE 'Beverages' AND p.Discontinued <> 1;";
        this.passNum = 0;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 5) {
            this.Query
                    = "SELECT  DISTINCT s.Company AS 'Companies with fees over $100' FROM Orders o, Shippers s WHERE o.[Shipper ID] = s.ID AND o.[Shipping Fee] > 100;";
        this.passNum = 0;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 6) {
            this.Query
                    = "SELECT (e.[First Name] + ' ' + e.[Last Name]) AS Employee, e.[Job Title] From Employees e;";
        this.passNum =1;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 7) {
            this.Query
                    = "SELECT o.[Shipped Date], e.[First Name] + ' ' + e.[Last Name] AS Employee FROM Orders o LEFT OUTER JOIN Employees e  ON o.[Employee ID] = e.[ID] ORDER BY o.[Shipped Date] DESC, e.[First Name] + ' ' + e.[Last Name];";
        this.passNum = 1;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 8) {
            this.Query
                    = "SELECT MAX(p.[List Price]) AS \"Max Price\", MIN(p.[List Price]) AS \"Min Price\", AVG(p.[List Price])  AS \"AVG Price\" FROM Products p;";
        this.passNum = 2;
        }else if (this.jComboBoxAccessHW.getSelectedIndex() == 9) {
            this.Query
                    = "SELECT p.[Category], MAX(p.[List Price]) AS \"Max Price\", MIN(p.[List Price]) AS \"Min Price\", AVG(p.[List Price])  AS \"AVG Price\" FROM Products p GROUP BY p.[Category];";
        this.passNum = 3;
        }
    }//GEN-LAST:event_jComboBoxAccessHWActionPerformed

    private void jButtonRunMySQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunMySQLActionPerformed
        // TODO add your handling code here:
        this.RunSQLSrvrQuery(Query, this.passNum);
        if (this.jTextAreaResultHW5Access.getText() != "") {
            this.jTextAreaResultHW5Access.setText("");
        }
        for (int i = 0; i < Result.size(); i++) {
            this.jTextAreaResultHW5Access.append(Result.get(i) + "\n");
        }
    }//GEN-LAST:event_jButtonRunMySQLActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(YWCA_DatabaseZeus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(YWCA_DatabaseZeus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(YWCA_DatabaseZeus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(YWCA_DatabaseZeus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new YWCA_DatabaseZeus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAccesRun;
    private javax.swing.JButton jButtonRunMySQL;
    private javax.swing.JComboBox jComboBoxAccessHW;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextAreaResultHW5Access;
    private javax.swing.JTextArea jTextAreaResults;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextFieldFROM;
    private javax.swing.JTextField jTextFieldSELECT;
    private javax.swing.JTextField jTextFieldWHERE;
    private javax.swing.JToggleButton jToggleButtonRunAccessAssignment;
    // End of variables declaration//GEN-END:variables
}
