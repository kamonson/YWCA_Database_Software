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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import ywca_database.UseSQL.SQL_Access;
import ywca_database.YWCA_DatabaseZeus;

/**
 *
 * @author Kat
 */
public class Counseling extends javax.swing.JFrame {

    String select, from, where;
    String Query = "";
    int passNum;

    /**
     * Creates new form Counseling
     */
    public Counseling() {
        initComponents();

        Update.setOpaque(false);
        Update.setContentAreaFilled(false);
        Update.setBorderPainted(false);

        Return.setOpaque(false);
        Return.setContentAreaFilled(false);
        Return.setBorderPainted(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IntakeDrop = new javax.swing.JComboBox();
        IntakeDrop1 = new javax.swing.JComboBox();
        IntakeDrop2 = new javax.swing.JComboBox();
        Update = new javax.swing.JButton();
        IntakeTF = new javax.swing.JTextField();
        MonthTF = new javax.swing.JTextField();
        OngoingTF = new javax.swing.JTextField();
        WalkinTF = new javax.swing.JTextField();
        Month = new javax.swing.JLabel();
        Return = new javax.swing.JButton();
        counseling_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 470));
        getContentPane().setLayout(null);

        IntakeDrop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teri's Ongoing", "Emma's Ongoing", "Melva's Ongoing" }));
        IntakeDrop.setMaximumSize(new java.awt.Dimension(154, 27));
        getContentPane().add(IntakeDrop);
        IntakeDrop.setBounds(50, 170, 210, 26);

