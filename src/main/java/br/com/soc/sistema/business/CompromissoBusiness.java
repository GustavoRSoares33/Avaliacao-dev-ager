package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;

public class CompromissoBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private CompromissoDao dao;
	private AgendaBusiness agendaBusiness = new AgendaBusiness();
	
	public CompromissoBusiness() {
		this.dao = new CompromissoDao();
	}
	
	public List<CompromissoVo> trazerTodosCompromissos(){
		return dao.findAllCompromissos();
	}
	
	public void novoCompromisso(CompromissoVo compromisso) {
		if(compromisso.getNome() == null || compromisso.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome nao pode ser em branco");
		}
		
		validarDisponibilidadeAgenda(compromisso);
		
		try {
			dao.insertCompromisso(compromisso);
		}catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do compromisso");
		}
	}
	
	public void alterarCompromisso(CompromissoVo compromisso) {
		if(compromisso.getNome() == null || compromisso.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome nao pode ser em branco");
		}
		
		try {
			dao.updateCompromisso(compromisso);
		}catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do compromisso");
		}
	}
	
	public CompromissoVo buscarCompromissoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public List<CompromissoVo> buscarPorAgenda(String idAgenda) {
		if (idAgenda == null || idAgenda.trim().isEmpty()) {
			throw new IllegalArgumentException("O código da agenda não pode ser nulo para realizar a busca.");
		}
		
		return dao.buscarPorAgenda(idAgenda);
	}
	
	public void excluirPorFuncionario(String idFuncionario) {
		if (idFuncionario == null || idFuncionario.trim().isEmpty()) {
			throw new IllegalArgumentException("O código do funcionário não pode ser nulo.");
		}
		dao.excluirSeNaoExistirOFuncionario(idFuncionario);
	}
	
	public void validarDisponibilidadeAgenda(CompromissoVo compromisso) {
		if (compromisso.getAgenda() != null && compromisso.getAgenda().getRowid() != null
				&& compromisso.getHoraCompromisso() != null) {

			AgendaVo agenda = agendaBusiness.buscarAgendaPor(compromisso.getAgenda().getRowid());

			if (agenda != null && agenda.getPeriodoDisponivel() != null) {
				
				int horaAgendada = compromisso.getHoraCompromisso().toLocalTime().getHour();
				
				String codigoPeriodo = agenda.getPeriodoDisponivel().getCodigo();

				if ("1".equals(codigoPeriodo) && horaAgendada >= 12) {
					throw new IllegalArgumentException("A agenda selecionada está disponível apenas no período da Manhã (antes das 12:00).");
				} 
				else if ("2".equals(codigoPeriodo) && horaAgendada < 12) {
					throw new IllegalArgumentException("A agenda selecionada está disponível apenas no período da Tarde (a partir das 12:00).");
				}
			}
		}
	}
	
	public void salvarCompromisso(CompromissoVo compromisso) {
		if(compromisso.getNome() == null || compromisso.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome nao pode ser em branco");
		}
		
		validarDisponibilidadeAgenda(compromisso);
		
		if (compromisso.getRowid() == null || compromisso.getRowid().isEmpty()) {
			dao.insertCompromisso(compromisso);
		} else {
			dao.updateCompromisso(compromisso);
		}
	}
}
