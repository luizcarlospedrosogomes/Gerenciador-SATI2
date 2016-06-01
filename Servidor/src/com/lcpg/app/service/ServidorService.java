/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.service;

import com.lcpg.app.banco.Evento;
import com.lcpg.app.banco.Usuario;
import com.lcpg.app.bean.Mensagem;
import com.lcpg.app.bean.Mensagem.Action;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author usuario
 */
    public class ServidorService {
    
    private ServerSocket serverSocket;
    private Socket       socket;
    private Map<String, ObjectOutputStream> mapOnlines = new  HashMap<String, ObjectOutputStream>();
    private ResultSet res;
    
    public ServidorService(int porta){
      
        try {
            serverSocket = new ServerSocket(porta);
           while(true){
               socket = serverSocket.accept();
               new Thread(new ListernerSocket(socket)).start();
           }
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    private class ListernerSocket implements Runnable{
        private ObjectOutputStream output;
        private ObjectInputStream input;
        
        public ListernerSocket(Socket socket){
            try {
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input  = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        
        @Override
        public void run(){
             Mensagem message = null;
            try {
                while((message = (Mensagem) input.readObject()) != null){
                Action action = message.getAction();
                
                if(action.equals(Action.CONNECT)){
                   conect(message, output);
                }else if(action.equals(Action.DISCONNECT)){
                    disconnect(message, output);
                    return;
                }else if(action.equals(Action.TIPO_EVENTO)){
                    tipoEvento(message, output);
                }else if(action.equals(Action.CADASTRO_EVENTO)){
                    cadastroEvento(message, output);
                }else if(action.equals(Action.ALUNO_CADASTRAR)){
                    cadastroAluno(message, output);
                }else if(action.equals(Action.ALUNO_LIST)){
                    alunoList(message, output);
                }
                else if(action.equals(Action.ALUNO_UPDATE)){
                    alunoAtualizar(message, output);
                    System.out.println("aluno update");
                }
                else if(action.equals(Action.EVENTO_LIST)){
                    eventoList(message, output);
                }else if(action.equals(Action.EXCLUIR_EVENTO)){
                    excluirEvento(message, output);
                }else if(action.equals(Action.ALUNO_EXCLUIR)){
                     excluirAluno(message, output);
                }
             }  
            } catch (IOException ex) {
                disconnect(message, output);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void conect(Mensagem message, ObjectOutputStream output){
        Usuario usuario = new Usuario();
        this.res = usuario.consultaLogin(message.getNome(), message.getSenha());
        try {
            if(res.next()){
                if(message.getNome().equals("admin") && message.getSenha().equals("admin")){
                    message.setTexto("200_1");
                    message.setIdUsuario(res.getInt(1));
                }else{
                    message.setTexto("200");
                    message.setIdUsuario(res.getInt(1));
                }
            }else
                    message.setTexto("403");
        } catch (SQLException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendOne(message, output);
    }
    
    private void tipoEvento(Mensagem message, ObjectOutputStream output){
        message.setAction(Action.TIPO_EVENTO);
        Evento evento = new Evento();
        HashMap<String, String> hmap= new HashMap<String, String>();
        this.res = evento.consultaTipoEvento();
        try {
            while(this.res.next()) {
                System.out.println(res.getInt(1)+" - "+res.getString(2));
                hmap.put(res.getString(1), res.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        message.setTipoEventoList(hmap);
        sendOne(message, output);
       
    }
    
    private void cadastroAluno(Mensagem message, ObjectOutputStream output){
        Usuario usuario = new Usuario();
        usuario.inserirAluno(message.getAlunoNome(), message.getAlunoRA(), message.getAlunoEmail(),  message.getAlunoTelefone(), message.getAlunoCurso(), message.getAlunoPeriodo());
    }
    
      private void alunoAtualizar(Mensagem message, ObjectOutputStream output){
        Usuario usuario = new Usuario();
        usuario.atualizarAluno(message.getAlunoID(), message.getAlunoNome(), message.getAlunoRA(), message.getAlunoEmail(),  message.getAlunoTelefone(), message.getAlunoCurso(), message.getAlunoPeriodo());
    }
      
    private void cadastroEvento(Mensagem message, ObjectOutputStream output){
        Evento evento = new Evento();
        evento.inserirEvento(message.getNomeEvento(), message.getHoraInicioEvento(), message.getHoraFimEvento(), message.getTipoEvento(), message.getIdUsuario(), message.getDataEvento());
        sendOne(message, output);
    }
    
    private void excluirEvento(Mensagem message, ObjectOutputStream output){
        Evento  evento = new Evento();
        if(evento.excluirEvento(message.getIdEvento())){
            message.setResposta("200");
        }else{
            message.setResposta("501");
        }
        
        sendOne(message, output);
    }
    
     private void excluirAluno(Mensagem message, ObjectOutputStream output){
        Usuario  usuario = new Usuario();
        if(usuario.excluirAluno(message.getAlunoID())){
            message.setResposta("200");
        }else{
            message.setResposta("501");
        }
        
        sendOne(message, output);
    }
    
    private void eventoList(Mensagem message, ObjectOutputStream output){
        Evento evento = new Evento();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        this.res = evento.eventoList(message.getIdUsuario());
        try {
        ResultSetMetaData meta = this.res.getMetaData();
        while (this.res.next()) {
            Map map = new HashMap();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = res.getString(key);
                map.put(key, value);
            }
            list.add(map);
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        message.setListEvento(list);
        sendOne(message, output);
    }
    
    private void alunoList(Mensagem message, ObjectOutputStream output){
        Usuario usuario = new Usuario();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        this.res = usuario.usuarioList();
        try {
        ResultSetMetaData meta = this.res.getMetaData();
        while (this.res.next()) {
            Map map = new HashMap();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = res.getString(key);
                map.put(key, value);
            }
            list.add(map);
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        message.setListAluno(list);
        sendOne(message, output);
    }
    
    private void disconnect(Mensagem message, ObjectOutputStream output){
//        mapOnlines.remove(message.getNome());
     //   message.setTexto("bye");
       // message.setAction(Action.SEND_ONE);
  //      sendAll(message, output);
        System.out.println("cliente "+ message.getNome()+" deixou a sala");
        
    }
    

  /*  
    private void sendAll(Mensagem message, ObjectOutputStream output){
        for(Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()){
            if(!kv.getKey().equals(message.getNome())){
                try {
                    kv.getValue().writeObject(message);
                } catch (IOException ex) {
                    Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
*/
    private void sendOne(Mensagem message, ObjectOutputStream output){
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
