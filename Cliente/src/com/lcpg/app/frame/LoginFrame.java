package com.lcpg.app.frame;

import com.lcpg.app.Service.ClienteService;
import com.lcpg.app.bean.ChatMessage;
import com.lcpg.app.bean.ChatMessage.Action;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {

    private Socket socket;
    private ChatMessage message;
    private ClienteService service;
    
    public LoginFrame() {
        initComponents();
    }
    
    private class ListenerSocket implements  Runnable{
        private ObjectInputStream input;
        private ObjectOutputStream output;
        
        public ListenerSocket(Socket socket){
            try {
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
        
        @Override
        public void run(){
            ChatMessage message = null;
            try {
                while((message = (ChatMessage) input.readObject()) != null){
                    Action action = message.getAction();
                     if(action.equals(ChatMessage.Action.CONNECT)){
                        connected(message);
                    }else if(action.equals(ChatMessage.Action.DISCONNECT)){
                        disconnect();
                        socket.close();
                    }
                }
            } catch (IOException ex) {
                  System.out.println("erro "+ ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("erro "+ ex);
            }
        }
    }
    
    private void connected(ChatMessage message){
        if(message.getTexto().equals("403")){
            JOptionPane.showMessageDialog(this, "Verifique sua conexao, usuario e senha. Não foi possivel conectar ao servidor.");
            return;
        }else if(message.getTexto().equals("200")){
            JOptionPane.showMessageDialog(this, "Bem vindo ao sistema "+ message.getNome());
            JFramePrincipal jFramePrincipal = new JFramePrincipal();
            jFramePrincipal.setVisible(true);
            this.setVisible(false);
        }else if(message.getTexto().equals("200_1")){
            JOptionPane.showMessageDialog(this, "Bem vindo ao sistema Administrador");
            JFrameAdmin jFrameAdmin = new JFrameAdmin(message,socket);
            jFrameAdmin.setVisible(true);            
            this.setVisible(false);
        }
    }
    
    private void disconnect(){
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFieldUsario = new javax.swing.JTextField();
        jTextFieldSenha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelErro = new javax.swing.JLabel();
        jButtonConectar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jLabelServidor = new javax.swing.JLabel();
        jTextFieldIPServidor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPorta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSenhaActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario");

        jLabel2.setText("Senha");

        jLabelErro.setEnabled(false);

        jButtonConectar.setText("Conectar");
        jButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarActionPerformed(evt);
            }
        });

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jLabelServidor.setText("Servidor");

        jTextFieldIPServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIPServidorActionPerformed(evt);
            }
        });

        jLabel4.setText("Porta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelServidor)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPorta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(jButtonConectar))
                    .addComponent(jTextFieldSenha)
                    .addComponent(jTextFieldUsario))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelErro)
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldUsario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelServidor)
                    .addComponent(jTextFieldIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConectar)
                    .addComponent(jButtonSair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelErro))
        );

        jLabel3.setText("Controle de Eventos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel3)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSenhaActionPerformed

    private void jButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarActionPerformed
        String usuario = jTextFieldUsario.getText();
        String senha   = jTextFieldSenha.getText();
        int    porta   = Integer.valueOf(jTextFieldPorta.getText());
        if(!usuario.isEmpty() && !senha.isEmpty() && !jTextFieldPorta.getText().isEmpty() && !jTextFieldIPServidor.getText().isEmpty()){
            this.message = new ChatMessage();
            this.message.setAction(Action.CONNECT);
            this.message.setNome(usuario);
            this.message.setSenha(senha);
            this.service = new ClienteService(jTextFieldIPServidor.getText(), porta);
            this.socket  = this.service.connect();
            new Thread(new ListenerSocket(this.socket)).start();
            this.service.send(message);
        }else{
           JOptionPane.showMessageDialog(this, "Insira Usuario, Senha, IP servidor e porta."); 
        }
    }//GEN-LAST:event_jButtonConectarActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jTextFieldIPServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIPServidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIPServidorActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConectar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelErro;
    private javax.swing.JLabel jLabelServidor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldIPServidor;
    private javax.swing.JTextField jTextFieldPorta;
    private javax.swing.JTextField jTextFieldSenha;
    private javax.swing.JTextField jTextFieldUsario;
    // End of variables declaration//GEN-END:variables
}
