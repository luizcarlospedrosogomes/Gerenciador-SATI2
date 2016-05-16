package com.lcpg.app.frame;

import com.lcpg.app.Service.ListenerSocket;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class JFrameCadastroEvento extends javax.swing.JFrame {

    private ListenerSocket listener;
    private int idUsuario;
    /**
     * Creates new form JFrameCadastroEvento
     */
    public JFrameCadastroEvento() {
        initComponents();
    }
    public JFrameCadastroEvento(int idUsuario) {
        this.idUsuario = idUsuario;
        
        initComponents();
        System.out.println(this.idUsuario);
        this.listener = new ListenerSocket();
        this.listener.eventoList(this.idUsuario);
        preencherTable();
    }
    
    public void preencherTable(){
        DefaultTableModel model = (DefaultTableModel) jTableListarEventos.getModel();
        String[] columnNames = {"Numero", "Evento", "Data", "Hora inico", "Hora fim",  "Cadastrado por"};
        model.setColumnIdentifiers(columnNames);
        Object[] row = new Object[6];
        for (Map<String, String> map : this.listener.listEvento) {
            Object [] dados = map.values().toArray();
                row[0] = dados[5];
                row[1] = dados[3];
                row[2] = dados[1];
                row[3] = dados[2];
                row[4] = dados[0];
                row[5] = dados[4];
                model.addRow(row);
        }        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListarEventos = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Eventos");

        jTableListarEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListarEventosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListarEventos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButtonExcluir.setText("Excluir");

        jButtonEditar.setText("Editar");

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonEditar)
                        .addGap(46, 46, 46)
                        .addComponent(jButtonExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdicionar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonEditar))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAdicionar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        this.listener = new ListenerSocket();
        this.listener.tipoEnvento();
        
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jTableListarEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListarEventosMouseClicked
        String evento   = jTableListarEventos.getValueAt(jTableListarEventos.getSelectedRow(), 1).toString();
        String idEvento = jTableListarEventos.getValueAt(jTableListarEventos.getSelectedRow(), 0).toString();
        int res = JOptionPane.showConfirmDialog(null, "Gostaria de excluir "+ evento + " ?", "", JOptionPane.YES_NO_OPTION);
             switch (res) {
                 case JOptionPane.YES_OPTION:
                     int p = jTableListarEventos.getSelectedRow();
                     DefaultTableModel model = (DefaultTableModel) jTableListarEventos.getModel();
                     model.removeRow(p);
                     this.listener.excluirEvento(idEvento);
                     JOptionPane.showMessageDialog(null, "Evento excluido");
                     break;
                 case JOptionPane.NO_OPTION:
                     JOptionPane.showMessageDialog(null, "Cancelado");
                     break;
         }        
    }//GEN-LAST:event_jTableListarEventosMouseClicked

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
            java.util.logging.Logger.getLogger(JFrameCadastroEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCadastroEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCadastroEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCadastroEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameCadastroEvento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListarEventos;
    // End of variables declaration//GEN-END:variables
}
