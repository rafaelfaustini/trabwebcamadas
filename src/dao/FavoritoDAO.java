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

public class FavoritoDAO implements FavoritoInDAO {

	private Connection conexao;
	
	public FavoritoDAO(Connection _conn) {
		this.conexao = _conn;
	}

	
	@Override
	public void Inserir(Favorito objeto) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Favorito> listarTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Atualizar(Favorito _objeto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Favorito buscarPorId(int _id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Favorito> listarFavoritosPorUsuario(int _idUsuario) throws SQLException {
		ResultSet rs = null;
		List<Favorito> favoritos = new ArrayList<Favorito>();
		String SQL = "SELECT f.id_historia, h.autor, h.terminada, h.data, h.titulo, h.sinopse from historia h, favorito f "
				+ "WHERE id_usuario = ? and f.id_historia = h.id";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		ps.setInt(1, _idUsuario); 
		
		rs = ps.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt(1);
			int idAutor = rs.getInt(2);
			boolean terminada = rs.getBoolean(3);
			LocalDateTime data = rs.getTimestamp(4).toLocalDateTime();
			String titulo = rs.getString(5);
			String sinopse = rs.getString(6);

			
			
			UsuarioDAO dao = new UsuarioDAO(this.conexao);
			
			Usuario autor = dao.buscarPorId(idAutor);
			
			Usuario usuario = dao.buscarPorId(_idUsuario);
			
			Historia historia = new Historia(id, autor, terminada, data, titulo, sinopse);
			
			Favorito fav = new Favorito(id, historia);
			
			favoritos.add(fav);
		}
		
		return favoritos;
	}

}