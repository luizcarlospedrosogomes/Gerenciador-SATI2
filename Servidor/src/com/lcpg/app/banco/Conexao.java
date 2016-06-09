
package com.lcpg.app.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private String url;// = "jdbc:postgresql://192.168.70.101:5432/distribuido";  
    private String usuario;// = "postgres";  
    private String senha;// = "admin";  
    private String banco;
    private Connection con; 
    private GetConexaoDB conexao;
   
    public Conexao(){
        this.conexao = new GetConexaoDB();
        this.banco   = conexao.getBanco();
        this.usuario = conexao.getUsuario();
        this.senha   = conexao.getSenha();
        this.url     = "jdbc:mysql://"+conexao.getIP()+"/"+this.banco;
        System.out.println(url);
        conectar();        
    }
        
    public Connection conectar() {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(this.url, this.usuario, this.senha);  

        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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
