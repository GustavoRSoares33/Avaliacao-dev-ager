package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {

	private AgendaDao dao;
	
	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}
	
	public List<AgendaVo> trazerTodasAsAgendas(){
		return dao.findAllAgenda();
	}
	
	public void salvarAgenda(AgendaVo agendaVo) {
		
		if(agendaVo.getNome().isEmpty()) {
			throw new IllegalArgumentException("O nome da agenda não pode ser em branco!");
		}
		
		try {
			dao.insertAgenda(agendaVo);
		}catch (Exception e) {
			throw new BusinessException("Não foi possível inserir nova agenda!");
		}
		
	}
	
}
