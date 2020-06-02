package consulado.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombrecategoria;

	@PrePersist
	public void TrimAllFields() {
		this.nombrecategoria=this.nombrecategoria.trim();
	}
	
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombrecategoria=" + nombrecategoria + "]";
	}


	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Categoria(long id, String nombrecategoria) {
		super();
		this.id = id;
		this.nombrecategoria = nombrecategoria;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombrecategoria == null) ? 0 : nombrecategoria.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Categoria other = (Categoria) obj;
		if (nombrecategoria == null) {
			if (other.nombrecategoria != null)
				return false;
		} else if (!nombrecategoria.equals(other.nombrecategoria))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombrecategoria() {
		return nombrecategoria;
	}


	public void setNombrecategoria(String nombrecategoria) {
		this.nombrecategoria = nombrecategoria;
	}

	
}
