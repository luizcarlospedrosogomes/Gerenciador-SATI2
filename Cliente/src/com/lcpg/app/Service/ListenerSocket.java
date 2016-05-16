/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.Service;

import com.lcpg.app.bean.Mensagem;
import com.lcpg.app.bean.Mensagem.Action;
import com.lcpg.app.frame.JFrameAdmin;
import com.lcpg.app.frame.JFrameCadastroEvento;
import com.lcpg.app.frame.JFrameCadastroEventoAdicionar;
import com.lcpg.app.frame.LoginFrame;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<Map<String, String>> listEvento;// = new ArrayList<Map<String, String>>();
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

    public void cadastroEvento(String nomeEvento, String horaIni, String horaFim, int tipoEvento, String dataEvento) {
        this.message = new Mensagem();
        this.message.setAction(Action.CADASTRO_EVENTO);
        this.message.setNomeEvento(nomeEvento);
        this.message.setDataEvento(dataEvento);
        this.message.setHoraInicioEvento(horaIni);
        this.message.setHoraFimEvento(horaFim);
        this.message.setTipoEvento(tipoEvento);
        this.message.setIdUsuario(3);
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
    
    public void excluirEvento(String idEvento){
        this.message = new Mensagem();
        this.message.setAction(Action.EXCLUIR_EVENTO);
        this.message.setIdEvento(idEvento);
        this.service = new ClienteService(this.conexao.getIP(), this.conexao.getPorta());
        this.socket = this.service.connect();
        this.service.send(this.message);
        respostaServidor(this.socket);
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
            JFrameCadastroEvento jFrameCadastroEvento = new JFrameCadastroEvento(message.getIdUsuario());
            jFrameCadastroEvento.setVisible(true);
            loginFrame.setVisible(false);
        } else if (message.getTexto().equals("403")) {
            JOptionPane.showMessageDialog(null, "Verifique sua conexao, usuario e senha. Não foi possivel conectar ao servidor.");
            return;
        }
        fecharConexao();
    }

    public void resTipoEvento(Mensagem message) {
        System.out.println(message.getTipoEventoList().values());
        System.out.println(message.getTipoEventoList().keySet());
        JFrameCadastroEventoAdicionar jFrameCadastroEventoAdicionar = new JFrameCadastroEventoAdicionar();
        jFrameCadastroEventoAdicionar.preencherComboBox(message.getTipoEventoList());
        jFrameCadastroEventoAdicionar.setVisible(true);
        fecharConexao();
    }

    public void resEventoList(Mensagem message){
        this.listEvento =  message.getListEvento();
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
                // disconnect();
                socket.close();
            } else if (action.equals(Mensagem.Action.TIPO_EVENTO)) {
                resTipoEvento(message);
            } else if (action.equals(Mensagem.Action.EVENTO_LIST)) {
                resEventoList(message);
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

}
