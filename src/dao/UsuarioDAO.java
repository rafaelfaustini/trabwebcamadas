package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Favorito;
import model.Usuario;

public class UsuarioDAO implements UsuarioInDAO {
	
	private Connection conexao;
	
	public UsuarioDAO(Connection _conn) {
		this.conexao = _conn;
	}

	@Override
	public void Inserir(Usuario objeto) throws SQLException {

		String SQL = "insert into usuario (username, email, senha, displayname, ativado) values (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setString(1, objeto.getUsername());
		ps.setString(2, objeto.getEmail());
		ps.setString(3, objeto.getSenha());
		ps.setString(4, objeto.getDisplayname());
		
		
		ps.execute();
	}

	@Override
	public List<Usuario> listarTodos() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet rs = null;
		
		String SQL = "select id, username, senha, displayname from Usuario";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String email = rs.getString(3);
			String senha = rs.getString(4);
			String displayname = rs.getString(5);
			
			FavoritoDAO daoFav = new FavoritoDAO(this.conexao);
			List<Favorito> favoritos = daoFav.listarFavoritosPorUsuario(id);

			Usuario u = new Usuario(id, username, email, senha, displayname, favoritos);
			
			usuarios.add(u);
		}
		
		return usuarios;
	}

	@Override
	public Boolean Excluir(int _id) throws SQLException {
		boolean rs = false;
		
		String SQL = "delete from usuario where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		
		rs = ps.execute();
		return rs; 
	}

	@Override
	public Boolean Atualizar(Usuario _objeto) throws SQLException {
		
		boolean rs = false;
		
		String SQL = "update usuario set username=?, email=?, senha=?, displayname=? where id=?";
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		

		ps.setString(1, _objeto.getUsername());
		ps.setString(2, _objeto.getEmail());
		ps.setString(3, _objeto.getSenha());
		ps.setString(4, _objeto.getDisplayname());
		
		rs = ps.execute();
		
		return rs;
	}

	@Override
	public Usuario buscarPorId(int _id) throws SQLException {
		ResultSet rs = null;
		
		String SQL = "Select * from usuario where id=?"; 
				
		PreparedStatement ps = this.conexao.prepareStatement(SQL);
		
		ps.setInt(1, _id);
		rs = ps.executeQuery();
		

		
		if(rs.next()) {
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String email = rs.getString(3);
			String senha = rs.getString(4);
			String displayname = rs.getString(5);
			
			FavoritoDAO daoEnd = new FavoritoDAO(this.conexao);
			List<Favorito> favoritos = daoEnd.listarFavoritosPorUsuario(id);
			
			Usuario u = new Usuario(id, username, email, senha, displayname, favoritos);
	
			return u;
		}
		
		return null;
	}

}
