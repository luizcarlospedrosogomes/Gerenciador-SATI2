package com.lcpg.app.frame;

import com.lcpg.app.Service.ListenerSocket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class JFrameEvento extends javax.swing.JFrame {

    private ListenerSocket listener;
    private int idUsuario;
    /**
     * Creates new form JFrameCadastroEvento
     */
    public JFrameEvento() {
        initComponents();
        }
    public JFrameEvento(int idUsuario) {
        this.idUsuario = idUsuario;
        initComponents();
        preencherTable();
    }
    
    public void preencherTable(){
        this.listener = new ListenerSocket();
        this.listener.eventoList(this.idUsuario);
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
    /*
    
     private void jTableListarEventosMouseClicked(java.awt.event.MouseEvent evt) {                                         
        jTableListarEventos.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            int r = jTableListarEventos.rowAtPoint(e.getPoint());
            if (r >= 0 && r < jTableListarEventos.getRowCount()) {
                jTableListarEventos.setRowSelectionInterval(r, r);
            } else {
                jTableListarEventos.clearSelection();
            }
            int rowindex = jTableListarEventos.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = createYourPopUp(rowindex,jTableListarEventos);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
            
    });
    }                                        
    */
    
    public static JPopupMenu createYourPopUp(int rowindex, JTable jTableListarEventos)
    {
       ListenerSocket listener = new ListenerSocket();
        JPopupMenu popup       = new JPopupMenu();
        //JMenuItem edit         = new JMenuItem("Editar Evento");       
        JMenuItem delete       = new JMenuItem("Exluir Evento");
        //popup.add(edit);
        popup.add(delete);
        
        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String evento   = jTableListarEventos.getValueAt(jTableListarEventos.getSelectedRow(), 1).toString();
        String idEvento = jTableListarEventos.getValueAt(jTableListarEventos.getSelectedRow(), 0).toString();
        int res = JOptionPane.showConfirmDialog(null, "Gostaria de excluir "+ evento + " ?", "", JOptionPane.YES_NO_OPTION);
        switch (res) {
                    case JOptionPane.YES_OPTION:
                    int p = jTableListarEventos.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) jTableListarEventos.getModel();
                    model.removeRow(p);
                    listener.excluirEvento(idEvento);
                    JOptionPane.showMessageDialog(null, "Evento excluido");
                    break;
                    case JOptionPane.NO_OPTION:
                    JOptionPane.showMessageDialog(null, "Cancelado");
                    break;
                }
            }
        });
        return popup;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListarEventos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("EVENTOS CADASTRADOS");

        jTableListarEventos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSair)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAtualizar)
                .addGap(29, 29, 29)
                .addComponent(jButtonAdicionar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionar)
                    .addComponent(jButtonAtualizar)
                    .addComponent(jButtonSair))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        //dispose();
    }//GEN-LAST:event_jButtonAdicionarActionPerformed
    
    
    
    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        DefaultTableModel dm = (DefaultTableModel) jTableListarEventos.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        preencherTable();
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jTableListarEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListarEventosMouseClicked
        jTableListarEventos.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            int r = jTableListarEventos.rowAtPoint(e.getPoint());
            if (r >= 0 && r < jTableListarEventos.getRowCount()) {
                jTableListarEventos.setRowSelectionInterval(r, r);
            } else {
                jTableListarEventos.clearSelection();
            }
            int rowindex = jTableListarEventos.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = createYourPopUp(rowindex,jTableListarEventos);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
            
    });
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
            java.util.logging.Logger.getLogger(JFrameEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameEvento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListarEventos;
    // End of variables declaration//GEN-END:variables
}
