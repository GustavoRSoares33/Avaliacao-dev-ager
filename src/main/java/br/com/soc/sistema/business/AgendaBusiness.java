package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado caracter no lugar de um numero";
	private AgendaDao dao;
	
	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}
	
	public List<AgendaVo> trazerTodasAsAgendas(){
		return dao.findAllAgenda();
	}
	
	public void salvarAgenda(AgendaVo agendaVo) {
		
		if(agendaVo.getNome() == null || agendaVo.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da agenda não pode ser em branco!");
		}
		
		if(agendaVo.getPeriodoDisponivel() == null) {
			throw new IllegalArgumentException("Por favor selecione um período para essa agenda!");
		}
		
		try {
			dao.insertAgenda(agendaVo);
		}catch (Exception e) {
			throw new BusinessException("Não foi possível inserir nova agenda!");
		}
		
	}
	
	public void editarAgenda(AgendaVo agendaVo) {
		
		if(agendaVo == null) {
			throw new IllegalArgumentException("Os dados da agenda não podem ser nulos!");
		}
		
		if(agendaVo.getNome() == null || agendaVo.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da agenda não pode ser em branco!");
		}
		
		if(agendaVo.getPeriodoDisponivel() == null) {
			throw new IllegalArgumentException("Por favor selecione um período para essa agenda!");
		}
		
		try {
			dao.updateAgenda(agendaVo);
		}catch (Exception e) {
			throw new BusinessException("Não foi possível editar a agenda!");
		}
	}
	
	public void deletarAgenda(AgendaVo agendaVo) {
		try {
			dao.deleteAgenda(agendaVo);
		}catch (Exception e) {
			throw new BusinessException("Não foi possível deletar a agenda!");
		}
	}
	
	public AgendaVo buscarAgendaPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (Exception e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
}
