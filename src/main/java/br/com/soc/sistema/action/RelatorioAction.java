package br.com.soc.sistema.action;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import br.com.soc.sistema.business.RelatorioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.GerarExcel;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioAction extends Action{

	RelatorioBusiness business = new RelatorioBusiness();
	List<RelatorioVo> relatorios;
	
	private String dataInicio;
	private String dataFim;
	
	private String formato;
	private InputStream arquivo;
	
	public String filtro() {
		return SUCCESS;
	}
	
	public String gerar() throws Exception {
		Date dtInicio = null;
		Date dtFim = null;
		
		try {
			if (dataInicio != null && !dataInicio.trim().isEmpty()) {
				dtInicio = Date.valueOf(dataInicio);
			}
			if (dataFim != null && !dataFim.trim().isEmpty()) {
				dtFim = Date.valueOf(dataFim);
			}
			
			relatorios = business.buscarRelatorio(dtInicio, dtFim);
			
			if ("excel".equals(formato)) {
				GerarExcel gerador = new GerarExcel();
				arquivo = gerador.exportarExcel(relatorios); 
				return "excel";
			}
			
			return "imprimir";
			
		} catch (IllegalArgumentException e) {
			addActionError("Data inválida. Por favor, verifique os campos.");
			return SUCCESS; 
		}
	}
	
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	public List<RelatorioVo> getRelatorios() {
		return relatorios;
	}
	
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	public InputStream getArquivo() {
		return arquivo;
	}
	public void setArquivo(InputStream arquivo) {
		this.arquivo = arquivo;
	}
	
}
