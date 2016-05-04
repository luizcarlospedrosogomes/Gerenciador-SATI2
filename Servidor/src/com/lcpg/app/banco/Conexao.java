
package com.lcpg.app.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private String url = "jdbc:postgresql://192.168.70.101:5432/distribuido";  
    private String usuario = "postgres";  
    private String senha = "admin";  
    private Connection con;  
    public Conexao(){
        conectar();        
    }
        
    public Connection conectar(){
        try {  
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url, usuario, senha);  

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void fecharConexao(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
