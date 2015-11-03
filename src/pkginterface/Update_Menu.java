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
public class Update_Menu extends javax.swing.JFrame {

    /**
     * Creates new form Modify
     */
    public Update_Menu() {
        initComponents();

        Counseling.setOpaque(false);
        Counseling.setContentAreaFilled(false);
        Counseling.setBorderPainted(false);

        Groups.setOpaque(false);
        Groups.setContentAreaFilled(false);
        Groups.setBorderPainted(false);

        WAW.setOpaque(false);
        WAW.setContentAreaFilled(false);
        WAW.setBorderPainted(false);

        HHAA.setOpaque(false);
        HHAA.setContentAreaFilled(false);
        HHAA.setBorderPainted(false);

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

        WAW = new javax.swing.JButton();
        Groups = new javax.swing.JButton();
        HHAA = new javax.swing.JButton();
        Counseling = new javax.swing.JButton();
        Return = new javax.swing.JButton();
        update_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("YWCA Database Modify Menu");
        setMinimumSize(new java.awt.Dimension(600, 470));
        getContentPane().setLayout(null);

        WAW.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        WAW.setForeground(new java.awt.Color(255, 102, 0));
        WAW.setText("Wrap Around Wednesday");
        getContentPane().add(WAW);
        WAW.setBounds(135, 223, 330, 40);

        Groups.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Groups.setForeground(new java.awt.Color(255, 102, 0));
        Groups.setText("Groups");
        Groups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupsActionPerformed(evt);
            }
        });
        getContentPane().add(Groups);
        Groups.setBounds(135, 109, 330, 40);

        HHAA.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        HHAA.setForeground(new java.awt.Color(255, 102, 0));
        HHAA.setText("HHAA");
        HHAA.setToolTipText("");
        getContentPane().add(HHAA);
        HHAA.setBounds(135, 281, 330, 40);

        Counseling.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Counseling.setForeground(new java.awt.Color(255, 102, 0));
        Counseling.setText("Counseling");
        Counseling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CounselingActionPerformed(evt);
            }
        });
        getContentPane().add(Counseling);
        Counseling.setBounds(135, 165, 330, 40);

        Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        getContentPane().add(Return);
        Return.setBounds(10, 390, 240, 50);

        update_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkginterface/ywca_modmenu.png"))); // NOI18N
        getContentPane().add(update_bg);
        update_bg.setBounds(0, 0, 600, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupsActionPerformed
        Main_Menu.groupsMain();
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_GroupsActionPerformed

    private void ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnActionPerformed
        String[] args = null;
        Main_Menu.main(args);// TODO add your handling code here:
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_ReturnActionPerformed

    private void CounselingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CounselingActionPerformed
        Main_Menu.CounselingMain();
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_CounselingActionPerformed

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
            java.util.logging.Logger.getLogger(Update_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Update_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Update_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Update_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Update_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Counseling;
    private javax.swing.JButton Groups;
    private javax.swing.JButton HHAA;
    private javax.swing.JButton Return;
    private javax.swing.JButton WAW;
    private javax.swing.JLabel update_bg;
    // End of variables declaration//GEN-END:variables
}
