package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.h2.mvstore.OffHeapStore;

import br.com.soc.sistema.exception.TechnicalException;
import br.com.soc.sistema.infra.PeriodoDisponivel;
import br.com.soc.sistema.vo.AgendaVo;

public class AgendaDao extends Dao{

	public void insertAgenda(AgendaVo agendaVO) {
		StringBuilder query = new StringBuilder("INSERT INTO agenda (nm_agenda, periodo_disponivel) VALUES (?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, agendaVO.getNome());
			ps.setInt(i++, Integer.parseInt(agendaVO.getPeriodoDisponivel().getCodigo()));
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Ocorreu um erro ao tentar salvar a agenda", e);
		}		
	}
	
	public void updateAgenda(AgendaVo agendaVO) {
		StringBuilder query = new StringBuilder("UPDATE agenda SET nm_agenda = ?, periodo_disponivel = ? WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, agendaVO.getNome());
			ps.setInt(i++, Integer.parseInt(agendaVO.getPeriodoDisponivel().getCodigo()));
			ps.setString(i++, agendaVO.getRowid());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Ocorreu um erro ao tentar alterar a agenda", e);
		}		
	}
	
	public void deleteAgenda(AgendaVo agendaVO) {
		StringBuilder query = new StringBuilder("DELETE FROM agenda WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, agendaVO.getRowid());
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Ocorreu um erro ao tentar deletar a agenda", e);
		}		
	}
	
	public List<AgendaVo> findAllAgenda(){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodo_disponivel periodo FROM agenda");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			AgendaVo vo = null;
			List<AgendaVo> agendas = new ArrayList<>();
			
			while(rs.next()) {
				vo = new AgendaVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));
				vo.setPeriodoDisponivel(PeriodoDisponivel.buscarPorCodigo(rs.getString("periodo")));
				
				agendas.add(vo);
			}
			return agendas;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<AgendaVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodo_disponivel periodo FROM agenda")
									.append(" WHERE lower(nm_agenda) like lower(?)");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				AgendaVo vo = null;
				List<AgendaVo> agendas = new ArrayList<>();
				
				while(rs.next()) {
					vo = new AgendaVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
					vo.setPeriodoDisponivel(PeriodoDisponivel.buscarPorCodigo(rs.getString("periodo")));
					
					agendas.add(vo);
				}
				return agendas;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public AgendaVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_agenda nome, periodo_disponivel periodo FROM agenda")
				.						append(" WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				AgendaVo vo = null;
				while(rs.next()) {
					vo = new AgendaVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
					vo.setPeriodoDisponivel(PeriodoDisponivel.buscarPorCodigo(rs.getString("periodo")));
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
