/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

/**
 *
 * @author Kat
 */
public class Groups extends javax.swing.JFrame {
String select, from, where;
    /**
     * Creates new form Groups
     */
private Reports r = new Reports();

    public Groups() {
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

        I = new javax.swing.JLabel();
        IU = new javax.swing.JLabel();
        SG = new javax.swing.JLabel();
        SGU = new javax.swing.JLabel();
        IUtextfield = new javax.swing.JTextField();
        SGtextfield = new javax.swing.JTextField();
        SGUtextfield = new javax.swing.JTextField();
        Itextfield = new javax.swing.JTextField();
        Update = new javax.swing.JButton();
        Return = new javax.swing.JButton();
        groups_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("YWCA Database: Groups Update");
        setMaximumSize(new java.awt.Dimension(600, 470));
        setMinimumSize(new java.awt.Dimension(600, 470));
        setPreferredSize(new java.awt.Dimension(600, 470));
        getContentPane().setLayout(null);

        I.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        I.setForeground(new java.awt.Color(255, 102, 0));
        I.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        I.setText("Isabella's");
        getContentPane().add(I);
        I.setBounds(50, 100, 210, 30);

        IU.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        IU.setForeground(new java.awt.Color(255, 102, 0));
        IU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IU.setText("Isabella's Unduplicated");
        getContentPane().add(IU);
        IU.setBounds(51, 153, 210, 30);

        SG.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        SG.setForeground(new java.awt.Color(255, 102, 0));
        SG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SG.setText("Support Groups");
        getContentPane().add(SG);
        SG.setBounds(50, 206, 210, 30);

        SGU.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        SGU.setForeground(new java.awt.Color(255, 102, 0));
        SGU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SGU.setText("Support Groups Unduplicated");
        getContentPane().add(SGU);
        SGU.setBounds(44, 262, 220, 30);
        getContentPane().add(IUtextfield);
        IUtextfield.setBounds(295, 154, 270, 30);
        getContentPane().add(SGtextfield);
        SGtextfield.setBounds(295, 207, 270, 30);
        getContentPane().add(SGUtextfield);
        SGUtextfield.setBounds(295, 263, 270, 30);
        getContentPane().add(Itextfield);
        Itextfield.setBounds(295, 101, 270, 30);

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

        Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        getContentPane().add(Return);
        Return.setBounds(10, 390, 250, 50);

        groups_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkginterface/ywca_groups.png"))); // NOI18N
        getContentPane().add(groups_bg);
        groups_bg.setBounds(0, 0, 600, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnActionPerformed
       String[] args = null;
       Update_Menu.main(args);
       this.dispose();
    }//GEN-LAST:event_ReturnActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        //Run Groups mod
        String Update = "UPDATE Groups SET";
        String Where = "";
        if (!"".equals(this.Itextfield.getText())) {
            String Isabellas = " Isabellas = " + this.Itextfield.getText() + ",";
            Update += Isabellas;
        }

        if (!"".equals(this.IUtextfield.getText())) {
            String Isabellas_Unduplicated = " Isabellas_Unduplicated = " + this.IUtextfield.getText() + ",";
            Update += Isabellas_Unduplicated;
        }

        if (!"".equals(this.SGtextfield.getText())) {
            String Support_Groups = " Support_Groups = " + this.SGtextfield.getText() + ",";
            Update += Support_Groups;
        }

        if (!"".equals(this.SGUtextfield.getText())) {
            String Support_Groups_Unduplicated = " Support_Groups_Unduplicated = " + this.SGUtextfield.getText() + ",";
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

       
        r.set(this.select, this.from, this.where);
        r.ModBabyMod();

    }//GEN-LAST:event_UpdateActionPerformed

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
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Groups.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Groups().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel I;
    private javax.swing.JLabel IU;
    private javax.swing.JTextField IUtextfield;
    private javax.swing.JTextField Itextfield;
    private javax.swing.JButton Return;
    private javax.swing.JLabel SG;
    private javax.swing.JLabel SGU;
    private javax.swing.JTextField SGUtextfield;
    private javax.swing.JTextField SGtextfield;
    private javax.swing.JButton Update;
    private javax.swing.JLabel groups_bg;
    // End of variables declaration//GEN-END:variables
}
