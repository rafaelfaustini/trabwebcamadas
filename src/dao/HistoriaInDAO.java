package dao;

import java.sql.SQLException;
import java.util.List;

import model.Comentario;
import model.Historia;


public interface HistoriaInDAO {
	void Inserir(Historia objeto) throws SQLException;

	List<Historia> listarTodos() throws SQLException;

	Boolean Excluir(int _id) throws SQLException;

	Boolean Atualizar(Historia _objeto) throws SQLException;

	Historia buscarPorId(int _id) throws SQLException;
	
	List<Comentario> listarHistoriaPorUsuario(int _idUsuario) throws SQLException;
}
