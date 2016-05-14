/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GetConexao {
    private Properties prop;
    private FileInputStream file;  
    private String IP;
    private int porta;
    
    public GetConexao(){
        getIPPorta();
    }
    
    public Properties  getIPPorta(){
        this.prop = new Properties();
        try {
            this.file = new FileInputStream("./src/properties/dados.properties"); 
            this.prop.load(this.file);
            setIP(prop.getProperty("prop.server.host"));
            setPorta(Integer.parseInt(prop.getProperty("prop.server.porta")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prop;
    }

    /**
     * @return the IP
     */
    public String getIP() {
        return IP;
    }

    /**
     * @param IP the IP to set
     */
    public void setIP(String IP) {
        this.IP = IP;
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
