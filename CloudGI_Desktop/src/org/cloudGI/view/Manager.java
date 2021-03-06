/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.view;

import org.cloudGI.tasks.Exec;
import org.cloudGI.tasks.MyTimerTask;
import org.cloudGI.tasks.RenewalTask;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Poliana Nascimento
 */
public class Manager extends javax.swing.JFrame {

    private MyTimerTask myTask;
    private RenewalTask myTask2;
    private Timer myTimer;
    private Timer myTimer2;

    /**
     * Creates new form Gerenciador
     */
    public Manager() {
        initComponents();
        jButton2.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        close1 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        close2 = new javax.swing.JLabel();
        close3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jButton1.setText("Iniciar Monitoramento");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 100, 207, 25);

        jButton2.setText("Parar Monitoramento");
        jButton2.setPreferredSize(new java.awt.Dimension(189, 29));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(240, 100, 207, 25);

        jButton3.setText("Excluir Instancia");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(240, 180, 207, 25);

        jButton4.setText("Listar Instancias");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(20, 140, 207, 25);

        jButton5.setText("Criar Instancia");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(240, 140, 207, 25);

        jButton6.setText("Reiniciar Instancia");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(20, 180, 207, 25);

        jScrollPane1.setViewportView(jTextPane1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 230, 427, 120);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cloudGI/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(300, 10, 150, 60);

        close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cloudGI/view/images/transparencia2.png"))); // NOI18N
        getContentPane().add(close1);
        close1.setBounds(450, 5, 20, 20);

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cloudGI/view/images/Close Square.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        getContentPane().add(close);
        close.setBounds(450, 5, 16, 16);

        close2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cloudGI/view/images/transparencia2.png"))); // NOI18N
        getContentPane().add(close2);
        close2.setBounds(430, 5, 20, 20);

        close3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cloudGI/view/images/Minimize Square.png"))); // NOI18N
        close3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                close3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                close3MouseExited(evt);
            }
        });
        getContentPane().add(close3);
        close3.setBounds(430, 5, 16, 16);

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Gerenciador");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 40, 300, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/cloudGI/images/fundo.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.lightGray));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 472, 360);

        setBounds(0, 0, 471, 361);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        myTask.cancel();
        myTimer.cancel();
        
        // Instance Renewal
   
        myTask2.cancel();
        myTimer2.cancel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        Exec ex = new Exec();
        ex.command("source devstack/openrc admin admin; nova list > instancias.txt");
        ListInstance l;
        l = new ListInstance();
        l.setVisible(true);

        jTextPane1.setText("");

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        jButton1.setEnabled(false);
        jButton2.setEnabled(true);

        int delay = 50000;
        int period = 50000;

        myTask = new MyTimerTask();
        myTimer = new Timer();
        myTimer.schedule(myTask, delay, period);
        
        //Instance Renewel
        
        myTask2 = new RenewalTask();
        myTimer2 = new Timer();
        Random r = new Random();
        //30minutos
        int t1 = 1800000;
        //60minutos
        int t2 = 3600000;
        myTimer2.schedule(myTask2, ((long) (t1 + ((t2 - t1) * r.nextDouble()))), ((long) (t1 + ((t2 - t1) * r.nextDouble()))));


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DeleteInstance dl = new DeleteInstance();
        dl.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void close3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close3MouseClicked
        setExtendedState(this.ICONIFIED);        // TODO add your handling code here:
    }//GEN-LAST:event_close3MouseClicked

    private void close3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close3MouseEntered
        this.close2.setVisible(true);
    }//GEN-LAST:event_close3MouseEntered

    private void close3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close3MouseExited
        this.close2.setVisible(false);
    }//GEN-LAST:event_close3MouseExited

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        this.close1.setVisible(true);
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        this.close1.setVisible(false);
    }//GEN-LAST:event_closeMouseExited

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        jTextPane1.setText("Iniciando Monitoramento...");
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        jTextPane1.setText("Aguarde...");
    }//GEN-LAST:event_jButton4MousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        StartInstance dl = new StartInstance();
        dl.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        RebootInstance dl = new RebootInstance();
        dl.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private javax.swing.JLabel close1;
    private javax.swing.JLabel close2;
    private javax.swing.JLabel close3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