        IntakeDrop1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teri's Walk-Ins", "Emma's Walk-Ins", "Melva's Walk-Ins" }));
        IntakeDrop1.setMaximumSize(new java.awt.Dimension(154, 27));
        IntakeDrop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntakeDrop1ActionPerformed(evt);
            }
        });
        getContentPane().add(IntakeDrop1);
        IntakeDrop1.setBounds(50, 226, 210, 26);

        IntakeDrop2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teri's Intakes", "Emma's Intakes", "Melva's Intakes" }));
        IntakeDrop2.setMaximumSize(new java.awt.Dimension(154, 27));
        IntakeDrop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntakeDrop2ActionPerformed(evt);
            }
        });
        getContentPane().add(IntakeDrop2);
        IntakeDrop2.setBounds(50, 115, 210, 26);

        Update.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        Update.setForeground(new java.awt.Color(255, 102, 0));
        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        getContentPane().add(Update);
        Update.setBounds(230, 330, 140, 40);

        IntakeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntakeTFActionPerformed(evt);
            }
        });
        getContentPane().add(IntakeTF);
        IntakeTF.setBounds(295, 113, 270, 30);
        getContentPane().add(MonthTF);
        MonthTF.setBounds(293, 279, 270, 30);
        MonthTF.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(OngoingTF);
        OngoingTF.setBounds(293, 168, 270, 30);
        getContentPane().add(WalkinTF);
        WalkinTF.setBounds(293, 224, 270, 30);

        Month.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        Month.setForeground(new java.awt.Color(255, 102, 0));
        Month.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Month.setText("Month");
        getContentPane().add(Month);
        Month.setBounds(40, 280, 220, 30);

        Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        getContentPane().add(Return);
        Return.setBounds(10, 390, 250, 50);

        counseling_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkginterface/ywca_counseling.png"))); // NOI18N
        getContentPane().add(counseling_bg);
        counseling_bg.setBounds(0, 0, 600, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        //Modify counsuling form 2015
        String Update = "UPDATE Counseling_2015 SET";
        String Where = "";

        if (this.IntakeDrop2.getSelectedIndex() == 0) {
            String melva_intakes = " Teri_Intakes = '" + this.IntakeTF.getText() + "',";
            Update += melva_intakes;
        } else if (this.IntakeDrop2.getSelectedIndex() == 1) {
            String melva_intakes = " Emma_Intakes = '" + this.IntakeTF.getText() + "',";
            Update += melva_intakes;
        } else if (this.IntakeDrop2.getSelectedIndex() == 2) {
            String melva_intakes = " Melva_Intakes = '" + this.IntakeTF.getText() + "',";
            Update += melva_intakes;
        } else {
            Update += "";
        }

        if (this.IntakeDrop.getSelectedIndex() == 0) {
            String melva_intakes = " Teri_OnGoing = '" + this.OngoingTF.getText() + "',";
            Update += melva_intakes;
        } else if (this.IntakeDrop.getSelectedIndex() == 1) {
            String melva_intakes = " Emma_OnGoing = '" + this.OngoingTF.getText() + "',";
            Update += melva_intakes;
        } else if (this.IntakeDrop.getSelectedIndex() == 2) {
            String melva_intakes = " Melva_OnGoing = '" + this.OngoingTF.getText() + "',";
            Update += melva_intakes;
        } else {
            Update += "";
        }

        if (this.IntakeDrop1.getSelectedIndex() == 0) {
            String melva_intakes = " Teri_Walkins = '" + this.WalkinTF.getText() + "',";
            Update += melva_intakes;
        } else if (this.IntakeDrop1.getSelectedIndex() == 1) {
            String melva_intakes = " Emma_Walkins = '" + this.WalkinTF.getText() + "',";
            Update += melva_intakes;
        } else if (this.IntakeDrop1.getSelectedIndex() == 2) {
            String melva_intakes = " Melva_Walkins = '" + this.WalkinTF.getText() + "',";
            Update += melva_intakes;
        } else {
            Update += "";
        }

        if (Update.endsWith(",")) {
            String substring = Update.substring(0, Update.length() - 1);
            Update = substring;
        }

        if (!"".equals(this.MonthTF.getText())) {
            String month = this.MonthTF.getText();
            Where = " Where month = " + "'" + month + "'";
        }
        this.select = Update;
        this.from = Where;
        this.where = "";

        this.ModBabyMod();
        JOptionPane.showMessageDialog(null, "Complete");
        String[] args = null;
        Update_Menu.main(args);
        this.dispose();
    }//GEN-LAST:event_UpdateActionPerformed

    private void IntakeDrop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntakeDrop2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IntakeDrop2ActionPerformed

    private void IntakeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntakeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IntakeTFActionPerformed

    private void IntakeDrop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntakeDrop1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IntakeDrop1ActionPerformed

    private void ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnActionPerformed
        String[] args = null;
        Update_Menu.main(args);
        this.dispose();
    }//GEN-LAST:event_ReturnActionPerformed

    public void ModBabyMod() {

        String in = this.select;
        this.passNum = 0;
        Pattern p = Pattern.compile(",");
        Matcher m = p.matcher(in);
        while (m.find()) {
            passNum++;
        }

        this.Query = this.select + this.from + this.where + ";";
        this.RunAccessQuery(Query, passNum);

        //switch to reports and view changes
//        this.jComboBox1.setSelectedIndex(0);
//        this.RunActionPerformed(null);
//        this.GoBabyGo(jTable1);
        //clear old stuff
        this.select = "select ";
        this.from = " from ";
        this.where = " where ";
        this.Query = "";
        this.passNum = 0;
    }

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
            this.select = "select ";
            this.from = " from ";
            this.where = " where ";
            this.Query = "";
            this.passNum = 0;
        }
    }

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
            java.util.logging.Logger.getLogger(Counseling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Counseling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Counseling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Counseling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Counseling().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox IntakeDrop;
    private javax.swing.JComboBox IntakeDrop1;
    private javax.swing.JComboBox IntakeDrop2;
    private javax.swing.JTextField IntakeTF;
    private javax.swing.JLabel Month;
    private javax.swing.JTextField MonthTF;
    private javax.swing.JTextField OngoingTF;
    private javax.swing.JButton Return;
    private javax.swing.JButton Update;
    private javax.swing.JTextField WalkinTF;
    private javax.swing.JLabel counseling_bg;
    // End of variables declaration//GEN-END:variables
}
