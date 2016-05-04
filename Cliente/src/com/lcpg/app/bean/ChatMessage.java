package com.lcpg.app.bean;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ChatMessage implements Serializable{
    private String nome;
    private String texto;
    private String nomeReservado;
    private String senha;
    private Set<String> setOnline = new HashSet<String>();
    private Action action;

    public String getNome() {
        return nome;
    }

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
    
    public enum Action{
        CONNECT, DISCONNECT, SEND_ONE, SEND_ALL, USERS_ONLINE
    }
            
}
