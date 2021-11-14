/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin.view;

import apoio.Mensagem;
import checkin.controller.Gateway;
import checkin.controller.Sync;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author mateus
 */
public class JdlSync extends javax.swing.JDialog {

    private static Gateway gateway;

    public JdlSync(java.awt.Frame parent, boolean modal, Gateway gateway) {
        super(parent, modal);
        this.gateway = gateway;
        initComponents();
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
        btnClose = new javax.swing.JButton();
        btnSync = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sync online with offline");

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnSync.setText("Sync");
        btnSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSyncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSync)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSync)
                    .addComponent(btnClose))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSyncActionPerformed
        syncUsers();
        syncInscriptions();
        downloadEvents();
        downloadUsers();
        downloadInscriptions();
        Mensagem.informacao("Sync finished!", this);
    }//GEN-LAST:event_btnSyncActionPerformed

    private void syncUsers() {
        Sync sync = new Sync();
        JSONArray users = sync.findUsers();
        System.out.println(users.toString());
        for (int i = 0; i < users.size(); i++) {
            JSONObject user = (JSONObject) users.get(i);
            String name = user.get("name").toString();
            String email = user.get("email").toString();
            String statusregister = this.gateway.createUser(email);
            if (statusregister.equals("false")) {
                System.out.println("User exists");
            }
        }
    }

    private void syncInscriptions() {
        Sync sync = new Sync();
        JSONArray inscriptions = sync.findInscriptions();
        System.out.println(inscriptions.toString());
        for (int i = 0; i < inscriptions.size(); i++) {
            JSONObject inscription = (JSONObject) inscriptions.get(i);
            String user_email = inscription.get("user_email").toString();
            String event_id = inscription.get("event_id").toString();
            String statusregister = this.gateway.registration(user_email, event_id);
            if (statusregister.equals("false")) {
                this.gateway.checkin(user_email, event_id);
            }
        }
    }

    private void downloadEvents() {
        JSONArray events = this.gateway.getAllEvents();
        Sync sync = new Sync();
        sync.dropEvents();
        for (int i = 0; i < events.size(); i++) {
            JSONObject event = (JSONObject) events.get(i);
            String description = event.get("description").toString();
            String date = event.get("event_date").toString();
            int id = Integer.parseInt(event.get("id").toString());
            sync.saveEvents(id, description, date, 1);
        }
    }

    private void downloadUsers() {
        JSONArray users = this.gateway.getAllUsers();
        Sync sync = new Sync();
        sync.dropUsers();
        for (int i = 0; i < users.size(); i++) {
            JSONObject user = (JSONObject) users.get(i);
            String name = user.get("name").toString();
            String email = user.get("email").toString();
            sync.saveUsers(name, email, 1);
        }
    }

    private void downloadInscriptions() {
        JSONArray inscriptions = this.gateway.getAllInscriptions();
        Sync sync = new Sync();
        sync.dropInscriptions();
        for (int i = 0; i < inscriptions.size(); i++) {
            JSONObject inscription = (JSONObject) inscriptions.get(i);
            String user_email = inscription.get("user_email").toString();
            int checkin = Integer.parseInt(inscription.get("checkin").toString());
            int activated = Integer.parseInt(inscription.get("activated").toString());
            int email_sent = Integer.parseInt(inscription.get("email_sent").toString());
            String certificate = inscription.get("certificate").toString();
            int event_id = Integer.parseInt(inscription.get("event_id").toString());
            sync.saveInscriptions(checkin, activated, email_sent, certificate, user_email, event_id, 1);
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
            java.util.logging.Logger.getLogger(JdlSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdlSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdlSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdlSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                JdlSync dialog = new JdlSync(new javax.swing.JFrame(), true, gateway);
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
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSync;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
