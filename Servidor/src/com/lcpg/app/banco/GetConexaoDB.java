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
public class GetConexaoDB {
    private Properties prop;
    private FileInputStream file;  
    private String IP;
    private int porta;
    private String usuario;
    private String banco;
    private String senha;
            
           
    
    public GetConexaoDB(){
        getDadosConexaoDB();
    }
    
    public Properties  getDadosConexaoDB(){
        this.prop = new Properties();
        try {
            this.file = new FileInputStream("./src/properties/dados.properties"); 
            this.prop.load(this.file);
            setIP(prop.getProperty("prop.server.host"));
            setPorta(Integer.parseInt(prop.getProperty("prop.server.porta")));
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
           
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
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
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
