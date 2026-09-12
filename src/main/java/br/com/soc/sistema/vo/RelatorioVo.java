package br.com.soc.sistema.vo;

public class RelatorioVo {

	private FuncionarioVo funcionario;
	private AgendaVo agenda;
	private CompromissoVo compromisso;
	
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
	
	@Override
	public String toString() {
		return "RelatorioVo [funcionario=" + funcionario + ", agenda=" + agenda + ", compromisso=" + compromisso + "]";
	}
	
}
