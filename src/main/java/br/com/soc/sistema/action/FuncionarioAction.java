package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscar;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action {
	
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	
	public String todos() {
		funcionarios.addAll(business.trazerTodosOsFuncionarios());	

		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		try {
			funcionarios = business.filtrarFuncionarios(filtrar);
		}catch (Exception e) {
			addActionError(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	public String novo() {
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		try {
			business.salvarFuncionario(funcionarioVo);
			
			return REDIRECT;
		}catch (IllegalArgumentException e) {
			addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String editar() {
	    if(funcionarioVo == null || funcionarioVo.getRowid() == null) {
	        return REDIRECT;
	    }
	    
	    try {
		    funcionarioVo = business.buscarFuncionarioPor(funcionarioVo.getRowid());
		    
		    return "editarFuncionario";
	    }catch (IllegalArgumentException e) {
	    	addActionError(e.getMessage());
			return "editarFuncionario";
		}
	}

	public String alterar() {
	    if(funcionarioVo == null || funcionarioVo.getRowid() == null) {
	        return REDIRECT;
	    }
	    
	    try {
		    business.alterarFuncionario(funcionarioVo);
		    
		    return REDIRECT;
	    }catch (IllegalArgumentException e) {
			addActionError(e.getMessage());
			return "editarFuncionario";
		}
	}
	
	public String excluir() {
		
		if(funcionarioVo == null || funcionarioVo.getRowid() == null) {
			return REDIRECT;
		}
		
		business.deletarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	public List<OpcoesComboBuscar> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscar.ID, OpcoesComboBuscar.NOME);
	}
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}
}
