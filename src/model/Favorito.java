package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Favorito implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Historia historia;
	private LocalDateTime criado;
	
	
	public Favorito() {
		
	}


	public Favorito(int id, Historia historia, LocalDateTime criado) {
		super();
		this.id = id;
		this.historia = historia;
		this.criado = criado;
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


	public LocalDateTime getCriado() {
		return criado;
	}


	public void setCriado(LocalDateTime criado) {
		this.criado = criado;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + ((historia == null) ? 0 : historia.hashCode());
		result = prime * result + id;
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
		Favorito other = (Favorito) obj;
		if (criado == null) {
			if (other.criado != null)
				return false;
		} else if (!criado.equals(other.criado))
			return false;
		if (historia == null) {
			if (other.historia != null)
				return false;
		} else if (!historia.equals(other.historia))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Favorito [id=" + id + ", historia=" + historia + ", criado=" + criado + "]";
	}


	

	
}
