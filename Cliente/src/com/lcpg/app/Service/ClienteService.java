package com.lcpg.app.Service;

import com.lcpg.app.bean.Mensagem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteService {
    private Socket  socket;
    private ObjectOutputStream output;
    private String ip;
    private int porta;
    
    public ClienteService(String ip, int porta){
        this.ip    = ip;
        this.porta = porta;
    }
    
    public Socket connect(){
        try {
            this.socket = new Socket(this.ip, this.porta);
            this.output = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException ex) {
            System.out.println("error "+ ex);
        }catch (IOException ex) {
            System.out.println("error "+ ex);
        }
        return socket;
    }
    
    public void send(Mensagem message){
        try {
          output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
