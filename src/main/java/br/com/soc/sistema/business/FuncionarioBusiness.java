package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	
	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
	}
	
	public List<FuncionarioVo> trazerTodosOsFuncionarios(){
		return dao.findAllFuncionarios();
	}	
	
	public void salvarFuncionario(FuncionarioVo funcionarioVo) {
		if(funcionarioVo.getNome() == null || funcionarioVo.getNome().trim().isEmpty())
			throw new IllegalArgumentException("Nome nao pode ser em branco");
		
		try {
			dao.insertFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public void alterarFuncionario(FuncionarioVo funcionarioVo) {
		
		if(funcionarioVo.getNome() == null || funcionarioVo.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Nome nao pode ser em branco");
		}
		
		try {
			dao.updateFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a alteração do registro");
		}
		
	}
	
	public void deletarFuncionario(FuncionarioVo funcionarioVo) {
		
			try {
				dao.deleteFuncionario(funcionarioVo);
			} catch (Exception e) {
				throw new BusinessException("Nao foi possivel deletar o funcionário do registro");
			}
			
		}	
		
	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter){
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					FuncionarioVo vo = dao.findByCodigo(codigo);
					
					if(vo != null) {
						funcionarios.add(vo);
					}
					
				}catch (NumberFormatException e) {
					throw new BusinessException("ID não encontrado!");
				}
			break;

			case NOME:
				if(filter.getValorBusca().isEmpty()) {
					throw new BusinessException("Nome não encontrado!");
				}
				funcionarios.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return funcionarios;
	}
	
	public FuncionarioVo buscarFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
}
