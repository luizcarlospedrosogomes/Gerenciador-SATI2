/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcpg.app.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author usuario
 */
public class Mensagem implements Serializable{
    private String nome;
    private String texto;
    private String senha;
    private String nomeReservado;
    private Set<String> setOnline = new HashSet<String>();
    private Action action;
    private String nomeEvento;
    private String dataEvento;
    private String horaInicioEvento;
    private String horaFimEvento;
    private int    tipoEvento;
    private int    idUsuario;
    private ResultSet result;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the nomeReservado
     */
    public String getNomeReservado() {
        return nomeReservado;
    }

    /**
     * @param nomeReservado the nomeReservado to set
     */
    public void setNomeReservado(String nomeReservado) {
        this.nomeReservado = nomeReservado;
    }

    /**
     * @return the setOnline
     */
    public Set<String> getSetOnline() {
        return setOnline;
    }

    /**
     * @param setOnline the setOnline to set
     */
    public void setSetOnline(Set<String> setOnline) {
        this.setOnline = setOnline;
    }

    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
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
     * @return the nomeEvento
     */
    public String getNomeEvento() {
        return nomeEvento;
    }

    /**
     * @param nomeEvento the nomeEvento to set
     */
    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    /**
     * @return the dataEvento
     */
    public String getDataEvento() {
        return dataEvento;
    }

    /**
     * @param dataEvento the dataEvento to set
     */
    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    /**
     * @return the horaInicioEvento
     */
    public String getHoraInicioEvento() {
        return horaInicioEvento;
    }

    /**
     * @param horaInicioEvento the horaInicioEvento to set
     */
    public void setHoraInicioEvento(String horaInicioEvento) {
        this.horaInicioEvento = horaInicioEvento;
    }

    /**
     * @return the horaFimEvento
     */
    public String getHoraFimEvento() {
        return horaFimEvento;
    }

    /**
     * @param horaFimEvento the horaFimEvento to set
     */
    public void setHoraFimEvento(String horaFimEvento) {
        this.horaFimEvento = horaFimEvento;
    }

    /**
     * @return the tipoEvento
     */
    public int getTipoEvento() {
        return tipoEvento;
    }

    /**
     * @param tipoEvento the tipoEvento to set
     */
    public void setTipoEvento(int tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the result
     */
    public ResultSet getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(ResultSet result) {
        this.result = result;
    }
    
    public enum Action{
        CONNECT, DISCONNECT, SEND_ONE, SEND_ALL, USERS_ONLINE, CADASTRO_EVENTO, TIPO_EVENTO
    }
            
}
