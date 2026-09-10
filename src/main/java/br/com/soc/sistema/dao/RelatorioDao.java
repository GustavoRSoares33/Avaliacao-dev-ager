package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.exception.TechnicalException;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;
import br.com.soc.sistema.vo.FuncionarioVo;
import br.com.soc.sistema.vo.RelatorioVo;

public class RelatorioDao extends Dao{
	
	public List<RelatorioVo> buscarPorPeriodo(Date dataInicio, Date dataFim) {
		StringBuilder query = new StringBuilder("SELECT ")
										.append("	f.rowid AS id_funcionario, ")
										.append("	f.nm_funcionario AS nome_funcionario, ")
										.append("	a.rowid AS id_agenda, ")
										.append("	a.nm_agenda AS nome_agenda, ")
										.append("	c.data_compromisso, ")
										.append("	c.hora_compromisso ")
										.append("FROM compromissos c ")
										.append("LEFT JOIN funcionario f ON c.rowid_funcionario = f.rowid ")
										.append("LEFT JOIN agenda a ON c.rowid_agenda = a.rowid ")
										.append("WHERE c.data_compromisso BETWEEN ? AND ? ")
										.append("ORDER BY c.data_compromisso, c.hora_compromisso");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setDate(i++, dataInicio);
			ps.setDate(i++, dataFim);
			
			try(ResultSet rs = ps.executeQuery()){
				List<RelatorioVo> relatorios = new ArrayList<>();
				
				while(rs.next()) {
					RelatorioVo vo = new RelatorioVo();
					
					FuncionarioVo funcionarioVo = new FuncionarioVo(); 
					funcionarioVo.setRowid(rs.getString("id_funcionario"));
					funcionarioVo.setNome(rs.getString("nome_funcionario"));
					
					AgendaVo agendaVo = new AgendaVo();
					agendaVo.setRowid(rs.getString("id_agenda"));
					agendaVo.setNome(rs.getString("nome_agenda"));
					
					CompromissoVo compromissoVo = new CompromissoVo();
					compromissoVo.setDataCompromisso(rs.getDate("data_compromisso"));
					compromissoVo.setHoraCompromisso(rs.getTime("hora_compromisso"));
					
					vo.setFuncionario(funcionarioVo);
					vo.setAgenda(agendaVo);
					vo.setCompromisso(compromissoVo);
					
					relatorios.add(vo);
				}
				return relatorios;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
	        throw new TechnicalException("Erro ao buscar relatório!", e);
		}
		
	}

}
