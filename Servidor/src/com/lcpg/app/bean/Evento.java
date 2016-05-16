
package com.lcpg.app.bean;

import java.io.Serializable;

public class Evento  implements Serializable{
    private String nome;
    private String data;
    private String horaIni;
    private String HoraFim;
    private int    tempo;
    private int    usuario;

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
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the horaIni
     */
    public String getHoraIni() {
        return horaIni;
    }

    /**
     * @param horaIni the horaIni to set
     */
    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * @return the HoraFim
     */
    public String getHoraFim() {
        return HoraFim;
    }

    /**
     * @param HoraFim the HoraFim to set
     */
    public void setHoraFim(String HoraFim) {
        this.HoraFim = HoraFim;
    }

    /**
     * @return the tempo
     */
    public int getTempo() {
        return tempo;
    }

    /**
     * @param tempo the tempo to set
     */
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    /**
     * @return the usuario
     */
    public int getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
}
