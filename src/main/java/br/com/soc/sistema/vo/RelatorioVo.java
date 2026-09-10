package br.com.soc.sistema.vo;

import java.sql.Date;

public class RelatorioVo {

	private FuncionarioVo funcionario;
	private AgendaVo agenda;
	private CompromissoVo compromisso;
	
	private Date dataInicio;
	private Date dataFim;
	
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
	
	public CompromissoVo getCompromisso() {
		return compromisso;
	}
	public void setCompromisso(CompromissoVo compromisso) {
		this.compromisso = compromisso;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	@Override
	public String toString() {
		return "RelatorioVo [funcionario=" + funcionario + ", agenda=" + agenda + ", compromisso=" + compromisso + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + "]";
	}
	
}
