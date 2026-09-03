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
	
	private String telaAtual;

	public String todos() {
			compromissos.addAll(business.trazerTodosCompromissos());
			return SUCCESS;
		}
	
	public String novo() {
		if("telaCrud".equals(telaAtual)) {
			if(compromissoVo.getNome() == null) {
				carregarListas();
				return INPUT;
			}
			
			try {
				if (compromissoVo.getAgenda() != null && 
						   (compromissoVo.getAgenda().getRowid() == null || 
						   		(compromissoVo.getAgenda().getRowid().trim().isEmpty()))) {
							
							compromissoVo.setAgenda(null);
						}
				limparCampos();
				converterDataEHora();
				business.novoCompromisso(compromissoVo);
				return REDIRECT;
				
			}catch(IllegalArgumentException e) {
				addActionError(e.getMessage());
				carregarListas();
				return INPUT;
			}
		}else if("telaAgenda".equals(telaAtual)) {
			String idDaAgenda = compromissoVo.getAgenda().getRowid();
			try {
				limparCampos();
				converterDataEHora();
				business.salvarCompromisso(compromissoVo);
				
				carregarAgenda(idDaAgenda);
				
				compromissoVo = new CompromissoVo();
				AgendaVo agenda = new AgendaVo();
				agenda.setRowid(idDaAgenda);
				compromissoVo.setAgenda(agenda);
				
				dataDigitada = null;
				horaDigitada = null;
				
				return "abrirAgenda";
				
			} catch (IllegalArgumentException e) {
				addActionError(e.getMessage());
				carregarAgenda(idDaAgenda);
				return "abrirAgenda";
			}
		}
		return INPUT;
	}
	
	public String editar() {
		if(compromissoVo == null || compromissoVo.getRowid() == null) {
			return REDIRECT;
		}
		
		try {
			compromissoVo = business.buscarCompromissoPor(compromissoVo.getRowid());
			carregarListas();
			
			if (compromissoVo.getDataCompromisso() != null) {
				dataDigitada = compromissoVo.getDataCompromisso().toString();
			}
			if (compromissoVo.getHoraCompromisso() != null) {
				horaDigitada = compromissoVo.getHoraCompromisso().toString().substring(0, 5);
			}
			
			return "editarCompromisso";
		}catch (IllegalArgumentException e) {
			addActionError(e.getMessage());
			return "editarCompromisso";
		}
		
	}
	
	public String alterar() {
		if(compromissoVo == null || compromissoVo.getRowid() == null) {
			return REDIRECT;
		}
		
		try {
			limparCampos();
			converterDataEHora();
			
			business.alterarCompromisso(compromissoVo);
			return REDIRECT;
		}catch (IllegalArgumentException e) {
			addActionError(e.getMessage());
			carregarListas();
			return "editarCompromisso";
		}
	}
	
	private void carregarListas() {
		AgendaBusiness agendaBusiness = new AgendaBusiness();
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		agendas.clear();
		funcionarios.clear();
		agendas.addAll(agendaBusiness.trazerTodasAsAgendas());
		funcionarios.addAll(funcionarioBusiness.trazerTodosOsFuncionarios());
	}
	
	private void carregarAgenda(String idAgenda) {
		AgendaBusiness agendaBusiness = new AgendaBusiness();
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		
		this.agendaVo = agendaBusiness.buscarAgendaPor(idAgenda);
		
		funcionarios.clear();
		funcionarios.addAll(funcionarioBusiness.trazerTodosOsFuncionarios());
		
		compromissos.clear();
		compromissos.addAll(business.buscarPorAgenda(idAgenda));
	}
	
	private void limparCampos() {
		if (compromissoVo.getAgenda() != null && 
		   (compromissoVo.getAgenda().getRowid() == null || compromissoVo.getAgenda().getRowid().trim().isEmpty())) {
			compromissoVo.setAgenda(null);
		}
		if (compromissoVo.getFuncionario() != null && 
		   (compromissoVo.getFuncionario().getRowid() == null || compromissoVo.getFuncionario().getRowid().trim().isEmpty())) {
			compromissoVo.setFuncionario(null);
		}
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
	
	public AgendaVo getAgendaVo() {
		return agendaVo;
	}
	public void setAgendaVo(AgendaVo agendaVo) {
		this.agendaVo = agendaVo;
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
	
	public String getTelaAtual() {
		return telaAtual;
	}
	public void setTelaAtual(String telaAtual) {
		this.telaAtual = telaAtual;
	}


}