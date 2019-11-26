package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Historia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Usuario autor;
	private boolean terminada;
	private LocalDateTime data;
	private String titulo;
	private String sinopse;
	
	public Historia() {
		
	}

	
	
	public Historia(int id, Usuario autor, boolean terminada, LocalDateTime data, String titulo, String sinopse) {
		super();
		this.id = id;
		this.autor = autor;
		this.terminada = terminada;
		this.data = data;
		this.titulo = titulo;
		this.sinopse = sinopse;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public boolean isTerminada() {
		return terminada;
	}

	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	@Override
	public Historia clone() {
		return new Historia(this.id, this.autor, this.terminada, this.data, this.titulo, this.sinopse);
	}


	public void restaurarHistoria(Historia _historia) {
		
		this.id = _historia.id;
		this.autor = _historia.autor;
		this.terminada = _historia.terminada;
		this.data = _historia.data;
		this.titulo = _historia.titulo;
		this.sinopse = _historia.sinopse;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + id;
		result = prime * result + ((sinopse == null) ? 0 : sinopse.hashCode());
		result = prime * result + (terminada ? 1231 : 1237);
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
		Historia other = (Historia) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id != other.id)
			return false;
		if (sinopse == null) {
			if (other.sinopse != null)
				return false;
		} else if (!sinopse.equals(other.sinopse))
			return false;
		if (terminada != other.terminada)
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
		return this.titulo+" de "+this.getAutor().getUsername();
	}
	
	
	
	

}
