package dao;

import java.sql.SQLException;
import java.util.List;

import model.Comentario;
import model.Favorito;


public interface FavoritoInDAO {
	void Inserir(Favorito objeto, int _idUsuario) throws SQLException;

	Boolean Excluir(int _idHistoria, int idUsuario) throws SQLException;
	
	List<Favorito> listarFavoritosPorUsuario(int _idUsuario) throws SQLException;
}
