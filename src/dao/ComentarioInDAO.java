package dao;

import java.sql.SQLException;
import java.util.List;

import model.Comentario;


public interface ComentarioInDAO {
	void Inserir(Comentario objeto) throws SQLException;

	Boolean Excluir(int _id) throws SQLException;

	Comentario buscarPorId(int _id) throws SQLException;
	
	List<Comentario> listarComentariosPorCapitulo(int _idCapitulo) throws SQLException;
}
