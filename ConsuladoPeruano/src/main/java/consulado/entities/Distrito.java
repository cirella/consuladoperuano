package consulado.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Distrito implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombredistrito;
	@ManyToOne
	@JoinColumn(nullable = true)
	private Local local;
	
	@PrePersist
	public void TrimAllFields() {
		this.nombredistrito=this.nombredistrito.trim();	
	}
	
	
	public Distrito() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombredistrito() {
		return nombredistrito;
	}


	public void setNombredistrito(String nombredistrito) {
		this.nombredistrito = nombredistrito;
	}


	public Local getLocal() {
		return local;
	}


	public void setLocal(Local local) {
		this.local = local;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombredistrito == null) ? 0 : nombredistrito.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((local == null) ? 0 : local.hashCode());
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
		Distrito other = (Distrito) obj;
		if (nombredistrito == null) {
			if (other.nombredistrito != null)
				return false;
		} else if (!nombredistrito.equals(other.nombredistrito))
			return false;
		if (id != other.id)
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Distrito [id=" + id + ", nombredistrito=" + nombredistrito + ", local=" + local + "]";
	}


	public Distrito(long id, String nombredistrito, Local local) {
		super();
		this.id = id;
		this.nombredistrito = nombredistrito;
		this.local = local;
	}

	
	
	
}
