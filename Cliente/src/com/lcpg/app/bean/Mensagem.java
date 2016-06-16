package com.lcpg.app.bean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mensagem implements Serializable{
    private String nome;
    private String texto;
    private String nomeReservado;
    private String senha;
    private String nomeEvento;
    private String dataEvento;
    private String horaInicioEvento;
    private String horaFimEvento;
    private int    tipoEvento;
    private String idEvento;
    private String resposta;
    private int    idUsuario;
    private int    controlepresencaEvento;
    private int    idPresencaEvento;
    private String alunoNome;
    private String alunoID;
    private String alunoTelefone;
    private String alunoCurso;
    private String alunoEmail;
    private String alunoPeriodo;
    private String alunoRA;
    private HashMap<String, String> tipoEventoList = new HashMap<String, String>();
    private List<Map<String, String>> listAluno = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> listEventoPresenca = new ArrayList<Map<String, String>>();
    private List<Map<String, String>> listEventoPresencaAluno = new ArrayList<Map<String,String>>();
    private List<Map<String, String>> listEvento = new ArrayList<Map<String, String>>();
    private Set<String> setOnline = new HashSet<String>();

    //List<String> suaColecao;
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
     * @return the tipoEventoList
     */
    public HashMap<String, String> getTipoEventoList() {
        return tipoEventoList;
    }

    /**
     * @param tipoEventoList the tipoEventoList to set
     */
    public void setTipoEventoList(HashMap<String, String> tipoEventoList) {
        this.tipoEventoList = tipoEventoList;
    }

       /**
     * @return the listEvento
     */
    public List<Map<String, String>> getListEvento() {
        return listEvento;
    }

    /**
     * @param listEvento the listEvento to set
     */
    public void setListEvento(List<Map<String, String>> listEvento) {
        this.listEvento = listEvento;
    }

    /**
     * @return the idEvento
     */
    public String getIdEvento() {
        return idEvento;
    }

    /**
     * @param idEvento the idEvento to set
     */
    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * @return the resposta
     */
    public String getResposta() {
        return resposta;
    }

    /**
     * @param resposta the resposta to set
     */
    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    /**
     * @return the alunoNome
     */
    public String getAlunoNome() {
        return alunoNome;
    }

    /**
     * @param alunoNome the alunoNome to set
     */
    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    /**
     * @return the alunoID
     */
    public String getAlunoID() {
        return alunoID;
    }

    /**
     * @param alunoID the alunoID to set
     */
    public void setAlunoID(String alunoID) {
        this.alunoID = alunoID;
    }

    /**
     * @return the alunoTelefone
     */
    public String getAlunoTelefone() {
        return alunoTelefone;
    }

    /**
     * @param alunoTelefone the alunoTelefone to set
     */
    public void setAlunoTelefone(String alunoTelefone) {
        this.alunoTelefone = alunoTelefone;
    }

    /**
     * @return the alunoCurso
     */
    public String getAlunoCurso() {
        return alunoCurso;
    }

    /**
     * @param alunoCurso the alunoCurso to set
     */
    public void setAlunoCurso(String alunoCurso) {
        this.alunoCurso = alunoCurso;
    }

    /**
     * @return the alunoEmail
     */
    public String getAlunoEmail() {
        return alunoEmail;
    }

    /**
     * @param alunoEmail the alunoEmail to set
     */
    public void setAlunoEmail(String alunoEmail) {
        this.alunoEmail = alunoEmail;
    }

    /**
     * @return the alunoPeriodo
     */
    public String getAlunoPeriodo() {
        return alunoPeriodo;
    }

    /**
     * @param alunoPeriodo the alunoPeriodo to set
     */
    public void setAlunoPeriodo(String alunoPeriodo) {
        this.alunoPeriodo = alunoPeriodo;
    }

    /**
     * @return the alunoRA
     */
    public String getAlunoRA() {
        return alunoRA;
    }

    /**
     * @param alunoRA the alunoRA to set
     */
    public void setAlunoRA(String alunoRA) {
        this.alunoRA = alunoRA;
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

    /**
     * @return the listEventoPresenca
     */
    public List<Map<String, String>> getListEventoPresenca() {
        return listEventoPresenca;
    }

    /**
     * @param listEventoPresenca the listEventoPresenca to set
     */
    public void setListEventoPresenca(List<Map<String, String>> listEventoPresenca) {
        this.listEventoPresenca = listEventoPresenca;
    }

    /**
     * @return the controlepresencaEvento
     */
    public int getControlepresencaEvento() {
        return controlepresencaEvento;
    }

    /**
     * @param controlepresencaEvento the controlepresencaEvento to set
     */
    public void setControlepresencaEvento(int controlepresencaEvento) {
        this.controlepresencaEvento = controlepresencaEvento;
    }

    /**
     * @return the idPresencaEvento
     */
    public int getIdPresencaEvento() {
        return idPresencaEvento;
    }

    /**
     * @param idPresencaEvento the idPresencaEvento to set
     */
    public void setIdPresencaEvento(int idPresencaEvento) {
        this.idPresencaEvento = idPresencaEvento;
    }

    /**
     * @return the listEventoPresencaAluno
     */
    public List<Map<String, String>> getListEventoPresencaAluno() {
        return listEventoPresencaAluno;
    }

    /**
     * @param listEventoPresencaAluno the listEventoPresencaAluno to set
     */
    public void setListEventoPresencaAluno(List<Map<String, String>> listEventoPresencaAluno) {
        this.listEventoPresencaAluno = listEventoPresencaAluno;
    }

    public enum Action{
        CONNECT, DISCONNECT, SEND_ONE, SEND_ALL, USERS_ONLINE, CADASTRO_EVENTO, TIPO_EVENTO, EVENTO_LIST, EXCLUIR_EVENTO, ALUNO_LIST, ALUNO_CADASTRAR, ALUNO_EXCLUIR, ALUNO_UPDATE, LIST_EVENTO_PRESENCA,PRESENCA_EVENTO_UPDATE, GET_ALUNO_RA, GET_PRESENCA_ALUNO_RA
    }
            
}
