package br.com.soc.sistema.vo;

import java.sql.Date;
import java.sql.Time;

public class CompromissoVo {

	private String rowid;
	private String nome;
	private FuncionarioVo funcionario;
	private AgendaVo agenda;
	private Date dataCompromisso;
	private Time horaCompromisso;
	
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public FuncionarioVo getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioVo funcionario) {
		this.funcionario = funcionario;
	}
	
	public AgendaVo getAgenda() {
		return agenda;
	}
	public void setAgenda(AgendaVo agenda) {
		this.agenda = agenda;
	}
	
	public Date getDataCompromisso() {
		return dataCompromisso;
	}
	public void setDataCompromisso(Date data_compromisso) {
		this.dataCompromisso = data_compromisso;
	}
	
	public Time getHoraCompromisso() {
		return horaCompromisso;
	}
	public void setHoraCompromisso(Time hora_compromisso) {
		this.horaCompromisso = hora_compromisso;
	}
	
	@Override
	public String toString() {
		return "CompromissoVo [rowid=" + rowid + ", nome=" + nome + ", funcionario=" + funcionario + ", dataCompromisso=" + dataCompromisso + ", horaCompromisso=" + horaCompromisso + "]";
	}
	
}
