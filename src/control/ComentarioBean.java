package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.ComentarioDAO;
import dao.CapituloDAO;
import model.Comentario;
import model.Historia;
import model.Capitulo;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class ComentarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Comentario comentario;
	private ListDataModel<Comentario> comentarios;
	private List<Capitulo> capitulos;
	private List<Historia> historias;
	private Historia historia;

	
	public ComentarioBean() {
		this.comentario = new Comentario();
		this.comentarios = new ListDataModel<Comentario>();
		this.historia = new Historia();
	}
	
	public Comentario getComentario() {
		return comentario;
	}
	



	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public ListDataModel<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ListDataModel<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	

	public void Limpar() {
		this.comentario = new Comentario();
		System.out.println("Limpo");
	}
	
	public void PrepararExcluir() 
	{
		this.comentario = this.comentarios.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	

	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ComentarioDAO dao = new ComentarioDAO(conexao);
			
			dao.Inserir(this.comentario);
			
			ListDataModel<Comentario> listaComentarios = new ListDataModel<>(dao.listarComentariosPorCapitulo((this.comentario.getCapitulo().getId())));
			this.comentarios = listaComentarios;
			
			this.comentario = new Comentario();
			
			JSFUtil.adicionarMensagemSucesso("Comentario cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ComentarioDAO dao = new ComentarioDAO(conexao);
			
			dao.Excluir(this.comentario.getId());
			
			ListDataModel<Comentario> listaComentarios = new ListDataModel<>(dao.listarComentariosPorCapitulo(this.comentario.getCapitulo().getId()));
			this.comentarios = listaComentarios;
			
			this.comentario = new Comentario();
			
			JSFUtil.adicionarMensagemSucesso("Comentario excluído com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void carregaLista() {
		try {
			System.out.print("Entrou na lista");

		FabricaConexao fabrica = new FabricaConexao();
		Connection conexao = fabrica.fazerConexao();
		ComentarioDAO dao = new ComentarioDAO(conexao);
		int id = this.comentario.getCapitulo().getId();
		this.comentarios = new ListDataModel<>(dao.listarComentariosPorCapitulo(id));
		} catch(Exception e){
			System.out.print(e);
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ComentarioDAO dao = new ComentarioDAO(conexao);
			CapituloDAO daoCapitulo = new CapituloDAO(conexao);
			
			this.capitulos = new ArrayList<Capitulo>(daoCapitulo.listarCapitulosPorHistoria(this.historia.getId()));
			System.out.print(this.capitulos);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
