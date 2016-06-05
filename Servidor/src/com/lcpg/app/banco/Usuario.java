
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
       String sql = "select id, nome, senha from usuario where nome = ? and senha = ? limit 1" ;
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
    
    public void inserirAluno(String nome, String RA, String email, String telefone, String curso, String periodo){
        String sql = "insert into usuario(nome, ra, curso, periodo, email, telefone)" +
            		"values(?,?,?,?,?,?);";
        try {
            
            pst = this.con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, RA);
            pst.setString(3, curso);
            pst.setInt(4, Integer.parseInt(periodo));
            pst.setString(5, email);
            pst.setString(6, telefone);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizarAluno(String idAluno,String nome, String RA, String email, String telefone, String curso, String periodo){
        String sql = "update usuario set nome = ?"
                + " , ra = ?, curso = ?"
                + " , periodo = ?"
                + " , email = ?"
                + " , telefone = ?" +
            	  " where id = ?";
        try {
            
            pst = this.con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, RA);
            pst.setString(3, curso);
            pst.setInt(4, Integer.parseInt(periodo));
            pst.setString(5, email);
            pst.setString(6, telefone);
            pst.setInt(7, Integer.parseInt(idAluno));
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet usuarioList(){
        String sql = "select id\n" +
                     "     , nome\n" +
                     "     , curso\n" +
                     "     , periodo\n" +
                     "     , turno\n" +
                     "     , email\n" +
                     "     , telefone\n" +
                     "     , ra \n" +
                     "  from usuario \n" +
                     " where id > 3";
                
        try {
            this.pst = con.prepareStatement(sql);
            this.result = this.pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.result;
    }
     
    public boolean excluirAluno(String idAluno){
        try {
            Statement stmt = this.con.createStatement();
            String sql = "DELETE from usuario where id= " + Integer.parseInt(idAluno) + ";";
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            return false;
           // Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
