package com.lcpg.app.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Evento {
    private Conexao   conectar;
    private Statement stm;
    private ResultSet result;
    private PreparedStatement pst;
    private Connection con;
   
    public Evento(){
     this.conectar = new Conexao();
     this.con      = this.conectar.conectar();
    
    }
   
//   /public ResultSet co select id, descricao from tipo_evento
    
    public ResultSet consultaTipoEvento(){
       String sql = "select id, descricao from tipo_evento" ;
      
       try {
            this.pst = con.prepareStatement(sql);
            this.result = this.pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.result;
   }
}
