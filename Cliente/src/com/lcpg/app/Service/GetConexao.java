/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        try { 
            this.prop = new Properties();
            this.file = new FileInputStream("./src/properties/dados.properties");
            this.prop.load(this.file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GetConexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       // getIPPorta();
    }
    
    public Properties  getIPPorta(){
        setIP(this.prop.getProperty("prop.server.host"));
        setPorta(Integer.parseInt(this.prop.getProperty("prop.server.porta")));
        return this.prop;
    }
    
    public void setIPPOrta(String IP, String porta){
        try {
            this.prop.setProperty("prop.server.host", IP);
            this.prop.setProperty("prop.server.porta", porta);
            //this.prop.put("prop.server.pota", porta);
            File f = new File("./src/properties/dados.properties");
            OutputStream out = new FileOutputStream( f );
            this.prop.store(out, "This is an optional header comment string");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GetConexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
