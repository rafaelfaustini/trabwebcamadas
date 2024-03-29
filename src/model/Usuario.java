package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String email;
	private String senha;
	private String displayname;
	private List<Favorito> favoritos;
	
	
	public Usuario() {
		favoritos = new ArrayList<Favorito>();
	}
	
	public Usuario(int id, String username, String email, String senha, String displayname,
			List<Favorito> favoritos) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.displayname = displayname;
		this.favoritos = favoritos;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	@Override
	public Usuario clone() {
		return new Usuario(this.id, this.username, this.email, this.senha, this.displayname, this.favoritos);
	}


	public void restaurarUsuario(Usuario _usuario) {
		
		this.id = _usuario.id;
		this.username = _usuario.username;
		this.email = _usuario.email;
		this.senha = _usuario.senha;
		this.displayname = _usuario.displayname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayname == null) ? 0 : displayname.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Usuario other = (Usuario) obj;
		if (displayname == null) {
			if (other.displayname != null)
				return false;
		} else if (!displayname.equals(other.displayname))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return username;
	}
	
	



}