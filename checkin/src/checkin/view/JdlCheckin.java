/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin.view;

import apoio.Mensagem;
import checkin.controller.Gateway;
import checkin.controller.Sync;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author mateus
 */
public class JdlCheckin extends javax.swing.JDialog {

    private static Gateway gateway;

    public JdlCheckin(java.awt.Frame parent, boolean modal, Gateway gateway) {
        super(parent, modal);
        this.gateway = gateway;
        initComponents();
        fillEvents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgEscolha = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnGerar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblEventsTitle = new javax.swing.JLabel();
        lblEvents = new javax.swing.JLabel();
        tfdEmail = new javax.swing.JTextField();
        tfdEvent = new javax.swing.JTextField();
        cbxOffline = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Checkin");

        btnFechar.setText("Close");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        jLabel2.setText("Event");

        btnGerar.setText("Checkin");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });

        jLabel3.setText("Email");

        lblEventsTitle.setText("Events");

        lblEvents.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEvents.setText("Events");
        lblEvents.setToolTipText("");
        lblEvents.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        cbxOffline.setText("Work offline");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGerar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFechar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfdEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxOffline))
                                    .addComponent(tfdEmail)))
                            .addComponent(lblEventsTitle))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxOffline))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEventsTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEvents, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnGerar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        if (cbxOffline.isSelected()) {
            Sync sync = new Sync();
            if (sync.findInscription(tfdEmail.getText(), Integer.parseInt(tfdEvent.getText()))) {
                boolean checkin = sync.checkin(tfdEmail.getText(), Integer.parseInt(tfdEvent.getText()));
                if (checkin) {
                    Mensagem.informacao("Checkin successfully.", this);
                } else {
                    Mensagem.informacao("Checkin unrealized.", this);
                }
            } else {
                if (sync.findUser(tfdEmail.getText())) {
                    sync.saveInscriptions(1, 1, 0, "", tfdEmail.getText(), Integer.parseInt(tfdEvent.getText()), 0);
                    boolean checkin = sync.checkin(tfdEmail.getText(), Integer.parseInt(tfdEvent.getText()));
                    if (checkin) {
                        Mensagem.informacao("Checkin successfully.", this);
                    } else {
                        Mensagem.informacao("Checkin unrealized.", this);
                    }
                } else {
                    int choise = Mensagem.confirmacao("User doesn't exists, want to create?", this);
                    if (choise == 0) {
                        sync.saveUsers(tfdEmail.getText(), tfdEmail.getText(), 0);
                        sync.saveInscriptions(1, 1, 0, "", tfdEmail.getText(), Integer.parseInt(tfdEvent.getText()), 0);
                        Mensagem.informacao("User created and checkin successfully.", this);
                    } else {
                        Mensagem.informacao("Checkin unrealized.", this);
                    }
                }
            }
        } else {
            String status = this.gateway.checkin(tfdEmail.getText(), tfdEvent.getText());
            if (status.equals("true")) {
                Mensagem.informacao("Checkin successfully.", this);
            } else {
                String statusregistration = this.gateway.registration(tfdEmail.getText(), tfdEvent.getText());
                if (statusregistration.equals("false")) {
                    int choise = Mensagem.confirmacao("User doesn't exists, want to create?", this);
                    if (choise == 0) {
                        String statususercreating = this.gateway.createUser(tfdEmail.getText());
                        if (statususercreating.equals("true")) {
                            String statusregistrationtwo = this.gateway.registration(tfdEmail.getText(), tfdEvent.getText());
                            if (statusregistrationtwo.equals("true")) {
                                Mensagem.informacao("Checkin successfully.", this);
                            } else {
                                Mensagem.informacao("Checkin unrealized.", this);
                            }
                        } else {
                            Mensagem.informacao("Checkin unrealized.", this);
                        }
                    } else {
                        Mensagem.informacao("Checkin unrealized.", this);
                    }
                }
            }
        }

    }//GEN-LAST:event_btnGerarActionPerformed

    private void fillEvents() {
        JSONArray events = this.gateway.getAllEvents();
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        for (int i = 0; i < events.size(); i++) {
            JSONObject event = (JSONObject) events.get(i);
            text.append(event.toString());
            text.append("<br>");
        }
        text.append("<html>");
        lblEvents.setText(text.toString());
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
            java.util.logging.Logger.getLogger(JdlCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdlCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdlCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdlCheckin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdlCheckin dialog = new JdlCheckin(new javax.swing.JFrame(), true, gateway);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgEscolha;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGerar;
    private javax.swing.JCheckBox cbxOffline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblEvents;
    private javax.swing.JLabel lblEventsTitle;
    private javax.swing.JTextField tfdEmail;
    private javax.swing.JTextField tfdEvent;
    // End of variables declaration//GEN-END:variables
}
