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
import com.lcpg.app.frame.LoginFrame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
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
        private String resLogin;
        private String iP;
        private int porta;
        private Properties prop;
        private FileInputStream file;
       
        public ListenerSocket(){
            getIPPorta();
        }
        
        public Properties  getIPPorta(){
            this.prop = new Properties();
            try {
                this.file = new FileInputStream("./src/properties/dados.properties"); 
                this.prop.load(this.file);
                setiP(prop.getProperty("prop.server.host"));
		setPorta(Integer.parseInt(prop.getProperty("prop.server.porta")));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            return prop;
        }
        
        
        public void conectaServidor(){
            this.message = new Mensagem();
            this.message.setAction(Action.CONNECT);
            this.message.setNome(getUsuario());
            this.message.setSenha(getSenha());
            System.out.println("conectando no IP: "+getiP()+":"+getPorta());
            this.service = new ClienteService(getiP(), getPorta());
            this.socket = this.service.connect();
            this.service.send(message);
            respostaServidor(this.socket);
        }
        
        public void tipoEnvento(){
            this.message = new Mensagem();
            this.message.setAction(Action.TIPO_EVENTO);
            this.service = new ClienteService(getiP(), getPorta());
            this.socket = this.service.connect();
            this.service.send(message);
            respostaServidor(this.socket);
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
       
  
        public void respostaServidor(Socket socket){
            
            try {
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
            Mensagem message = null;
            try {
               // while((message = (ChatMessage) input.readObject()) != null){
                    message = (Mensagem) input.readObject();
                    Action action = message.getAction();
                    if(action.equals(Mensagem.Action.CONNECT)){
                        conectar(message);                   
                    }else if(action.equals(Mensagem.Action.DISCONNECT)){
                       // disconnect();
                        socket.close();
                    }
                //}
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

    /**
     * @return the resLogin
     */
    public String getResLogin() {
        return resLogin;
    }

    /**
     * @param resLogin the resLogin to set
     */
    public void setResLogin(String resLogin) {
        this.resLogin = resLogin;
    }

    /**
     * @return the iP
     */
    public String getiP() {
        return iP;
    }

    /**
     * @param iP the iP to set
     */
    public void setiP(String iP) {
        this.iP = iP;
    }

    /**
     * @return the porta
     */
    public int getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(int porta) {
        this.porta = porta;
    }
}


