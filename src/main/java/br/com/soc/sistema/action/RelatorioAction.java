package br.com.soc.sistema.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.RelatorioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioAction extends Action{

	RelatorioBusiness business = new RelatorioBusiness();
	List<RelatorioVo> relatorios;
	
	private Date dataInicio;
	private Date dataFim;
	
	public String gerar() {
		relatorios = business.buscarRelatorio(dataInicio, dataFim);
		return SUCCESS;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public List<RelatorioVo> getRelatorios() {
		return relatorios;
	}
	
}
