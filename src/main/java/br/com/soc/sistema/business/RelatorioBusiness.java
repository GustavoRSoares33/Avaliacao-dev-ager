package br.com.soc.sistema.business;

import java.sql.Date;
import java.util.List;

import br.com.soc.sistema.dao.RelatorioDao;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioBusiness {

	private RelatorioDao dao;
	
	public RelatorioBusiness() {
		this.dao = new RelatorioDao();
	}
	
	public List<RelatorioVo> buscarRelatorio(Date dataInicio, Date dataFim){
		return dao.buscarPorPeriodo(dataInicio, dataFim);
	}
	
}
