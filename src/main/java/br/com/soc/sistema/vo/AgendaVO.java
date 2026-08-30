package br.com.soc.sistema.vo;

import br.com.soc.sistema.infra.PeriodoDisponivel;

public class AgendaVO {

	private String rowid;
	private String nome;
	
	private PeriodoDisponivel periodoDisponivel;
	
	public AgendaVO() {}
	
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
	
	public PeriodoDisponivel getPeriodoDisponivel() {
		return periodoDisponivel;
	}
	public void setPeriodoDisponivel(PeriodoDisponivel periodoDisponivel) {
		this.periodoDisponivel = periodoDisponivel;
	}
	
	@Override
	public String toString() {
		return "AgendaVO [rowid=" + rowid + ", nome=" + nome + "periodoDisponivel=" + periodoDisponivel + "]";
	}
	
}
