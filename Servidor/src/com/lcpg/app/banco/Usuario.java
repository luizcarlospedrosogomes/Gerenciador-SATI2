
package com.lcpg.app.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {
    private Conexao   conectar;
    private Statement stm;
    private ResultSet result;
    private PreparedStatement pst;
    private Connection con;
   
    public Usuario(){
     this.conectar = new Conexao();
     this.con      = this.conectar.conectar();
    }
   public ResultSet consultaLogin(String nome, String senha){
       String sql = "select nome, senha from usuario where nome = ? and senha = ?" ;
       System.out.println("nome "+nome +" senha "+senha);
       try {
            this.pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, senha);
            System.out.println(sql);
            this.result = this.pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.result;
   }
    
    
}
