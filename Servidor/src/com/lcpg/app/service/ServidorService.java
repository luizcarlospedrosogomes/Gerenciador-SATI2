/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.service;

import com.lcpg.app.banco.Usuario;
import com.lcpg.app.bean.ChatMessage;
import com.lcpg.app.bean.ChatMessage.Action;
import com.sun.xml.internal.ws.client.SenderException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ServidorService {
    
    private ServerSocket serverSocket;
    private Socket       socket;
    private Map<String, ObjectOutputStream> mapOnlines = new  HashMap<String, ObjectOutputStream>();
    private ResultSet res;
    
    public ServidorService(int porta){
      
        try {
            serverSocket = new ServerSocket(porta);
           while(true){
               socket = serverSocket.accept();
               new Thread(new ListernerSocket(socket)).start();
           }
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    private class ListernerSocket implements Runnable{
        private ObjectOutputStream output;
        private ObjectInputStream input;
        
        public ListernerSocket(Socket socket){
            try {
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input  = new ObjectInputStream(socket.getInputStream());
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
                
                if(action.equals(Action.CONNECT)){
                   conect(message, output);
                }else if(action.equals(Action.DISCONNECT)){
                    disconnect(message, output);
                    return;
                }else if(action.equals(Action.SEND_ONE)){
                }else if(action.equals(Action.SEND_ALL)){
                    
                }else if(action.equals(Action.USERS_ONLINE)){
                    
                }}
            } catch (IOException ex) {
                disconnect(message, output);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void conect(ChatMessage message, ObjectOutputStream output){
        Usuario usuario = new Usuario();
        this.res = usuario.consultaLogin(message.getNome(), message.getSenha());
        try {
            if(res.next()){
                if(message.getNome().equals("admin") && message.getSenha().equals("admin"))
                    message.setTexto("200_1");
                else
                    message.setTexto("200");
            }else
                    message.setTexto("403");
        } catch (SQLException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendOne(message, output);
    }
    
    private void disconnect(ChatMessage message, ObjectOutputStream output){
        mapOnlines.remove(message.getNome());
        message.setTexto("bye");
        message.setAction(Action.SEND_ONE);
        sendAll(message, output);
        System.out.println("cliente "+ message.getNome()+" deixou a sala");
        
    }
    
    private void sendAll(ChatMessage message, ObjectOutputStream output){
        for(Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()){
            if(!kv.getKey().equals(message.getNome())){
                try {
                    kv.getValue().writeObject(message);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private void sendOne(ChatMessage message, ObjectOutputStream output){
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
