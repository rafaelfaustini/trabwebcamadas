package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.FavoritoDAO;
import dao.HistoriaDAO;
import dao.UsuarioDAO;
import model.Favorito;
import model.Historia;
import model.Usuario;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class FavoritoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Favorito favorito;
	private ListDataModel<Favorito> favoritos;
	private List<Historia> historias;
	private List<Usuario> usuarios;
	private Usuario usuario;
	private Boolean editando;
	private Favorito favoritoAntesDaEdicao;
	
	public FavoritoBean() {
		this.favorito = new Favorito();
		this.favoritos = new ListDataModel<Favorito>();
		this.editando = false;
	}
	
	public Favorito getFavorito() {
		return favorito;
	}
	



	public List<Historia> getHistorias() {
		return historias;
	}

	public void setHistorias(List<Historia> historias) {
		this.historias = historias;
	}

	public void setFavorito(Favorito favorito) {
		this.favorito = favorito;
	}

	public ListDataModel<Favorito> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(ListDataModel<Favorito> favoritos) {
		this.favoritos = favoritos;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.favorito = new Favorito();
		System.out.println("Limpo");
	}
	
	public void Editar(Favorito _favorito) {
		this.favoritoAntesDaEdicao = _favorito.clone();
		this.favorito = _favorito;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.favorito.restaurarFavorito(this.favoritoAntesDaEdicao);
		this.favorito = new Favorito();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.favorito = this.favoritos.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FavoritoDAO dao = new FavoritoDAO(conexao);
			
			dao.Inserir(this.favorito, this.usuario.getId());
			
			//ListDataModel<Favorito> listaFavoritos = new ListDataModel<>(dao.listarFavoritosPorHistoria(this.favorito.getHistoria().getId()));
			//this.favoritos = listaFavoritos;
			
			this.favorito = new Favorito();
			
			JSFUtil.adicionarMensagemSucesso("Favorito cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FavoritoDAO dao = new FavoritoDAO(conexao);
			
			//dao.Excluir(this.favorito.getId());
			
			//ListDataModel<Favorito> listaFavoritos = new ListDataModel<>(dao.listarFavoritosPorHistoria(this.favorito.getHistoria().getId()));
			//this.favoritos = listaFavoritos;
			
			this.favorito = new Favorito();
			
			JSFUtil.adicionarMensagemSucesso("Favorito excluído com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			HistoriaDAO daoHistoria = new HistoriaDAO(conexao);
			UsuarioDAO daousuario = new UsuarioDAO(conexao);
			
			this.historias = new ArrayList<Historia>(daoHistoria.listarTodos());
			this.usuarios = new ArrayList<Usuario>(daousuario.listarTodos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
