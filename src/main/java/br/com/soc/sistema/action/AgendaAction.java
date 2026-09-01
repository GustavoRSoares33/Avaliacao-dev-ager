package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.AgendaFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscar;
import br.com.soc.sistema.infra.PeriodoDisponivel;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class AgendaAction extends Action{

	private List<AgendaVo> agendas = new ArrayList<>();
	private List<CompromissoVo> compromissos = new ArrayList<>();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	
	private AgendaBusiness business = new AgendaBusiness();
	private CompromissoBusiness compromissoBusiness = new CompromissoBusiness();
    private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
    
	private AgendaVo agendaVo = new AgendaVo();
	private AgendaFilter filtrar = new AgendaFilter();
	
	public String todas() {
		agendas.addAll(business.trazerTodasAsAgendas());
		
		return SUCCESS;
	}
	
	public String novo() {
	    if(agendaVo.getNome() == null && agendaVo.getPeriodoDisponivel() == null) {
	        return INPUT;
	    }
	    
	    try {
		    business.salvarAgenda(agendaVo);
		    return REDIRECT;
	    }catch (IllegalArgumentException e) {
			addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String editar() {
		if(agendaVo == null || agendaVo.getRowid() == null) {
			return REDIRECT;
		}
		
		try {
			agendaVo = business.buscarAgendaPor(agendaVo.getRowid());
			return "editarAgenda";
		}catch (IllegalArgumentException e) {
			addActionError(e.getMessage());
			return "editarAgenda";
		}
		
	}
	
	public String alterar() {
		if(agendaVo == null || agendaVo.getRowid() == null) {
			return REDIRECT;
		}
		
		try {
			business.editarAgenda(agendaVo);
			return REDIRECT;
		}catch (IllegalArgumentException e) {
			addActionError(e.getMessage());
			return "editarAgenda";
		}
		
	}
	
	public String excluir() {
		List<CompromissoVo> vinculados = compromissoBusiness.buscarPorAgenda(agendaVo.getRowid());
		
		if(agendaVo == null || agendaVo.getRowid() == null) {
			return REDIRECT;
		}
		
		if(vinculados != null && !vinculados.isEmpty()) {
			addActionError("Não é possível excluir esta agenda, pois ela possui compromissos cadastrados.");
			
			agendas.clear();
			agendas.addAll(business.trazerTodasAsAgendas());
			return SUCCESS;
		}
		
		business.deletarAgenda(agendaVo);
		return REDIRECT;
	}

	public String filtrar() {
		if(filtrar.isNullOpcoesCombo()) {
			return REDIRECT;
		}
		
		try {
			agendas = business.filtrarAgendas(filtrar);
		}catch (Exception e) {
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}
	
	public String detalhes() {
        if (agendaVo != null && agendaVo.getRowid() != null) {
            
            agendaVo = business.buscarAgendaPor(agendaVo.getRowid());
            
            funcionarios = funcionarioBusiness.trazerTodosOsFuncionarios();
            
            compromissos = compromissoBusiness.buscarPorAgenda(agendaVo.getRowid());
        }
        
        return "detalhes"; 
    }
	
	public PeriodoDisponivel[] getListaPeriodos() {
	    return PeriodoDisponivel.values();
	}
	
	public AgendaVo getAgendaVo() {
		return agendaVo;
	}

	public void setAgendaVo(AgendaVo agendaVo) {
		this.agendaVo = agendaVo;
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
	
	public List<OpcoesComboBuscar> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscar.values());
	}
	
	public AgendaFilter getFiltrar() {
		return filtrar;
	}
	
	public void setFiltrar(AgendaFilter filtrar) {
		this.filtrar = filtrar;
	}
}
