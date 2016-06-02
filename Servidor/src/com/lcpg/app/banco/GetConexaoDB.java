
package com.lcpg.app.banco;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author usuario
 */
public class GetConexaoDB {
    private Properties prop;
    private FileInputStream file;  
    private String IP;
    private String porta;
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
            setIP(prop.getProperty("prop.server.ip"));
            setPorta(prop.getProperty("prop.server.porta"));
            setBanco(prop.getProperty("prop.server.banco"));
            setUsuario(prop.getProperty("prop.server.usuario"));
            setSenha(prop.getProperty("prop.server.senha"));
            
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
    public String getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(String porta) {
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
