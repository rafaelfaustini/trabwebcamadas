package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.HistoriaDAO;
import dao.UsuarioDAO;
import model.Historia;
import model.Usuario;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class HistoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Historia historia;
	private ListDataModel<Historia> historias;
	private List<Usuario> usuarios;
	private Boolean editando;
	private Historia historiaAntesDaEdicao;
	
	public HistoriaBean() {
		this.historia = new Historia();
		this.historias = new ListDataModel<Historia>();
		this.editando = false;
	}
	
	public Historia getHistoria() {
		return historia;
	}
	



	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

	public ListDataModel<Historia> getHistorias() {
		return historias;
	}

	public void setHistorias(ListDataModel<Historia> historias) {
		this.historias = historias;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.historia = new Historia();
		System.out.println("Limpo");
	}
	
	public void Editar(Historia _historia) {
		this.historiaAntesDaEdicao = _historia.clone();
		this.historia = _historia;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.historia.restaurarHistoria(this.historiaAntesDaEdicao);
		this.historia = new Historia();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.historia = this.historias.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			HistoriaDAO dao = new HistoriaDAO(conexao);
			
			dao.Atualizar(this.historia);
			
			JSFUtil.adicionarMensagemSucesso("Historia alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.historia = new Historia();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			HistoriaDAO dao = new HistoriaDAO(conexao);
			
			dao.Inserir(this.historia);
			
			ListDataModel<Historia> listaHistorias = new ListDataModel<>(dao.listarTodos());
			this.historias = listaHistorias;
			
			this.historia = new Historia();
			
			JSFUtil.adicionarMensagemSucesso("Historia cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			HistoriaDAO dao = new HistoriaDAO(conexao);
			
			dao.Excluir(this.historia.getId());
			
			ListDataModel<Historia> listaHistorias = new ListDataModel<>(dao.listarTodos());
			this.historias = listaHistorias;
			
			this.historia = new Historia();
			
			JSFUtil.adicionarMensagemSucesso("Historia excluída com sucesso!");
			
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
			
			HistoriaDAO dao = new HistoriaDAO(conexao);
			UsuarioDAO daoUsuario = new UsuarioDAO(conexao);
			
			this.usuarios = new ArrayList<Usuario>(daoUsuario.listarTodos());
			this.historias = new ListDataModel<>(dao.listarTodos());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
