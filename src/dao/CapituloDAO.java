package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Capitulo;
import model.Comentario;
import model.Favorito;
import model.Historia;
import model.Usuario;

public class CapituloDAO implements CapituloInDAO {

	private Connection conexao;
	
	public CapituloDAO(Connection _conn) {
		this.conexao = _conn;
	}

	@Override
	public void Inserir(Capitulo objeto) throws SQLException {
		String SQL = "insert into capitulo (historia, observacoes, ordem, texto, titulo) values (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, objeto.getHistoria().getId());
		ps.setString(2, objeto.getObservacoes());
		ps.setInt(3, objeto.getOrdem());
		ps.setString(4, objeto.getTexto());
		ps.setString(5, objeto.getTitulo());
		
		ps.execute();
	}


	@Override
	public Boolean Excluir(int _id) throws SQLException {
		boolean rs = false;
		
		String SQL = "delete from capitulo where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);

		
		rs = ps.execute();
		return rs; 
	}

	@Override
	public Boolean Atualizar(Capitulo _objeto) throws SQLException {
		
		boolean rs = false;
		
		String SQL = "update capitulo set observacoes=?, ordem=?, texto=?, titulo=? where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		

		ps.setString(1, _objeto.getObservacoes());
		ps.setInt(2, _objeto.getOrdem());
		ps.setString(3, _objeto.getTexto());
		ps.setString(4, _objeto.getTitulo());
		ps.setInt(5, _objeto.getId());
		
		rs = ps.execute();
		
		return rs;
	}

	@Override
	public Capitulo buscarPorId(int _id) throws SQLException {
		ResultSet rs = null;
		
		String SQL = "Select * from capitulo where id=?"; 
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		rs = ps.executeQuery();
		

		
		if(rs.next()) {

			int id = rs.getInt(1);
			
			HistoriaDAO dao = new HistoriaDAO(this.conexao);

			Historia historia = dao.buscarPorId(rs.getInt(2));
			String observacoes = rs.getString(3);
			int ordem = rs.getInt(4);
			String texto = rs.getString(5);
			String titulo = rs.getString(6);
			ComentarioDAO daoComentario = new ComentarioDAO(this.conexao);
			List<Comentario> comentarios = daoComentario.listarComentariosPorCapitulo(_id);
			
			Capitulo c = new Capitulo(id, historia, observacoes, ordem, texto, titulo, comentarios);
	
			return c;
		}
		
		return null;
	}

	@Override
	public List<Capitulo> listarCapitulosPorHistoria(int _idHistoria) throws SQLException {
		ResultSet rs = null;
		List<Capitulo> capitulos = new ArrayList<Capitulo>();
		String SQL = "select * from capitulo where historia=?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		ps.setInt(1, _idHistoria); 
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt(1);
			HistoriaDAO dao = new HistoriaDAO(this.conexao);
			Historia historia = dao.buscarPorId(rs.getInt(2));
			String observacoes = rs.getString(3);
			int ordem = rs.getInt(4);
			String texto = rs.getString(5);
			String titulo = rs.getString(6);

			
			
			ComentarioDAO daoComentario = new ComentarioDAO(this.conexao);
			List<Comentario> comentarios = daoComentario.listarComentariosPorCapitulo(id);
			
			Capitulo c = new Capitulo(id, historia, observacoes, ordem, texto, titulo, comentarios);
			
			capitulos.add(c);
		}
		
		return capitulos;
	}

}
