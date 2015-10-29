/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ywca_database;

import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.omg.CORBA.Object;
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
            String database = String.format("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=%s\\10 ADVP - Database - Counseling & Legal.mdb;UID =%s; PWD =%s", DBQ, UID, PWD);
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

    //use to change table
    public void ModBabyMod() {
        if (this.select == "select ") {
            if (this.jTextFieldSELECT.getText().length() < 1) {
                this.select = "";
            } else {
                this.select += this.jTextFieldSELECT.getText();
            }
        }
        String in = this.select;
        this.passNum = 0;
        Pattern p = Pattern.compile(",");
        Matcher m = p.matcher(in);
        while (m.find()) {
            passNum++;
        }
        if (this.from == " from ") {
            if (this.jTextFieldFROM.getText().length() < 1) {
                this.from = "";
            } else {
                this.from += this.jTextFieldFROM.getText();
            }
        }
        if (this.where == " where ") {
            if (this.jTextFieldWHERE.getText().length() < 1) {
                this.where = "";
            } else {
                this.where += this.jTextFieldWHERE.getText();
            }
        }
        this.Query = this.select + this.from + this.where + ";";
        this.RunAccessQuery(Query, passNum);
        this.jTextAreaResults.setText("");
        for (int i = 0; i < Result.size(); i++) {
            this.jTextAreaResults.append(Result.get(i) + "\n");
        }
        if (this.jTextFieldSELECT.getText().length() < 1) {
            this.select = "";
        } else {
            this.select += this.jTextFieldSELECT.getText();
        }

        //clear old stuff
        Result.clear();
        ResultHeadings.clear();
        this.select = "select ";
        this.from = " from ";
        this.where = " where ";
        this.Query = "";
        this.passNum = 0;
    }

    //run SQLQuerry on button press 
    public void GoBabyGo(JTable table) {
        //establish select, from, where
        if (this.select == "select ") {
            if (this.jTextFieldSELECT.getText().length() < 1) {
                this.select = "";
            } else {
                this.select += this.jTextFieldSELECT.getText();
            }
        }
        String in = this.select;
        this.passNum = 0;
        Pattern p = Pattern.compile(",");
        Matcher m = p.matcher(in);
        while (m.find()) {
            passNum++;
        }
        if (this.from == " from ") {
            if (this.jTextFieldFROM.getText().length() < 1) {
                this.from = "";
            } else {
                this.from += this.jTextFieldFROM.getText();
            }
        }
        if (this.where == " where ") {
            if (this.jTextFieldWHERE.getText().length() < 1) {
                this.where = "";
            } else {
                this.where += this.jTextFieldWHERE.getText();
            }
        }
        this.Query = this.select + this.from + this.where + ";";
        this.RunAccessQuery(Query, passNum);
        this.jTextAreaResults.setText("");
        for (int i = 0; i < Result.size(); i++) {
            this.jTextAreaResults.append(Result.get(i) + "\n");
        }
        if (this.jTextFieldSELECT.getText().length() < 1) {
            this.select = "";
        } else {
            this.select += this.jTextFieldSELECT.getText();
        }

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

        DefaultTableModel tableModel1 = new DefaultTableModel();
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        Reports = new javax.swing.JPanel();
        jComboBoxAccessHW = new javax.swing.JComboBox();
        jButtonRunMySQL = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Ericas = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Groups = new javax.swing.JPanel();
        Isabellas = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        Isabellas_Unduplicated = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        Support_Groups = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        Support_Groups_Unduplicated = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        Month_Groups = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        BtnGroups = new javax.swing.JButton();
        Counseling = new javax.swing.JPanel();
        Teri_Intakes = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Emma_Intakes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Melva_Intakes = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Teri_OnGoing = new javax.swing.JTextField();
        Emma_OnGoing = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Melva_OnGoing = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Teri_Walkins = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Emma_Walkins = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Melva_Walkins = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        Month = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        WAW_FamLaw = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        WAW_LegalAdvocate = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        WAW_CSOAdvocate = new javax.swing.JTextField();
        WAW_HousingAdvocate = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        WAW_Counseling = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        WAW_ChildAdvocate = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        WAW_CommunityPartners = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        BtnWAW = new javax.swing.JButton();
        WAW_Week = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        SQLAccess = new javax.swing.JPanel();
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
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        DBPath = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBoxAccessHW.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Groups", "Counseling_2015", "Wrap Arround Wed", "HHAA" }));
        jComboBoxAccessHW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAccessHWActionPerformed(evt);
            }
        });

        jButtonRunMySQL.setText("Run");
        jButtonRunMySQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunMySQLActionPerformed(evt);
            }
        });

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.setToolTipText("");
        jScrollPane5.setViewportView(jTable2);

        javax.swing.GroupLayout ReportsLayout = new javax.swing.GroupLayout(Reports);
        Reports.setLayout(ReportsLayout);
        ReportsLayout.setHorizontalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxAccessHW, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRunMySQL)
                .addContainerGap(669, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
        );
        ReportsLayout.setVerticalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAccessHW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRunMySQL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane6.addTab("Reports", Reports);

        Isabellas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IsabellasActionPerformed(evt);
            }
        });

        jLabel19.setText("Isabellas");

        Isabellas_Unduplicated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Isabellas_UnduplicatedActionPerformed(evt);
            }
        });

        jLabel20.setText("Isabellas_Unduplicated");

        jLabel21.setText("Support Groups");

        Support_Groups_Unduplicated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Support_Groups_UnduplicatedActionPerformed(evt);
            }
        });

        jLabel22.setText("Support Groups Unduplicated");

        jLabel23.setText("Month");

        BtnGroups.setText("Modify Groups");
        BtnGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGroupsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GroupsLayout = new javax.swing.GroupLayout(Groups);
        Groups.setLayout(GroupsLayout);
        GroupsLayout.setHorizontalGroup(
            GroupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GroupsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GroupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Isabellas)
                    .addGroup(GroupsLayout.createSequentialGroup()
                        .addGroup(GroupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(Support_Groups, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Support_Groups_Unduplicated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                                .addComponent(Isabellas_Unduplicated, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(Month_Groups, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(488, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GroupsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        GroupsLayout.setVerticalGroup(
            GroupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GroupsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Isabellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Isabellas_Unduplicated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Support_Groups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Support_Groups_Unduplicated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Month_Groups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 504, Short.MAX_VALUE)
                .addComponent(BtnGroups)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Groups_Update", Groups);

        Teri_Intakes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Teri_IntakesActionPerformed(evt);
            }
        });

        jLabel2.setText("Teri_Intakes");

        jLabel3.setText("Emma_Intakes");

        jLabel4.setText("Melva_Intakes");

        jLabel5.setText("Teri_OnGoing");

        jLabel13.setText("Emma_OnGoing");

        jLabel14.setText("Melva_OnGoing");

        jLabel15.setText(" Teri_Walkins");

        jLabel16.setText("Emma_Walkins");

        jLabel17.setText("Melva_Walkins");

        jLabel18.setText("Month");

        jButton2.setText("Modify Counseling Form");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CounselingLayout = new javax.swing.GroupLayout(Counseling);
        Counseling.setLayout(CounselingLayout);
        CounselingLayout.setHorizontalGroup(
            CounselingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CounselingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CounselingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CounselingLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(CounselingLayout.createSequentialGroup()
                        .addGroup(CounselingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Teri_OnGoing)
                            .addComponent(Teri_Intakes)
                            .addComponent(Emma_Intakes)
                            .addComponent(Melva_Intakes, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Emma_OnGoing)
                            .addComponent(Melva_OnGoing)
                            .addGroup(CounselingLayout.createSequentialGroup()
                                .addGroup(CounselingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addGap(0, 886, Short.MAX_VALUE))
                            .addComponent(Teri_Walkins)
                            .addComponent(Emma_Walkins)
                            .addComponent(Melva_Walkins)
                            .addComponent(Month))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CounselingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(26, 26, 26))
        );
        CounselingLayout.setVerticalGroup(
            CounselingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CounselingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Teri_Intakes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Emma_Intakes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Melva_Intakes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Teri_OnGoing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Emma_OnGoing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Melva_OnGoing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Teri_Walkins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Emma_Walkins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Melva_Walkins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Counseling_Update", Counseling);

        WAW_FamLaw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WAW_FamLawActionPerformed(evt);
            }
        });

        jLabel24.setText("Family Law");

        WAW_LegalAdvocate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WAW_LegalAdvocateActionPerformed(evt);
            }
        });

        jLabel25.setText("Legal Advocate");

        jLabel26.setText("CSO Advocate");

        WAW_CSOAdvocate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WAW_CSOAdvocateActionPerformed(evt);
            }
        });

        jLabel27.setText("Housing Advocate");

        jLabel28.setText("Counseling");

        jLabel29.setText("Child Advocate");

        jLabel30.setText("Community Partners");

        BtnWAW.setText("Modify Wrap Around Wednesday");
        BtnWAW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnWAWActionPerformed(evt);
            }
        });

        jLabel31.setText("Week (xx/xx/xxxx)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(525, Short.MAX_VALUE)
                .addComponent(BtnWAW)
                .addGap(227, 227, 227))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(WAW_CommunityPartners)
                    .addComponent(WAW_ChildAdvocate)
                    .addComponent(WAW_Counseling)
                    .addComponent(WAW_HousingAdvocate)
                    .addComponent(WAW_CSOAdvocate)
                    .addComponent(WAW_LegalAdvocate)
                    .addComponent(WAW_FamLaw)
                    .addComponent(WAW_Week, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel24)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(WAW_FamLaw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WAW_LegalAdvocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WAW_CSOAdvocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WAW_HousingAdvocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WAW_Counseling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WAW_ChildAdvocate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WAW_CommunityPartners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(WAW_Week, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(BtnWAW)
                .addGap(118, 118, 118))
        );

        jTabbedPane1.addTab("WrapAroundWednesday_Update", jPanel1);

        jTextField2.setText("jTextField2");

        jLabel32.setText("Client ID");

        jTextField3.setText("jTextField3");

        jLabel33.setText("Relationship to Head of Household");

        jTextField4.setText("jTextField4");

        jLabel34.setText("Number of Case Members");

        jTextField7.setText("jTextField7");

        jLabel35.setText("Date of Birth (xx/xx/xxxx)");

        jTextField8.setText("jTextField8");

        jLabel36.setText("DOB Quality");

        jTextField9.setText("jTextField9");

        jLabel37.setText("Race");

        jTextField10.setText("jTextField10");

        jLabel38.setText("Ethnicity");

        jTextField11.setText("jTextField11");

        jLabel39.setText("Gender");

        jTextField12.setText("jTextField12");

        jLabel40.setText("Veteran (YES / NO)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39)
                            .addComponent(jLabel38)
                            .addComponent(jLabel36)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32)
                            .addComponent(jLabel37))))
                .addContainerGap(766, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addContainerGap(323, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("HHAA_Update", jPanel4);

        javax.swing.GroupLayout EricasLayout = new javax.swing.GroupLayout(Ericas);
        Ericas.setLayout(EricasLayout);
        EricasLayout.setHorizontalGroup(
            EricasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EricasLayout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        EricasLayout.setVerticalGroup(
            EricasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane6.addTab("Erica's Forms", Ericas);

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

        jTable3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.setToolTipText("");
        jScrollPane6.setViewportView(jTable3);

        javax.swing.GroupLayout SQLAccessLayout = new javax.swing.GroupLayout(SQLAccess);
        SQLAccess.setLayout(SQLAccessLayout);
        SQLAccessLayout.setHorizontalGroup(
            SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SQLAccessLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSELECT))
            .addGroup(SQLAccessLayout.createSequentialGroup()
                .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SQLAccessLayout.createSequentialGroup()
                        .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFROM)
                            .addComponent(jTextFieldWHERE))
                        .addContainerGap())
                    .addGroup(SQLAccessLayout.createSequentialGroup()
                        .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SQLAccessLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addGroup(SQLAccessLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(SQLAccessLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAccesRun)
                        .addGap(175, 175, 175))))
            .addGroup(SQLAccessLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                .addContainerGap())
        );
        SQLAccessLayout.setVerticalGroup(
            SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SQLAccessLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldSELECT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFROM, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldWHERE, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(SQLAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAccesRun)
                .addContainerGap())
        );

        jTabbedPane6.addTab("SQL Access", SQLAccess);

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
                .addContainerGap(874, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 648, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        jTabbedPane6.addTab("Add User", jPanel3);

        jTextField1.setText("Path eg:  Z:\\DatabaseLocation");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setText("Database Destination Path");

        jButton1.setText("Change Path");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DBPathLayout = new javax.swing.GroupLayout(DBPath);
        DBPath.setLayout(DBPathLayout);
        DBPathLayout.setHorizontalGroup(
            DBPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBPathLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DBPathLayout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 662, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DBPathLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        DBPathLayout.setVerticalGroup(
            DBPathLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBPathLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 758, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane6.addTab("DB Path", DBPath);

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
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Set the path of database file
        String locationData = "locationData.txt";
        String location = this.jTextField1.getText();
        byte[] arrByte = location.getBytes();
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(locationData), 1024)) {
            out.write(arrByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jLabel1.setText("Changed!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButtonAccesRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccesRunActionPerformed
        //Select SQL
        this.GoBabyGo(jTable3);
        Result.clear();
        ResultHeadings.clear();
    }//GEN-LAST:event_jButtonAccesRunActionPerformed

    private void jTextFieldSELECTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSELECTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSELECTActionPerformed

    private void jTextFieldWHEREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWHEREActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWHEREActionPerformed

    private void jComboBoxAccessHWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAccessHWActionPerformed
        /*list of reports form to use:
         this.select = "SELECT " + " ";
         this.from = "FROM " + " ";
         this.where = "WHERE ";
            
         basic concat query = select+from+where, as a result Sub where for group by, Select for modify etc.
         ; added later do not add
         */
        this.Query = "";
        if (this.jComboBoxAccessHW.getSelectedIndex() == 0) {
            this.select = "SELECT Month, Isabellas, Isabellas_Unduplicated, Support_Groups, Support_Groups_Unduplicated" + " ";
            this.from = "FROM Groups" + " ";
            this.where = "";
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 1) {
            this.select = "SELECT Month, Teri_Intakes, Emma_Intakes, Melva_Intakes, Teri_OnGoing, Emma_OnGoing, Melva_OnGoing, Teri_Walkins, Emma_Walkins, Melva_Walkins" + " ";
            this.from = "FROM Counseling_2015" + " ";
            this.where = "";
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 2) {
            this.select = "SELECT Week, FamilyLaw, CSOAdvocate, HousingAdvocate, Counseling, ChildAdvocate, CommunityPartners, TotalPeople" + " ";
            this.from = "FROM WrapAroundWednesday" + " ";
            this.where = "";
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 3) {
            this.select = "SELECT ClientID, HoH_Relationship, Num_Case_Members, DOB, DOB_Quality, Race, Ethnicity, Gender, Veteran_Status, Disabling_Condition, Prior_Residence, Length_of_Stay, Entry_Housing_Status, Enroll_Date, Exit_Date, Bednights, Destination_at_Exit, Program" + " ";
            this.from = "FROM HHAA_BednightsEmployment" + " ";
            this.where = "";
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 4) {
            this.Query
                    = "SELECT  p.[Product Name] FROM Products p WHERE p.Category LIKE 'Beverages' AND p.Discontinued <> 1;";
            this.passNum = 0;
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 5) {
            this.Query
                    = "SELECT  DISTINCT s.Company AS 'Companies with fees over $100' FROM Orders o, Shippers s WHERE o.[Shipper ID] = s.ID AND o.[Shipping Fee] > 100;";
            this.passNum = 0;
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 6) {
            this.Query
                    = "SELECT (e.[First Name] + ' ' + e.[Last Name]) AS Employee, e.[Job Title] From Employees e;";
            this.passNum = 1;
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 7) {
            this.Query
                    = "SELECT o.[Shipped Date], e.[First Name] + ' ' + e.[Last Name] AS Employee FROM Orders o LEFT OUTER JOIN Employees e  ON o.[Employee ID] = e.[ID] ORDER BY o.[Shipped Date] DESC, e.[First Name] + ' ' + e.[Last Name];";
            this.passNum = 1;
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 8) {
            this.Query
                    = "SELECT MAX(p.[List Price]) AS \"Max Price\", MIN(p.[List Price]) AS \"Min Price\", AVG(p.[List Price])  AS \"AVG Price\" FROM Products p;";
            this.passNum = 2;
        } else if (this.jComboBoxAccessHW.getSelectedIndex() == 9) {
            this.Query
                    = "SELECT p.[Category], MAX(p.[List Price]) AS \"Max Price\", MIN(p.[List Price]) AS \"Min Price\", AVG(p.[List Price])  AS \"AVG Price\" FROM Products p GROUP BY p.[Category];";
            this.passNum = 3;
        }
    }//GEN-LAST:event_jComboBoxAccessHWActionPerformed

    private void jButtonRunMySQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunMySQLActionPerformed
        //Select reports
        this.GoBabyGo(this.jTable2);
        Result.clear();
        ResultHeadings.clear();

    }//GEN-LAST:event_jButtonRunMySQLActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        if (KeyEvent.VK_ENTER != evt.getKeyCode()) {
        } else {
            this.jButton1ActionPerformed(null);
        }

    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        this.jTextField1.setText("");
    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Modify counsuling form 2015
        String Update = "UPDATE Counseling_2015 SET";
        String Where = "";
        if (!"".equals(this.Teri_Intakes.getText())) {
            String teri_intakes = " Teri_Intakes = '" + this.Teri_Intakes.getText() + "',";
            Update += teri_intakes;
        }

        if (!"".equals(this.Emma_Intakes.getText())) {
            String emma_intakes = " Emma_Intakes = '" + this.Emma_Intakes.getText() + "',";
            Update += emma_intakes;
        }

        if (!"".equals(this.Melva_Intakes.getText())) {
            String melva_intakes = " Melva_Intakes = '" + this.Melva_Intakes.getText() + "',";
            Update += melva_intakes;
        }

        if (!"".equals(this.Teri_OnGoing.getText())) {
            String teri_ongoing = " Teri_OnGoing = '" + this.Teri_OnGoing.getText() + "',";
            Update += teri_ongoing;
        }

        if (!"".equals(this.Emma_OnGoing.getText())) {
            String emma_ongoing = " Emma_OnGoing = '" + this.Emma_OnGoing.getText() + "',";
            Update += emma_ongoing;
        }

        if (!"".equals(this.Melva_OnGoing.getText())) {
            String melva_ongoing = " Melva_OnGoing = '" + this.Melva_OnGoing.getText() + "',";
            Update += melva_ongoing;
        }
        if (!"".equals(this.Teri_Walkins.getText())) {
            String teri_walkins = " Teri_Walkins = '" + this.Teri_Walkins.getText() + "',";
            Update += teri_walkins;
        }

        if (!"".equals(this.Emma_Walkins.getText())) {
            String emma_walkins = " Emma_Walkins = '" + this.Emma_Walkins.getText() + "',";
            Update += emma_walkins;
        }

        if (!"".equals(this.Melva_Walkins.getText())) {
            String melva_walkins = " Melva_Walkins = '" + this.Melva_Walkins.getText() + "'";
            Update += melva_walkins;
        }

        if (Update.endsWith(",")) {
            String substring = Update.substring(0, Update.length() - 1);
            Update = substring;
        }

        if (!"".equals(this.Month.getText())) {
            String month = this.Month.getText();
            Where = " Where month = " + "'" + month + "'";
        }
        this.select = Update;
        this.from = Where;

        this.ModBabyMod();

        //switch to reports and view changes
        this.jComboBoxAccessHW.setSelectedIndex(1);
        this.jTabbedPane6.setSelectedIndex(0);
        this.jComboBoxAccessHWActionPerformed(evt);
        this.GoBabyGo(jTable2);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void Teri_IntakesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Teri_IntakesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Teri_IntakesActionPerformed

    private void IsabellasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IsabellasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IsabellasActionPerformed

    private void Isabellas_UnduplicatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Isabellas_UnduplicatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Isabellas_UnduplicatedActionPerformed

    private void Support_Groups_UnduplicatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Support_Groups_UnduplicatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Support_Groups_UnduplicatedActionPerformed

    private void BtnGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGroupsActionPerformed
        //Run Groups mod
        String Update = "UPDATE Groups SET";
        String Where = "";
        if (!"".equals(this.Isabellas.getText())) {
            String Isabellas = " Isabellas = " + this.Isabellas.getText() + ",";
            Update += Isabellas;
        }

        if (!"".equals(this.Isabellas_Unduplicated.getText())) {
            String Isabellas_Unduplicated = " Isabellas_Unduplicated = " + this.Isabellas_Unduplicated.getText() + ",";
            Update += Isabellas_Unduplicated;
        }

        if (!"".equals(this.Support_Groups.getText())) {
            String Support_Groups = " Support_Groups = " + this.Support_Groups.getText() + ",";
            Update += Support_Groups;
        }

        if (!"".equals(this.Support_Groups_Unduplicated.getText())) {
            String Support_Groups_Unduplicated = " Support_Groups_Unduplicated = " + this.Support_Groups_Unduplicated.getText() + ",";
            Update += Support_Groups_Unduplicated;
        }

        if (Update.endsWith(",")) {
            String substring = Update.substring(0, Update.length() - 1);
            Update = substring;
        }

        if (!"".equals(this.Month_Groups.getText())) {
            String Month_Groups = this.Month_Groups.getText();
            Where = " Where month = " + "'" + Month_Groups + "'";
        }
        this.select = Update;
        this.from = Where;

        this.ModBabyMod();

        //switch to reports and view changes
        this.jComboBoxAccessHW.setSelectedIndex(0);
        this.jTabbedPane6.setSelectedIndex(0);
        this.jComboBoxAccessHWActionPerformed(evt);
        this.GoBabyGo(jTable2);

        
        
        
        
        
    }//GEN-LAST:event_BtnGroupsActionPerformed

    private void WAW_LegalAdvocateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WAW_LegalAdvocateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WAW_LegalAdvocateActionPerformed

    private void WAW_FamLawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WAW_FamLawActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WAW_FamLawActionPerformed

    private void BtnWAWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnWAWActionPerformed
        // TODO add your handling code here:
        
        //Run WAW mod
        String Update = "UPDATE WrapAroundWednesday SET";
        String Where = "";
        if (!"".equals(this.WAW_FamLaw.getText())) {
            String WAW_FamLaw = " WAW_FamLaw = " + this.WAW_FamLaw.getText() + ",";
            Update += WAW_FamLaw;
            //Should ^^^^^ by WAW_FamLaw (input var) or FamilyLaw(db attribute)
            //Made all of them input var, change if should be db att
        }

        if (!"".equals(this.WAW_LegalAdvocate.getText())) {
            String WAW_LegalAdvocate = " WAW_LegalAdvocate = " + this.WAW_LegalAdvocate.getText() + ",";
            Update += WAW_LegalAdvocate;
        }

        if (!"".equals(this.WAW_CSOAdvocate.getText())) {
            String WAW_CSOAdvocate = " WAW_CSOAdvocate = " + this.WAW_CSOAdvocate.getText() + ",";
            Update += WAW_CSOAdvocate;
        }

        if (!"".equals(this.WAW_HousingAdvocate.getText())) {
            String WAW_HousingAdvocate = " WAW_HousingAdvocate = " + this.WAW_HousingAdvocate.getText() + ",";
            Update += WAW_HousingAdvocate;
        }
        
        if (!"".equals(this.WAW_Counseling.getText())) {
            String WAW_Counseling = " WAW_Counseling = " + this.WAW_Counseling.getText() + ",";
            Update += WAW_Counseling;
        }
        
        if (!"".equals(this.WAW_ChildAdvocate.getText())) {
            String WAW_ChildAdvocate = " WAW_ChildAdvocate = " + this.WAW_ChildAdvocate.getText() + ",";
            Update += WAW_ChildAdvocate;
        }
        
        if (!"".equals(this.WAW_CommunityPartners.getText())) {
            String WAW_CommunityPartners = " WAW_CommunityPartners = " + this.WAW_CommunityPartners.getText() + ",";
            Update += WAW_CommunityPartners;
        }

        if (Update.endsWith(",")) {
            String substring = Update.substring(0, Update.length() - 1);
            Update = substring;
        }

        if (!"".equals(this.WAW_Week.getText())) {
            String WAW_Week = this.WAW_Week.getText();
            Where = " Where week = " + "'" + WAW_Week + "'";
        }
        this.select = Update;
        this.from = Where;

        this.ModBabyMod();

        //switch to reports and view changes
        this.jComboBoxAccessHW.setSelectedIndex(2);
        this.jTabbedPane6.setSelectedIndex(0);
        this.jComboBoxAccessHWActionPerformed(evt);
        this.GoBabyGo(jTable2);

        
        
    }//GEN-LAST:event_BtnWAWActionPerformed

    private void WAW_CSOAdvocateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WAW_CSOAdvocateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WAW_CSOAdvocateActionPerformed

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
    private javax.swing.JButton BtnGroups;
    private javax.swing.JButton BtnWAW;
    private javax.swing.JPanel Counseling;
    private javax.swing.JPanel DBPath;
    private javax.swing.JTextField Emma_Intakes;
    private javax.swing.JTextField Emma_OnGoing;
    private javax.swing.JTextField Emma_Walkins;
    private javax.swing.JPanel Ericas;
    private javax.swing.JPanel Groups;
    private javax.swing.JTextField Isabellas;
    private javax.swing.JTextField Isabellas_Unduplicated;
    private javax.swing.JTextField Melva_Intakes;
    private javax.swing.JTextField Melva_OnGoing;
    private javax.swing.JTextField Melva_Walkins;
    private javax.swing.JTextField Month;
    private javax.swing.JTextField Month_Groups;
    private javax.swing.JPanel Reports;
    private javax.swing.JPanel SQLAccess;
    private javax.swing.JTextField Support_Groups;
    private javax.swing.JTextField Support_Groups_Unduplicated;
    private javax.swing.JTextField Teri_Intakes;
    private javax.swing.JTextField Teri_OnGoing;
    private javax.swing.JTextField Teri_Walkins;
    private javax.swing.JTextField WAW_CSOAdvocate;
    private javax.swing.JTextField WAW_ChildAdvocate;
    private javax.swing.JTextField WAW_CommunityPartners;
    private javax.swing.JTextField WAW_Counseling;
    private javax.swing.JTextField WAW_FamLaw;
    private javax.swing.JTextField WAW_HousingAdvocate;
    private javax.swing.JTextField WAW_LegalAdvocate;
    private javax.swing.JTextField WAW_Week;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAccesRun;
    private javax.swing.JButton jButtonRunMySQL;
    private javax.swing.JComboBox jComboBoxAccessHW;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextAreaResults;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldFROM;
    private javax.swing.JTextField jTextFieldSELECT;
    private javax.swing.JTextField jTextFieldWHERE;
    // End of variables declaration//GEN-END:variables
}
