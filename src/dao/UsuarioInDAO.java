package dao;

import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public interface UsuarioInDAO { // Interface de PessoaDAO
	void Inserir(Usuario objeto) throws SQLException;
	
	List<Usuario> listarTodos() throws SQLException;
	
	Boolean Excluir(int _id) throws SQLException;
	
	Boolean Atualizar(Usuario _objeto) throws SQLException;
	
	Usuario buscarPorId(int _id) throws SQLException;
	
}
