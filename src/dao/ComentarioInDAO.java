package dao;

import java.sql.SQLException;
import java.util.List;

import model.Comentario;


public interface ComentarioInDAO {
	void Inserir(Comentario objeto) throws SQLException;

	List<Comentario> listarTodos() throws SQLException;

	Boolean Excluir(int _id) throws SQLException;

	Boolean Atualizar(Comentario _objeto) throws SQLException;

	Comentario buscarPorId(int _id) throws SQLException;
}
