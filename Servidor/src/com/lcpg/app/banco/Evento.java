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
    
    public void inserirEvento(String nome, String data_ini, String data_fim, int tipoEvento, int idUsuario,String data){
        	
            String sql = "insert into evento(nome, data_ini, data_fim, tipo_evento_id, usuario_id, data)" +
            		"values(?,?,?,?,?,?);";
        try {
            System.out.println(sql);
            System.out.println("tipo evento: "+ tipoEvento);
            pst = this.con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, data_ini);
            pst.setString(3, data_fim);
            pst.setInt(4, tipoEvento);
            pst.setInt(5, idUsuario);
            pst.setString(6, data);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet eventoList(int idUsuario){
        String sql = "select  e.id"
                + "         , e.nome"
                + "         , e.data_ini"
                + "         , e.data_fim"
                + "         , e.data"
                + "         , u.nome as usuario "
                + "      from evento e\n" +
                      " inner join tipo_evento te\n" +
                        " on te.id = e.tipo_evento_id\n" +
                       " inner join usuario u\n" +
                          " on u.id = e.usuario_id"+
                       " where u.id = ?";
                
        try {
            this.pst = con.prepareStatement(sql);
            pst.setInt(1, 3);
            this.result = this.pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.result;
    }
    
    public boolean excluirEvento(String idEvento){
        try {
            Statement stmt = this.con.createStatement();
            String sql = "DELETE from evento where id= " + Integer.parseInt(idEvento) + ";";
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            return false;
           // Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
        
}

