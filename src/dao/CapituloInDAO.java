package dao;

import java.sql.SQLException;
import java.util.List;

import model.Capitulo;


public interface CapituloInDAO {
	void Inserir(Capitulo objeto) throws SQLException;

	List<Capitulo> listarTodos() throws SQLException;

	Boolean Excluir(int _id) throws SQLException;

	Boolean Atualizar(Capitulo _objeto) throws SQLException;

	Capitulo buscarPorId(int _id) throws SQLException;
	
	List<Capitulo> listarCapitulosPorHistoria(int _idHistoria) throws SQLException;
	
}
