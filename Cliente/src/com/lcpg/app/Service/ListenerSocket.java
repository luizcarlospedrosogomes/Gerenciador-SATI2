/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.Service;

import com.lcpg.app.bean.Mensagem;
import com.lcpg.app.bean.Mensagem.Action;
import com.lcpg.app.frame.JFrameAdmin;
import com.lcpg.app.frame.JFrameEventocadastro;
import com.lcpg.app.frame.JFramePrincipal;
import static com.lcpg.app.frame.JFramePrincipal.idUsuario;
import com.lcpg.app.frame.LoginFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ListenerSocket{
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private Mensagem message;
    private ClienteService service;
    private String usuario;
    private String senha;
    public List<Map<String, String>> listaEventoPresenca;// = new ArrayList<Map<String, String>>();
    public List<Map<String, String>> listEvento;// = new ArrayList<Map<String, String>>();
    public List<Map<String, String>> listAluno;
    public String [] dadosAluno = new String[4];
    private GetConexao conexao; 

    public ListenerSocket() {
        this.conexao = new GetConexao();
        
    }

    public void conectaServidor() {
        this.message = new Mensagem();
        this.message.setAction(Action.CONNECT);
        this.message.setNome(getUsuario());
        this.message.setSenha(getSenha());
        System.out.println("conectando no IP: " + this.conexao.getIP() + ":" + this.conexao.getPorta());
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(message);
        respostaServidor(this.socket);
    }

    public void tipoEnvento() {
        this.message = new Mensagem();
        this.message.setAction(Action.TIPO_EVENTO);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
        respostaServidor(this.socket);
        System.out.println("respota servidor");
    }

    public void cadastroEvento(String IdUsuario,String nomeEvento, String horaIni, String horaFim, int tipoEvento, String dataEvento) {
        this.message = new Mensagem();
        this.message.setAction(Action.CADASTRO_EVENTO);
        this.message.setNomeEvento(nomeEvento);
        this.message.setDataEvento(dataEvento);
        this.message.setHoraInicioEvento(horaIni);
        this.message.setHoraFimEvento(horaFim);
        this.message.setTipoEvento(tipoEvento);
        this.message.setIdUsuario(Integer.parseInt(IdUsuario));
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
    }
    
    public void cadastrarAluno(String nome, String RA, String email, String curso, String periodo, String telefone){
        this.message = new Mensagem();
        this.message.setAction(Action.ALUNO_CADASTRAR);
        this.message.setAlunoNome(nome);
        this.message.setAlunoRA(RA);
        this.message.setAlunoEmail(email);
        this.message.setAlunoCurso(curso);
        this.message.setAlunoPeriodo(periodo);
        this.message.setAlunoTelefone(telefone);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
    }
    
      public void atualizarAluno(String idAluno, String nome, String RA, String email, String curso, String periodo, String telefone){
        this.message = new Mensagem();
        this.message.setAction(Action.ALUNO_UPDATE);
        this.message.setAlunoID(idAluno);
        this.message.setAlunoNome(nome);
        this.message.setAlunoRA(RA);
        this.message.setAlunoEmail(email);
        this.message.setAlunoCurso(curso);
        this.message.setAlunoPeriodo(periodo);
        this.message.setAlunoTelefone(telefone);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
    }
    
    public void controlarEvento(String idPresencaEvento, String idEvento, String IdusuarioControle, int controle){
        this.message = new Mensagem();
        this.message.setAction(Action.PRESENCA_EVENTO_UPDATE);
        this.message.setControlepresencaEvento(Integer.parseInt(idPresencaEvento));
        this.message.setAlunoID(IdusuarioControle);
        this.message.setIdEvento(idEvento);     
        this.message.setControlepresencaEvento(controle);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
    }
            
            
    public void eventoList(int idUsuario) {
        this.message = new Mensagem();
        this.message.setAction(Action.EVENTO_LIST);
        this.message.setIdUsuario(idUsuario);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
        respostaServidor(this.socket);
    }
    
    public void getAlunoRA(String RA) {
        this.message = new Mensagem();
        this.message.setAction(Action.GET_ALUNO_RA);
        this.message.setAlunoRA(RA);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
        respostaServidor(this.socket);
    }
    
    public void listEventoPresenca() {
        this.message = new Mensagem();
        this.message.setAction(Action.LIST_EVENTO_PRESENCA);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
        respostaServidor(this.socket);
    }
    
     public void alunoList() {
        this.message = new Mensagem();
        this.message.setAction(Action.ALUNO_LIST);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
        respostaServidor(this.socket);
    }
      
    public void excluirEvento(String idEvento){
        this.message = new Mensagem();
        this.message.setAction(Action.EXCLUIR_EVENTO);
        this.message.setIdEvento(idEvento);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
        respostaServidor(this.socket);
    }
    
     public void excluirAluno(String idAluno){
        this.message = new Mensagem();
        this.message.setAction(Action.ALUNO_EXCLUIR);
        this.message.setAlunoID(idAluno);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
       // respostaServidor(this.socket);
    }
    
    private void conectar(Mensagem message) {
        LoginFrame loginFrame = new LoginFrame();
        if (message.getTexto().equals("200_1")) {
            System.out.println("esta em 200_1");
            System.out.println(message.getIdUsuario());
            JOptionPane.showMessageDialog(null, "Bem vindo ao sistema Administrador");
            loginFrame.setVisible(false);
            JFrameAdmin jFrameAdmin = new JFrameAdmin();
            jFrameAdmin.setVisible(true);
        } else if (message.getTexto().equals("200")) {
            JOptionPane.showMessageDialog(null, "Bem vindo ao sistema " + message.getNome() + " ID " + message.getIdUsuario());
            System.out.println(message.getIdUsuario());
            JFramePrincipal jJFramePrincipal = new JFramePrincipal(message.getNome(),message.getIdUsuario() );
            jJFramePrincipal.setVisible(true);
            loginFrame.setVisible(false);
        } else if (message.getTexto().equals("403")) {
            JOptionPane.showMessageDialog(null, "Verifique sua conexao, usuario e senha. Não foi possivel conectar ao servidor.");
            return;
        }
        fecharConexao();
    }

    public void resTipoEvento(Mensagem message) {
        JFrameEventocadastro jFrameCadastroEventoAdicionar = new JFrameEventocadastro();
        jFrameCadastroEventoAdicionar.preencherComboBox(message.getTipoEventoList());
        jFrameCadastroEventoAdicionar.setVisible(true);
        fecharConexao();
    }
    
    public  void resGetAlunoRA(Mensagem message){
       if(message.getResposta().equals("200")){
            this.dadosAluno[0] = message.getAlunoNome();
            this.dadosAluno[1] = message.getAlunoEmail();
            this.dadosAluno[2] = message.getAlunoCurso();
            this.dadosAluno[3] = message.getResposta();
        }else{
            this.dadosAluno[3] = message.getResposta();
        } 
    }
        
    public void resListEventoPresenca(Mensagem message){
        this.listaEventoPresenca =  message.getListEventoPresenca();
        fecharConexao();
    }
    
    public void resEventoList(Mensagem message){
        this.listEvento =  message.getListEvento();
        fecharConexao();
    }
     
    public void resAlunoList(Mensagem message){
        this.listAluno =  message.getListAluno();
        fecharConexao();
    }
    private void respostaServidor(Socket socket) {

        try {
            this.input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        Mensagem message = null;
        try {
            message = (Mensagem) input.readObject();
            Action action = message.getAction();
            if (action.equals(Mensagem.Action.CONNECT)) {
                conectar(message);
            } else if (action.equals(Mensagem.Action.DISCONNECT)) {
                socket.close();
            } else if (action.equals(Mensagem.Action.TIPO_EVENTO)) {
                resTipoEvento(message);
            } else if (action.equals(Mensagem.Action.EVENTO_LIST)) {
                resEventoList(message);
            } else if (action.equals(Mensagem.Action.ALUNO_CADASTRAR)) {
                //resAlunoCadastro(message);
            } else if (action.equals(Mensagem.Action.ALUNO_LIST)) {
                resAlunoList(message);
            }else if (action.equals(Mensagem.Action.ALUNO_EXCLUIR)) {
                resAlunoList(message);
            }else if (action.equals(Mensagem.Action.LIST_EVENTO_PRESENCA)) {
                resListEventoPresenca(message);
            }else if (action.equals(Mensagem.Action.GET_ALUNO_RA)) {
                resGetAlunoRA(message);
            }
        } catch (IOException ex) {
            System.out.println("erro " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("erro " + ex);
        }
    }

    private void fecharConexao() {
        try {
            System.out.println("fechou socket");
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ListenerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * @return the listAluno
     */
    public List<Map<String, String>> getListAluno() {
        return listAluno;
    }

    /**
     * @param listAluno the listAluno to set
     */
    public void setListAluno(List<Map<String, String>> listAluno) {
        this.listAluno = listAluno;
    }

  

}
