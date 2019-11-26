package model;

import java.io.Serializable;

public class Favorito implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario id;
	private Historia historia;
	
	
	public Favorito() {
		
	}


	public Favorito(Usuario id, Historia historia) {
		super();
		this.id = id;
		this.historia = historia;
	}


	public Usuario getId() {
		return id;
	}


	public void setId(Usuario id) {
		this.id = id;
	}


	public Historia getHistoria() {
		return historia;
	}


	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

	@Override
	public Favorito clone() {
		return new Favorito(this.id, this.historia);
	}


	public void restaurarFavorito(Favorito _favorito) {
		
		this.id = _favorito.id;
		this.historia = _favorito.historia;
		
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((historia == null) ? 0 : historia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (historia == null) {
			if (other.historia != null)
				return false;
		} else if (!historia.equals(other.historia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Favorito [id=" + id + ", historia=" + historia + "]";
	}


	

	
}
