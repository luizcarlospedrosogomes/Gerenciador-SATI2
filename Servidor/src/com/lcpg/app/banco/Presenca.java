
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
public class Presenca {

    private Conexao   conectar;
    private Statement stm;
    private ResultSet result;
    private PreparedStatement pst;
    private Connection con;
   
    public Presenca(){
     this.conectar = new Conexao();
     this.con      = this.conectar.conectar();
     
    }
    
    public ResultSet listarEventoPresenca(){
    String sql = "select pe.id as id_presenca_evento"
            + ", e.id as n_evento"
            + ", u.nome as cadastrado_por"
            + ", e.nome as nome_evento"
            + ", te.descricao as tipo_evento"
            + ", e.data_ini as hora_ini_previsto"
            + ", e.data_fim as hora_fim_previsto"
            + ", e.data as data_prevista\n" +
           "from evento e\n" +
          "inner join presenca_evento pe\n" +
             "on pe.id_evento = e.id\n" +
           "inner join tipo_evento te\n" +
              "on te.id = e.tipo_evento_id\n" +
           "inner join usuario u\n" +
              "on u.id = e.usuario_id\n" +
           "where pe.controlado = 0";
    
                   
        try {
            this.pst = con.prepareStatement(sql);
            this.result = this.pst.executeQuery();
        } catch (SQLException ex) {
           
        }
        //System.out.println(sql);
        return this.result;
    }
    
    
    public void atualizaPresencaEvento(String idEvento, String idUsuarioControle, int statusControle){
     String sql = "update presenca_evento"
                + "  set id_usuario_controle = ?"
                + " , controlado = ? "
                + " , data_hora_ini = now()"             
              	+ " where id_evento = ?";
        try {
            
            pst = this.con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(idUsuarioControle));
            pst.setInt(2, statusControle);
            pst.setInt(3, Integer.parseInt(idEvento));
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}