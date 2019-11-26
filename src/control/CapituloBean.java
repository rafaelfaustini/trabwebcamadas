package control;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.CapituloDAO;
import dao.HistoriaDAO;
import model.Capitulo;
import model.Historia;
import util.FabricaConexao;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class CapituloBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Capitulo capitulo;
	private ListDataModel<Capitulo> capitulos;
	private List<Historia> historias;
	private Boolean editando;
	private Capitulo capituloAntesDaEdicao;
	
	public CapituloBean() {
		this.capitulo = new Capitulo();
		this.capitulos = new ListDataModel<Capitulo>();
		this.editando = false;
	}
	
	public Capitulo getCapitulo() {
		return capitulo;
	}
	



	public List<Historia> getHistorias() {
		return historias;
	}

	public void setHistorias(List<Historia> historias) {
		this.historias = historias;
	}

	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	public ListDataModel<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(ListDataModel<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
	
	public Boolean getEditando() {
		return editando;
	}

	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	
	public void Limpar() {
		this.capitulo = new Capitulo();
		System.out.println("Limpo");
	}
	
	public void Editar(Capitulo _capitulo) {
		this.capituloAntesDaEdicao = _capitulo.clone();
		this.capitulo = _capitulo;
		this.editando = true;
	}
	
	public void CancelarEdicao() {
		this.capitulo.restaurarCapitulo(this.capituloAntesDaEdicao);
		this.capitulo = new Capitulo();
		this.editando = false;
	}
	
	public void PrepararExcluir() 
	{
		this.capitulo = this.capitulos.getRowData();
	}
	
	//------------ OPERAÇÕES COM A BASE DE DADOS
	
	public void SalvarEdicao() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CapituloDAO dao = new CapituloDAO(conexao);
			
			dao.Atualizar(this.capitulo);
			
			JSFUtil.adicionarMensagemSucesso("Capitulo alterada com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.capitulo = new Capitulo();
		this.editando = false;
	}
	
	public void Adicionar() {
		//DAO
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CapituloDAO dao = new CapituloDAO(conexao);
			
			dao.Inserir(this.capitulo);
			
			ListDataModel<Capitulo> listaCapitulos = new ListDataModel<>(dao.listarCapitulosPorHistoria(this.capitulo.getHistoria().getId()));
			this.capitulos = listaCapitulos;
			
			this.capitulo = new Capitulo();
			
			JSFUtil.adicionarMensagemSucesso("Capitulo cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CapituloDAO dao = new CapituloDAO(conexao);
			
			dao.Excluir(this.capitulo.getId());
			
			ListDataModel<Capitulo> listaCapitulos = new ListDataModel<>(dao.listarCapitulosPorHistoria(this.capitulo.getHistoria().getId()));
			this.capitulos = listaCapitulos;
			
			this.capitulo = new Capitulo();
			
			JSFUtil.adicionarMensagemSucesso("Capitulo excluído com sucesso!");
			
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
		CapituloDAO dao = new CapituloDAO(conexao);
		int id = this.capitulo.getHistoria().getId();
		this.capitulos = new ListDataModel<>(dao.listarCapitulosPorHistoria(id));
		} catch(Exception e){
			System.out.print(e);
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			CapituloDAO dao = new CapituloDAO(conexao);
			HistoriaDAO daoHistoria = new HistoriaDAO(conexao);
			
			this.historias = new ArrayList<Historia>(daoHistoria.listarTodos());
			System.out.print(this.historias);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
