/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Kat
 */
public class DBpath extends javax.swing.JFrame {

    /**
     * Creates new form DBpath
     */
    public DBpath() {
        initComponents();

        Change.setOpaque(false);
        Change.setContentAreaFilled(false);
        Change.setBorderPainted(false);

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

        DBpath = new javax.swing.JLabel();
        Change = new javax.swing.JButton();
        Return = new javax.swing.JButton();
        DBpathTF = new javax.swing.JTextField();
        dbpath_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 320));
        setMinimumSize(new java.awt.Dimension(500, 320));
        getContentPane().setLayout(null);

        DBpath.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        DBpath.setForeground(new java.awt.Color(255, 102, 0));
        DBpath.setText("Enter a Database Destination Path");
        getContentPane().add(DBpath);
        DBpath.setBounds(93, 93, 250, 30);

        Change.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        Change.setForeground(new java.awt.Color(255, 102, 0));
        Change.setText("Change");
        Change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeActionPerformed(evt);
            }
        });
        getContentPane().add(Change);
        Change.setBounds(180, 170, 140, 50);

        Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        getContentPane().add(Return);
        Return.setBounds(10, 250, 230, 40);

        DBpathTF.setText("eg:  Z:\\DatabaseLocation");
        DBpathTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBpathTFActionPerformed(evt);
            }
        });
        getContentPane().add(DBpathTF);
        DBpathTF.setBounds(78, 133, 340, 30);

        dbpath_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkginterface/ywca_dbpath.png"))); // NOI18N
        getContentPane().add(dbpath_bg);
        dbpath_bg.setBounds(0, 0, 500, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeActionPerformed
        // TODO add your handling code here:

        //Set the path of database file
        String locationData = "locationData.txt";
        String location = this.DBpathTF.getText();
        byte[] arrByte = location.getBytes();
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(locationData), 1024)) {
            out.write(arrByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Complete");
        String[] args = null;
        Update_Menu.main(args);
        this.dispose();
    }//GEN-LAST:event_ChangeActionPerformed

    private void ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnActionPerformed
        String[] args = null;
        Main_Menu.main(args);// TODO add your handling code here:
        this.dispose();        // TODO add your handling code here:  // TODO add your handling code here:
    }//GEN-LAST:event_ReturnActionPerformed

    private void DBpathTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBpathTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DBpathTFActionPerformed

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
            java.util.logging.Logger.getLogger(DBpath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DBpath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DBpath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DBpath.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DBpath().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Change;
    private javax.swing.JLabel DBpath;
    private javax.swing.JTextField DBpathTF;
    private javax.swing.JButton Return;
    private javax.swing.JLabel dbpath_bg;
    // End of variables declaration//GEN-END:variables
}
