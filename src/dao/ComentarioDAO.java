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

public class ComentarioDAO implements ComentarioInDAO {
	private Connection conexao;
	
	public ComentarioDAO(Connection _conn) {
		this.conexao = _conn;
	}

	@Override
	public void Inserir(model.Comentario objeto) throws SQLException {

		String SQL = "insert into comentario (texto ,id_usuario, id_capitulo) values (?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		CapituloDAO dao = new CapituloDAO(this.conexao);
		
		ps.setString(1, objeto.getTexto());
		ps.setInt(2, objeto.getAutor().getId());
		ps.setInt(3, objeto.getCapitulo().getId());
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
	public model.Comentario buscarPorId(int _id) throws SQLException {
		ResultSet rs = null;
		
		String SQL = "Select * from comentario where id=?"; 
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		rs = ps.executeQuery();
		

		
		if(rs.next()) {
			int id = rs.getInt(1);
			String texto = rs.getString(2);
			LocalDateTime criado = rs.getTimestamp(3).toLocalDateTime();
			UsuarioDAO daoUsuario = new UsuarioDAO(this.conexao);
			CapituloDAO daoCapitulo = new CapituloDAO(this.conexao);
			
			Usuario autor = daoUsuario.buscarPorId(rs.getInt(4));
			Capitulo capitulo = daoCapitulo.buscarPorId(rs.getInt(5));
			
			
			Comentario c = new Comentario(id, texto, criado, autor, capitulo);
	
			return c;
		}
		
		return null;
	}

	@Override
	public List<model.Comentario> listarComentariosPorCapitulo(int _idCapitulo) throws SQLException {
		ResultSet rs = null;
		List<Comentario> comentarios = new ArrayList<Comentario>();
		String SQL = "SELECT * from comentario where id_capitulo = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		ps.setInt(1, _idCapitulo); 
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt(1);
			String texto = rs.getString(2);
			LocalDateTime criado = rs.getTimestamp(3).toLocalDateTime();
			UsuarioDAO daoUsuario = new UsuarioDAO(this.conexao);
			CapituloDAO daoCapitulo = new CapituloDAO(this.conexao);
			
			Usuario autor = daoUsuario.buscarPorId(rs.getInt(4));
			Capitulo capitulo = daoCapitulo.buscarPorId(rs.getInt(5));
			
			
			Comentario c = new Comentario(id, texto, criado, autor, capitulo);
			
			comentarios.add(c);
		}
		
		return comentarios;
	}

}
