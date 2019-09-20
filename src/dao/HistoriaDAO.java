package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Comentario;
import model.Favorito;
import model.Historia;
import model.Usuario;

public class HistoriaDAO implements HistoriaInDAO {
	
	private Connection conexao;
	
	public HistoriaDAO(Connection _conn) {
		this.conexao = _conn;
	}

	@Override
	public void Inserir(Historia objeto) throws SQLException {
		String SQL = "insert into historia (autor, titulo, sinopse) values (?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, objeto.getAutor().getId());
		ps.setString(2, objeto.getTitulo());
		ps.setString(3, objeto.getSinopse());
		
		ps.execute();
	}

	@Override
	public List<Historia> listarTodos() throws SQLException {
		List<Historia> historias = new ArrayList<Historia>();
		ResultSet rs = null;
		
		String SQL = "select autor, terminada, data, titulo, sinopse from historia";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt(1);
			UsuarioDAO dao = new UsuarioDAO(this.conexao);
			
			Usuario autor = dao.buscarPorId(rs.getInt(2));
			Boolean terminada = rs.getBoolean(3);
			LocalDateTime data = rs.getTimestamp(4).toLocalDateTime();
			String titulo = rs.getString(5);
			String sinopse = rs.getString(6);
			
			Historia h = new Historia(id, autor, terminada, data, titulo, sinopse);
			
			historias.add(h);
		}
		
		return historias;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
		boolean rs = false;
		
		String SQL = "delete from historia where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.execute();
		return rs; 
	}

	@Override
	public Boolean Atualizar(Historia _objeto) throws SQLException {
		
		boolean rs = false;
		
		String SQL = "update historia set terminada=?, titulo=?, sinopse=? where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		

		ps.setBoolean(1, _objeto.isTerminada());
		ps.setString(2, _objeto.getTitulo());
		ps.setString(3, _objeto.getSinopse());
		ps.setInt(4, _objeto.getId());
		
		rs = ps.execute();
		
		return rs;
	}

	@Override
	public Historia buscarPorId(int _id) throws SQLException {
		ResultSet rs = null;
		
		String SQL = "Select * from historia where id=?"; 
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		rs = ps.executeQuery();
		

		
		if(rs.next()) {
			int id = rs.getInt(1);
			UsuarioDAO dao = new UsuarioDAO(this.conexao);
			Usuario autor = dao.buscarPorId(rs.getInt(2));
			Boolean terminada = rs.getBoolean(3);
			LocalDateTime data = rs.getTimestamp(4).toLocalDateTime();
			String titulo = rs.getString(5);
			String sinopse = rs.getString(5);
			
			Historia h = new Historia(id, autor, terminada, data, titulo, sinopse);
	
			return h;
		}
		
		return null;
	}

	@Override
	public List<Historia> listarHistoriaPorUsuario(int _idUsuario) throws SQLException {
		ResultSet rs = null;
		List<Historia> historias = new ArrayList<Historia>();
		String SQL = "SELECT * from historia where autor=?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		ps.setInt(1, _idUsuario); 
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt(1);
			UsuarioDAO dao = new UsuarioDAO(this.conexao);
			Usuario autor = dao.buscarPorId(rs.getInt(2));
			Boolean terminada = rs.getBoolean(3);
			LocalDateTime data = rs.getTimestamp(4).toLocalDateTime();
			String titulo = rs.getString(5);
			String sinopse = rs.getString(5);

			
			
			Historia historia = new Historia(id, autor, terminada, data, titulo, sinopse);
			
			historias.add(historia);
		}
		
		return historias;
	}

}
