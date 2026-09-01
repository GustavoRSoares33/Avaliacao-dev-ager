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
	private CompromissoBusiness business = new CompromissoBusiness();
	private CompromissoVo compromissoVo = new CompromissoVo();
	private AgendaVo agendaVo = new AgendaVo();


	public String todos() {

			compromissos.addAll(business.trazerTodosCompromissos());
		
			return SUCCESS;
	
		}
	
	
	public String gravarContexto() {
	
	try {
	
	business.salvarCompromisso(compromissoVo);
	
	
	// Se der tudo certo, redireciona (SUCCESS configurado no struts.xml)
	
	return SUCCESS;
	
	
	} catch (IllegalArgumentException e) {
	
	// Se cair na regra de bloqueio de horário, exibe a mensagem elegante
	
	addActionError(e.getMessage());
	
	
	// Para a tela abrirAgenda_i.jsp não ficar em branco ao retornar o erro,
	
	// precisamos recarregar os dados do contexto (igual fizemos no método detalhes)
	
	AgendaBusiness agendaBusiness = new AgendaBusiness();
	
	FuncionarioBusiness funcBusiness = new FuncionarioBusiness();
	
	
	// Aqui simulamos a mesma carga do detalhes()
	
	agendaVo.setNome(agendaBusiness.buscarAgendaPor(compromissoVo.getAgenda().getRowid()).getNome());
	
	funcionarios.addAll(funcBusiness.trazerTodosOsFuncionarios());
	
	setCompromissos(business.buscarPorAgenda(compromissoVo.getAgenda().getRowid()));
	
	
	// Retorna para a tela de input mostrando o erro
	
	return "detalhes";
	
	}

}



public List<CompromissoVo> getCompromissos() {

return compromissos;

}

public void setCompromissos(List<CompromissoVo> compromissos) {

this.compromissos = compromissos;

}


public CompromissoVo getCompromissoVo() {

return compromissoVo;

}

public void setCompromissoVo(CompromissoVo compromissoVo) {

this.compromissoVo = compromissoVo;

}


}