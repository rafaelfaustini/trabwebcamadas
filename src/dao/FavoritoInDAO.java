package dao;

import java.sql.SQLException;
import java.util.List;

import model.Comentario;
import model.Favorito;


public interface FavoritoInDAO {
	void Inserir(Favorito objeto) throws SQLException;

	List<Favorito> listarTodos() throws SQLException;

	Boolean Excluir(int _id) throws SQLException;

	Boolean Atualizar(Favorito _objeto) throws SQLException;

	Favorito buscarPorId(int _id) throws SQLException;
	
	List<Favorito> listarFavoritosPorUsuario(int _idUsuario) throws SQLException;

}
