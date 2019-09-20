package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comentario implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String texto;
	private LocalDateTime criado;
	private Usuario autor;
	private Capitulo capitulo;
	
	
	public Comentario() {
		
	}


	public Comentario(int id, String texto, LocalDateTime criado, Usuario autor, Capitulo capitulo) {
		super();
		this.id = id;
		this.texto = texto;
		this.criado = criado;
		this.autor = autor;
	}

	

	public Capitulo getCapitulo() {
		return capitulo;
	}


	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public LocalDateTime getCriado() {
		return criado;
	}


	public void setCriado(LocalDateTime criado) {
		this.criado = criado;
	}


	public Usuario getAutor() {
		return autor;
	}


	public void setAutor(Usuario autor) {
		this.autor = autor;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((capitulo == null) ? 0 : capitulo.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + id;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		Comentario other = (Comentario) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (capitulo == null) {
			if (other.capitulo != null)
				return false;
		} else if (!capitulo.equals(other.capitulo))
			return false;
		if (criado == null) {
			if (other.criado != null)
				return false;
		} else if (!criado.equals(other.criado))
			return false;
		if (id != other.id)
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", criado=" + criado + ", autor=" + autor + "]";
	}


	

	
}
