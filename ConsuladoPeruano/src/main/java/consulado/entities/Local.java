package consulado.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Local implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombrelocal;
	@OneToMany(mappedBy="local")
	private List<LocalProducto> localesproductos;
	
	@PrePersist
	public void TrimAllFields() {
		this.nombrelocal=this.nombrelocal.trim();
	}
	
	public Local() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombrelocal() {
		return nombrelocal;
	}

	public void setNombrelocal(String nombrelocal) {
		this.nombrelocal = nombrelocal;
	}

	public List<LocalProducto> getLocalesproductos() {
		return localesproductos;
	}

	public void setLocalesproductos(List<LocalProducto> localesproductos) {
		this.localesproductos = localesproductos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((localesproductos == null) ? 0 : localesproductos.hashCode());
		result = prime * result + ((nombrelocal == null) ? 0 : nombrelocal.hashCode());
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
		Local other = (Local) obj;
		if (id != other.id)
			return false;
		if (localesproductos == null) {
			if (other.localesproductos != null)
				return false;
		} else if (!localesproductos.equals(other.localesproductos))
			return false;
		if (nombrelocal == null) {
			if (other.nombrelocal != null)
				return false;
		} else if (!nombrelocal.equals(other.nombrelocal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", nombrelocal=" + nombrelocal + ", size localesproductos=" + ((localesproductos != null)?localesproductos.size():"null") + "]";
	}

	public Local(long id, String nombrelocal, List<LocalProducto> localesproductos) {
		super();
		this.id = id;
		this.nombrelocal = nombrelocal;
		this.localesproductos = localesproductos;
	}

	



}
