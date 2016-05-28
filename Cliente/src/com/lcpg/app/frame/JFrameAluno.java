/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.frame;

import com.lcpg.app.Service.ListenerSocket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.nio.file.Files.delete;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class JFrameAluno extends javax.swing.JFrame {

    private ListenerSocket listener;
    
    public JFrameAluno() {
        initComponents();
        preencherTable();
    }
    
    public void preencherTable(){
        this.listener = new ListenerSocket();
        this.listener.alunoList();
        DefaultTableModel model = (DefaultTableModel) jTableAluno.getModel();
        String[] columnNames = {"Numero", "Nome", "R.A.", "Email", "Telefone",  "Curso", "Periodo"};
        model.setColumnIdentifiers(columnNames);
        Object[] row = new Object[7];
        System.out.println(this.listener.listAluno);
        for (Map<String, String> map : this.listener.listAluno) {
            Object [] dados = map.values().toArray();
            row[0] = dados[4]; //id
            row[1] = dados[3]; //nome
            row[2] = dados[7]; //ra
            row[3] = dados[6]; //email
            row[4] = dados[0]; //telefone
            row[5] = dados[2]; //curso
            row[6] = dados[1]; //periodo
            model.addRow(row);           
        }
    }
    /*
    public static void atualizarTable(){
        DefaultTableModel dm = (DefaultTableModel) jTableAluno.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        preencherTable();
    }
    */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAluno = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAluno);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 530, Short.MAX_VALUE)
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

        jLabel1.setText("CADASTRO DE ALUNOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        JFrameAlunoCadastro jFrameAlunoCadastro = new JFrameAlunoCadastro();
        jFrameAlunoCadastro.setVisible(true);
    }//GEN-LAST:event_jButtonAdicionarActionPerformed
    
    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        DefaultTableModel dm = (DefaultTableModel) jTableAluno.getModel();
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

    private void jTableAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlunoMouseClicked
        jTableAluno.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            int r = jTableAluno.rowAtPoint(e.getPoint());
            if (r >= 0 && r < jTableAluno.getRowCount()) {
                jTableAluno.setRowSelectionInterval(r, r);
            } else {
                jTableAluno.clearSelection();
            }
            int rowindex = jTableAluno.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = createYourPopUp(rowindex,jTableAluno);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
            
    });
    }//GEN-LAST:event_jTableAlunoMouseClicked
    
    public static JPopupMenu createYourPopUp(int rowindex, JTable jTableAluno)
    {
       ListenerSocket listener = new ListenerSocket();
        JPopupMenu popup       = new JPopupMenu();
        JMenuItem edit         = new JMenuItem("Editar Aluno");       
        JMenuItem delete       = new JMenuItem("Exluir aluno");
        popup.add(edit);
        popup.add(delete);
        
        edit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){     
                /*{"Numero", "Nome", "R.A.", "Email", "Telefone",  "Curso", "Periodo"};*/
                String idAluno  = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 0).toString();
                String nome     = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 1).toString();
                String RA       = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 2).toString();
                String email    = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 3).toString();
                String telefone = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 4).toString();
                String curso    = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 5).toString();
                String periodo  = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 6).toString();
                JFrameAlunoCadastro jFrameAlunoCadastro = new JFrameAlunoCadastro(idAluno, nome, RA, email, telefone, periodo, curso);
                jFrameAlunoCadastro.setVisible(true);
            }
        });
        
        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String aluno   = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 1).toString();
                String idAluno = jTableAluno.getValueAt(jTableAluno.getSelectedRow(), 0).toString();
                int res = JOptionPane.showConfirmDialog(null, "Gostaria de excluir "+ aluno + " ?", "", JOptionPane.YES_NO_OPTION);
                switch (res) {
                    case JOptionPane.YES_OPTION:
                    int p = jTableAluno.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) jTableAluno.getModel();
                    model.removeRow(p);
                    listener.excluirAluno(idAluno);
                    JOptionPane.showMessageDialog(null, "Aluno excluido");
                    break;
                    case JOptionPane.NO_OPTION:
                    JOptionPane.showMessageDialog(null, "Cancelado");
                    break;
                }
            }
        });
        return popup;
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
            java.util.logging.Logger.getLogger(JFrameAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAluno;
    // End of variables declaration//GEN-END:variables
}
