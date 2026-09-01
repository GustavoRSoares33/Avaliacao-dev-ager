package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.exception.TechnicalException;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class CompromissoDao extends Dao{
	
	public void insertCompromisso(CompromissoVo compromisso) {
		
		StringBuilder query = new StringBuilder("INSERT INTO compromissos (nm_compromisso, rowid_funcionario, rowid_agenda, data_compromisso, hora_compromisso) VALUES (?, ?, ?, ?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setString(i++, compromisso.getNome());
			
			if(compromisso.getFuncionario() != null && compromisso.getFuncionario().getRowid() != null && !compromisso.getFuncionario().getRowid().trim().isEmpty()) {
				ps.setString(i++, compromisso.getFuncionario().getRowid());
			}else {
				ps.setNull(i++, java.sql.Types.VARCHAR);
			}
			
			if(compromisso.getAgenda() != null && compromisso.getAgenda().getRowid() != null && !compromisso.getAgenda().getRowid().trim().isEmpty()) {
				ps.setString(i++, compromisso.getAgenda().getRowid());
			}else {
				ps.setNull(i++, java.sql.Types.VARCHAR);
			}
			
			if(compromisso.getDataCompromisso() != null) {
				ps.setDate(i++, compromisso.getDataCompromisso());
			}else {
				ps.setNull(i++, java.sql.Types.DATE);
			}
			
			if(compromisso.getHoraCompromisso() != null) {
				ps.setTime(i++, compromisso.getHoraCompromisso());
			}else {
				ps.setNull(i++, java.sql.Types.TIME);
			}
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erro ao inserir o compromisso!", e);
		}
		
	}
	
	public List<CompromissoVo> findAllCompromissos(){
		StringBuilder query = new StringBuilder()
				.append("SELECT")
				.append("	c.rowid AS id_compromisso, ")
				.append("	c.nm_compromisso AS nome_compromisso, ")
				.append("	f.rowid AS id_funcionario, ")
				.append("	f.nm_funcionario AS nome_funcionario, ")
				.append("	a.rowid AS id_agenda, ")
				.append("	a.nm_agenda AS nome_agenda, ")
				.append("	c.data_compromisso, ")
				.append("	c.hora_compromisso ")
				.append("FROM compromissos c ")
				.append("LEFT JOIN funcionario f ON c.rowid_funcionario = f.rowid ")
				.append("LEFT JOIN agenda a ON c.rowid_agenda = a.rowid ");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			CompromissoVo vo = null;
			List<CompromissoVo> compromissos = new ArrayList<>();
			
			while(rs.next()){
				vo = new CompromissoVo();
				vo.setRowid(rs.getString("id_compromisso"));
				vo.setNome(rs.getString("nome_compromisso"));
				vo.setDataCompromisso(rs.getDate("data_compromisso"));
				vo.setHoraCompromisso(rs.getTime("hora_compromisso"));
				
				FuncionarioVo funcionario = new FuncionarioVo();
				funcionario.setRowid(rs.getString("id_funcionario"));
				funcionario.setNome(rs.getString("nome_funcionario") != null ? rs.getString("nome_funcionario") : "Não atribuido");
				vo.setFuncionario(funcionario);
				
				AgendaVo agenda = new AgendaVo();
				agenda.setRowid(rs.getString("id_agenda"));
				agenda.setNome(rs.getString("nome_agenda") != null ? rs.getString("nome_agenda") : "Não atribuido");
				vo.setAgenda(agenda);
				
				compromissos.add(vo);
			}
			return compromissos;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erro ao buscar os compromissos!", e);
		}
	}
	
	public void updateCompromisso(CompromissoVo compromisso) {
		StringBuilder query = new StringBuilder("UPDATE compromissos SET nm_compromisso = ?, rowid_funcionario = ?, rowid_agenda = ?, data_compromisso = ?, hora_compromisso = ? WHERE rowid = ?");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setString(i++, compromisso.getNome());
			
			if(compromisso.getFuncionario() != null && compromisso.getFuncionario().getRowid() != null && !compromisso.getFuncionario().getRowid().trim().isEmpty()) {
				ps.setString(i++, compromisso.getFuncionario().getRowid());
			}else {
				ps.setNull(i++, java.sql.Types.VARCHAR);
			}
			
			if(compromisso.getAgenda() != null && compromisso.getAgenda().getRowid() != null && !compromisso.getAgenda().getRowid().trim().isEmpty()) {
				ps.setString(i++, compromisso.getAgenda().getRowid());
			}else {
				ps.setNull(i++, java.sql.Types.VARCHAR);
			}
			
			if(compromisso.getDataCompromisso() != null) {
				ps.setDate(i++, compromisso.getDataCompromisso());
			}else {
				ps.setNull(i++, java.sql.Types.DATE);
			}
			
			if(compromisso.getHoraCompromisso() != null) {
				ps.setTime(i++, compromisso.getHoraCompromisso());
			}else {
				ps.setNull(i++, java.sql.Types.TIME);
			}
			
			ps.setString(i++, compromisso.getRowid());
			
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erro ao atualizar o compromisso!", e);
		}
		
	}
	
	public CompromissoVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder()
				.append("SELECT ")
				.append("	c.rowid AS id_compromisso, ")
				.append("	c.nm_compromisso AS nome_compromisso, ")
				.append("	f.rowid AS id_funcionario, ")
				.append("	f.nm_funcionario AS nome_funcionario, ")
				.append("	a.rowid AS id_agenda, ")
				.append("	a.nm_agenda AS nome_agenda, ")
				.append("	c.data_compromisso, ")
				.append("	c.hora_compromisso ")
				.append("FROM compromissos c ")
				.append("LEFT JOIN funcionario f ON c.rowid_funcionario = f.rowid ")
				.append("LEFT JOIN agenda a ON c.rowid_agenda = a.rowid ")
				.append("WHERE c.rowid = ?");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				CompromissoVo vo = null;
				
				while(rs.next()) {
					vo = new CompromissoVo();
					vo.setRowid(rs.getString("id_compromisso"));
					vo.setNome(rs.getString("nome_compromisso"));
					vo.setDataCompromisso(rs.getDate("data_compromisso"));
					vo.setHoraCompromisso(rs.getTime("hora_compromisso"));
					
					FuncionarioVo funcionario = new FuncionarioVo();
					funcionario.setRowid(rs.getString("id_funcionario"));
					funcionario.setNome(rs.getString("nome_funcionario") != null ? rs.getString("nome_funcionario") : "Não atribuído");
					vo.setFuncionario(funcionario);
					
					AgendaVo agenda = new AgendaVo();
					agenda.setRowid(rs.getString("id_agenda"));
					agenda.setNome(rs.getString("nome_agenda") != null ? rs.getString("nome_agenda") : "Não atribuída");
					vo.setAgenda(agenda);
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erro ao buscar o compromisso por código!", e);
		}
	}
	
	public List<CompromissoVo> buscarPorAgenda(String idAgenda) {
	    StringBuilder query = new StringBuilder()
	            .append("SELECT ")
	            .append("	c.rowid AS id_compromisso, ")
	            .append("	c.nm_compromisso AS nome_compromisso, ")
	            .append("	f.rowid AS id_funcionario, ")
	            .append("	f.nm_funcionario AS nome_funcionario, ")
	            .append("	a.rowid AS id_agenda, ")
	            .append("	a.nm_agenda AS nome_agenda, ")
	            .append("	c.data_compromisso, ")
	            .append("	c.hora_compromisso ")
	            .append("FROM compromissos c ")
	            .append("LEFT JOIN funcionario f ON c.rowid_funcionario = f.rowid ")
	            .append("LEFT JOIN agenda a ON c.rowid_agenda = a.rowid ")
	            .append("WHERE c.rowid_agenda = ?");
	    
	    try(
	        Connection con = getConexao();
	        PreparedStatement ps = con.prepareStatement(query.toString())){
	        
	        ps.setString(1, idAgenda);
	        
	        try(ResultSet rs = ps.executeQuery()) {
	            List<CompromissoVo> lista = new ArrayList<>();
	            
	            while(rs.next()){
	                CompromissoVo vo = new CompromissoVo();
	                vo.setRowid(rs.getString("id_compromisso"));
	                vo.setNome(rs.getString("nome_compromisso"));
	                vo.setDataCompromisso(rs.getDate("data_compromisso"));
	                vo.setHoraCompromisso(rs.getTime("hora_compromisso"));
	                
	                FuncionarioVo funcionario = new FuncionarioVo();
	                funcionario.setRowid(rs.getString("id_funcionario"));
	                funcionario.setNome(rs.getString("nome_funcionario") != null ? rs.getString("nome_funcionario") : "Não atribuído");
	                vo.setFuncionario(funcionario);
	                
	                AgendaVo agenda = new AgendaVo();
	                agenda.setRowid(rs.getString("id_agenda"));
	                agenda.setNome(rs.getString("nome_agenda") != null ? rs.getString("nome_agenda") : "Não atribuída");
	                vo.setAgenda(agenda);
	                
	                lista.add(vo);
	            }
	            return lista;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new TechnicalException("Erro ao buscar compromissos por agenda!", e);
	    }
	}
	
	public void excluirSeNaoExistirOFuncionario(String idFuncionario) {
		StringBuilder query = new StringBuilder("DELETE FROM compromissos WHERE rowid_funcionario = ?");
		
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setString(i, idFuncionario);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erro ao excluir os compromissos vinculados ao funcionário!", e);
		}
	}

}
