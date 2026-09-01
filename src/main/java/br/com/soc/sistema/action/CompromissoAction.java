package br.com.soc.sistema.action;

import java.util.ArrayList;

import java.util.List;



import br.com.soc.sistema.business.AgendaBusiness;

import br.com.soc.sistema.business.CompromissoBusiness;

import br.com.soc.sistema.business.FuncionarioBusiness;

import br.com.soc.sistema.infra.Action;

import br.com.soc.sistema.vo.AgendaVo;

import br.com.soc.sistema.vo.CompromissoVo;

import br.com.soc.sistema.vo.FuncionarioVo;

public class CompromissoAction extends Action{

	private List<CompromissoVo> compromissos = new ArrayList<>();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private List<AgendaVo> agendas = new ArrayList<>();
	private CompromissoBusiness business = new CompromissoBusiness();
	private CompromissoVo compromissoVo = new CompromissoVo();
	private AgendaVo agendaVo = new AgendaVo();
	
	private String dataDigitada;
	private String horaDigitada;

	public String todos() {
			compromissos.addAll(business.trazerTodosCompromissos());
			return SUCCESS;
		}
	
	public String novo() {
		if(compromissoVo.getNome() == null || compromissoVo.getNome().trim().isEmpty()) {
			carregarListas();
			return INPUT;
		}
		
		try {
			if (compromissoVo.getAgenda() != null && 
					   (compromissoVo.getAgenda().getRowid() == null || compromissoVo.getAgenda().getRowid().trim().isEmpty())) {
						
						compromissoVo.setAgenda(null);
					}
			
			converterDataEHora();
			business.novoCompromisso(compromissoVo);
			return REDIRECT;
		}catch(IllegalArgumentException e) {
			addActionError(e.getMessage());
			carregarListas();
			return INPUT;
		}
		
	}
	
	public String gravarContexto() {
		try {
			business.salvarCompromisso(compromissoVo);
			return SUCCESS;
		
		} catch (IllegalArgumentException e) {
		
			addActionError(e.getMessage());
			
			AgendaBusiness agendaBusiness = new AgendaBusiness();
			FuncionarioBusiness funcBusiness = new FuncionarioBusiness();
			
			agendaVo.setNome(agendaBusiness.buscarAgendaPor(compromissoVo.getAgenda().getRowid()).getNome());
			funcionarios.addAll(funcBusiness.trazerTodosOsFuncionarios());
			
			setCompromissos(business.buscarPorAgenda(compromissoVo.getAgenda().getRowid()));
		
			return "detalhes";
		}
	}
	
	private void carregarListas() {
		AgendaBusiness agendaBusiness = new AgendaBusiness();
		FuncionarioBusiness funcBusiness = new FuncionarioBusiness();
		agendas.clear();
		funcionarios.clear();
		agendas.addAll(agendaBusiness.trazerTodasAsAgendas());
		funcionarios.addAll(funcBusiness.trazerTodosOsFuncionarios());
	}

	private void converterDataEHora() {
		if (dataDigitada != null && !dataDigitada.trim().isEmpty()) {
			compromissoVo.setDataCompromisso(java.sql.Date.valueOf(dataDigitada));
		}
		if (horaDigitada != null && !horaDigitada.trim().isEmpty()) {
			compromissoVo.setHoraCompromisso(java.sql.Time.valueOf(horaDigitada + ":00"));
		}
	}

	public List<CompromissoVo> getCompromissos() {
		return compromissos;
	}
	public void setCompromissos(List<CompromissoVo> compromissos) {
		this.compromissos = compromissos;
	}
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<AgendaVo> getAgendas() {
		return agendas;
	}
	public void setAgendas(List<AgendaVo> agendas) {
		this.agendas = agendas;
	}
	
	public CompromissoVo getCompromissoVo() {
		return compromissoVo;
	}
	public void setCompromissoVo(CompromissoVo compromissoVo) {
		this.compromissoVo = compromissoVo;
	}
	
	public String getDataDigitada() {
		return dataDigitada;
	}
	public void setDataDigitada(String dataDigitada) {
		this.dataDigitada = dataDigitada;
	}
	
	public String getHoraDigitada() {
		return horaDigitada;
	}
	public void setHoraDigitada(String horaDigitada) {
		this.horaDigitada = horaDigitada;
	}


}