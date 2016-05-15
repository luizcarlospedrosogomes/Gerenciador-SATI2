/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.Service;

import com.lcpg.app.bean.Mensagem;
import com.lcpg.app.bean.Mensagem.Action;
import com.lcpg.app.frame.JFrameAdmin;
import com.lcpg.app.frame.JFrameCadastroEvento;
import com.lcpg.app.frame.JFrameCadastroEventoAdicionar;
import com.lcpg.app.frame.LoginFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ListenerSocket{
        private ObjectInputStream input;
        private ObjectOutputStream output;
        private Socket socket;
        private Mensagem message;
        private ClienteService service;
        private String usuario;
        private String senha;

        private GetConexao conexao;
       
        public ListenerSocket(){
           this.conexao = new  GetConexao();                   
        }
 
        
        public void conectaServidor(){
            this.message = new Mensagem();
            this.message.setAction(Action.CONNECT);
            this.message.setNome(getUsuario());
            this.message.setSenha(getSenha());
            System.out.println("conectando no IP: "+this.conexao.getIP()+":"+this.conexao.getPorta());
            this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
            this.socket = this.service.connect();
            this.service.send(message);
            respostaServidor(this.socket);
        }
        
        public void  tipoEnvento(){
            this.message = new Mensagem();
            this.message.setAction(Action.TIPO_EVENTO);
            this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
            this.socket = this.service.connect();
            this.service.send(this.message);
            respostaServidor(this.socket);
            System.out.println("respota servidor");
        }
        
        private void conectar(Mensagem message){
            LoginFrame loginFrame = new LoginFrame();
            if(message.getTexto().equals("200_1")){
                System.out.println("esta em 200_1");
                System.out.println(message.getIdUsuario());
                JOptionPane.showMessageDialog(null, "Bem vindo ao sistema Administrador");
                loginFrame.setVisible(false);
                JFrameAdmin jFrameAdmin = new JFrameAdmin();
                jFrameAdmin.setVisible(true);         
             }else if(message.getTexto().equals("200")){
                JOptionPane.showMessageDialog(null, "Bem vindo ao sistema "+ message.getNome()+ " ID "+  message.getIdUsuario());
                JFrameCadastroEvento jFrameCadastroEvento = new JFrameCadastroEvento();
                jFrameCadastroEvento.setVisible(true);
                loginFrame.setVisible(false);
             }else if(message.getTexto().equals("403")){
                  JOptionPane.showMessageDialog(null, "Verifique sua conexao, usuario e senha. Não foi possivel conectar ao servidor.");
                  return;
             }
            try {
                System.out.println("fechou socket");
                this.socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public  void resTipoEvento(Mensagem message){
            System.out.println(message.getTipoEventoList().values());
            System.out.println(message.getTipoEventoList().keySet());
            JFrameCadastroEventoAdicionar jFrameCadastroEventoAdicionar = new JFrameCadastroEventoAdicionar();
            jFrameCadastroEventoAdicionar.preencherComboBox(message.getTipoEventoList());
            jFrameCadastroEventoAdicionar.setVisible(true);
            //return message.getTipoEventoList();
        }

        private void respostaServidor(Socket socket){
            
            try {
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            Mensagem message = null;
            try {
                message = (Mensagem) input.readObject();
                Action action = message.getAction();
                if (action.equals(Mensagem.Action.CONNECT)) {
                    conectar(message);
                } else if (action.equals(Mensagem.Action.DISCONNECT)) {
                    // disconnect();
                    socket.close();
                } else if (action.equals(Mensagem.Action.TIPO_EVENTO)) {
                    resTipoEvento(message);
                }
            } catch (IOException ex) {
                  System.out.println("erro "+ ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("erro "+ ex);
            }
        }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
