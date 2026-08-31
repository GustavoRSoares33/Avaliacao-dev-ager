package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.PeriodoDisponivel;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaAction extends Action{

	private List<AgendaVo> agendas = new ArrayList<>();
	private AgendaBusiness business = new AgendaBusiness();
	private AgendaVo agendaVo = new AgendaVo();
	
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
		if(agendaVo == null || agendaVo.getRowid() == null) {
			return REDIRECT;
		}
		
		business.deletarAgenda(agendaVo);
		return REDIRECT;
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
	
	public List<AgendaVo> getAgendas() {
		return agendas;
	}
	
	public void setAgendas(List<AgendaVo> agendas) {
		this.agendas = agendas;
	}
	
}
