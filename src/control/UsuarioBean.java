package control;

import java.io.Serializable;
import java.sql.Connection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.UsuarioDAO;
import model.Usuario;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private ListDataModel<Usuario> usuarios;
	private Boolean editando;
	private Usuario usuarioAntesDaEdicao;
	
	public UsuarioBean() {
		this.usuario = new Usuario();
		this.usuarios = new ListDataModel<Usuario>();
		this.editando = false;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ListDataModel<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ListDataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.usuario = new Usuario();
	}
	
	public void Editar(Usuario _usuario) {
		this.usuarioAntesDaEdicao = _usuario.clone();
		this.usuario = _usuario;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.usuario.restaurarUsuario(this.usuarioAntesDaEdicao);
		this.usuario = new Usuario();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.usuario = this.usuarios.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			UsuarioDAO dao = new UsuarioDAO(conexao);
			
			dao.Atualizar(this.usuario);
			
			JSFUtil.adicionarMensagemSucesso("Usuario alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.usuario = new Usuario();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			UsuarioDAO dao = new UsuarioDAO(conexao);
			
			dao.Inserir(this.usuario);
			
			ListDataModel<Usuario> listaUsuarios = new ListDataModel<>(dao.listarTodos());
			this.usuarios = listaUsuarios;
			
			this.usuario = new Usuario();
			
			JSFUtil.adicionarMensagemSucesso("Usuario cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			UsuarioDAO dao = new UsuarioDAO(conexao);
			
			dao.Excluir(this.usuario.getId());
			
			ListDataModel<Usuario> listaUsuarios = new ListDataModel<>(dao.listarTodos());
			this.usuarios = listaUsuarios;
			
			this.usuario = new Usuario();
			
			JSFUtil.adicionarMensagemSucesso("Usuario excluída com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			UsuarioDAO dao = new UsuarioDAO(conexao);
			
			this.usuarios = new ListDataModel<>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
