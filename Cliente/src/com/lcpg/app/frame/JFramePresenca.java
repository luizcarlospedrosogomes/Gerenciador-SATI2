package com.lcpg.app.frame;

import com.lcpg.app.Service.ListenerSocket;
import static com.lcpg.app.frame.JFrameEvento.createYourPopUp;
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

public class JFramePresenca extends javax.swing.JFrame {
    private ListenerSocket listener;
    public int idUsuario;
    
    public JFramePresenca() {
        initComponents();
    }
    
    public JFramePresenca(int idUsuario){
        this.idUsuario = idUsuario;
        initComponents();
        preencherTable();
    }
    
    
    public void preencherTable(){
        this.listener = new ListenerSocket();
        this.listener.listEventoPresenca();
        DefaultTableModel model = (DefaultTableModel) jTableEventoPresenca.getModel();
        String[] columnNames = {"Cod.","Numero do evento", "Evento","Tipo evento ", "Data prevista", "Hora inico prevista", "Hora fim prevista",  "Cadastrado por"};
        model.setColumnIdentifiers(columnNames);
        Object[] row = new Object[8];
        for (Map<String, String> map : this.listener.listaEventoPresenca) {
            Object [] dados = map.values().toArray();
            row[0] = dados[2];// cod
            row[1] = dados[7];// n evento
            row[2] = dados[3];// evento
            row[3] = dados[5];// tipo evento
            row[4] = dados[0];// data
            row[5] = dados[4];// hora ini
            row[6] = dados[1];// hora fim
            row[7] = dados[6];// cadastrad por
            model.addRow(row);
            
       }
    }
    
     
    public  JPopupMenu createYourPopUp(int rowindex, JTable jTableListarEventos)
    {
       ListenerSocket listener = new ListenerSocket();
        JPopupMenu popup       = new JPopupMenu();
        JMenuItem controle       = new JMenuItem("Controlar Evento");
        
        popup.add(controle);
        
        controle.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String eventoNome   = jTableListarEventos.getValueAt(jTableListarEventos.getSelectedRow(), 2).toString();
                String idEvento   = jTableListarEventos.getValueAt(jTableListarEventos.getSelectedRow(), 1).toString();
                String idPresencaEvento = jTableListarEventos.getValueAt(jTableListarEventos.getSelectedRow(), 0).toString();
               
                int res = JOptionPane.showConfirmDialog(null, "Gostaria de controlar "+ eventoNome + " ?", "", JOptionPane.YES_NO_OPTION);
                switch (res) {
                    case JOptionPane.YES_OPTION:
                    int p = jTableListarEventos.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) jTableListarEventos.getModel();
                    model.removeRow(p);
                    listener.controlarEvento(idPresencaEvento, idEvento, JFramePrincipal.idUsuario, 1);
                    //    System.out.println("evento "+evento+" idEvento"+idEvento);
                    JFramePresencaAluno jFramePresencaAluno = new JFramePresencaAluno(eventoNome, idEvento, JFramePrincipal.idUsuario );
                    jFramePresencaAluno.setVisible(true);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEventoPresenca = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonAtualizar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableEventoPresenca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEventoPresenca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEventoPresencaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEventoPresenca);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAtualizar)
                    .addComponent(jButtonSair))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel1.setText("CONTROLE DE PRESENÇA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        DefaultTableModel dm = (DefaultTableModel) jTableEventoPresenca.getModel();
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

    private void jTableEventoPresencaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEventoPresencaMouseClicked
         jTableEventoPresenca.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            int r = jTableEventoPresenca.rowAtPoint(e.getPoint());
            if (r >= 0 && r < jTableEventoPresenca.getRowCount()) {
                jTableEventoPresenca.setRowSelectionInterval(r, r);
            } else {
                jTableEventoPresenca.clearSelection();
            }
            int rowindex = jTableEventoPresenca.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = createYourPopUp(rowindex,jTableEventoPresenca);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
            
    });
    }//GEN-LAST:event_jTableEventoPresencaMouseClicked

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
            java.util.logging.Logger.getLogger(JFramePresenca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePresenca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePresenca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePresenca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePresenca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEventoPresenca;
    // End of variables declaration//GEN-END:variables
}
