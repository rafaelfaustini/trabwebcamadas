package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Capitulo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Historia historia;
	private String observacoes;
	private int ordem;
	private String texto;
	private String titulo;
	private List<Comentario> comentarios;
	
	
	public Capitulo() {
		comentarios = new ArrayList<Comentario>();
	}
	

	public Capitulo(int id, Historia historia, String observacoes, int ordem, String texto, String titulo,
			List<Comentario> comentarios) {
		super();
		this.id = id;
		this.historia = historia;
		this.observacoes = observacoes;
		this.ordem = ordem;
		this.texto = texto;
		this.titulo = titulo;
		this.comentarios = comentarios;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Historia getHistoria() {
		return historia;
	}


	public void setHistoria(Historia historia) {
		this.historia = historia;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public int getOrdem() {
		return ordem;
	}


	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + ((historia == null) ? 0 : historia.hashCode());
		result = prime * result + id;
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ordem;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Capitulo other = (Capitulo) obj;
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (historia == null) {
			if (other.historia != null)
				return false;
		} else if (!historia.equals(other.historia))
			return false;
		if (id != other.id)
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (ordem != other.ordem)
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Capitulo [id=" + id + ", historia=" + historia + ", observacoes=" + observacoes + ", ordem=" + ordem
				+ ", texto=" + texto + ", titulo=" + titulo + ", comentarios=" + comentarios + "]";
	}
	
	

	
}
