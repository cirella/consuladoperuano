package consulado.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Direccion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombredireccion;
	private String referencia;
	private String interior;
	private double latitud;
	private double longitud;

	@ManyToOne()
	private Distrito distrito;

	@ManyToOne()
	private Cliente cliente;

	@PrePersist
	public void TrimAllFields() {
		this.nombredireccion=this.nombredireccion.trim();
		this.referencia=this.referencia.trim();
		this.interior=this.interior.trim();		
	}
	
	
	
	public Direccion() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombredireccion() {
		return nombredireccion;
	}



	public void setNombredireccion(String nombredireccion) {
		this.nombredireccion = nombredireccion;
	}



	public String getReferencia() {
		return referencia;
	}



	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}



	public String getInterior() {
		return interior;
	}



	public void setInterior(String interior) {
		this.interior = interior;
	}



	public double getLatitud() {
		return latitud;
	}



	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}



	public double getLongitud() {
		return longitud;
	}



	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}



	public Distrito getDistrito() {
		return distrito;
	}



	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((nombredireccion == null) ? 0 : nombredireccion.hashCode());
		result = prime * result + ((distrito == null) ? 0 : distrito.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((interior == null) ? 0 : interior.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
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
		Direccion other = (Direccion) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (nombredireccion == null) {
			if (other.nombredireccion != null)
				return false;
		} else if (!nombredireccion.equals(other.nombredireccion))
			return false;
		if (distrito == null) {
			if (other.distrito != null)
				return false;
		} else if (!distrito.equals(other.distrito))
			return false;
		if (id != other.id)
			return false;
		if (interior == null) {
			if (other.interior != null)
				return false;
		} else if (!interior.equals(other.interior))
			return false;
		if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
			return false;
		if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Direccion [id=" + id + ", nombredireccion=" + nombredireccion + ", referencia=" + referencia + ", interior="
				+ interior + ", latitud=" + latitud + ", longitud=" + longitud + ", distrito=" + distrito + ", cliente="
				+ cliente + "]";
	}



	public Direccion(long id, String nombredireccion, String referencia, String interior, double latitud, double longitud,
			Distrito distrito, Cliente cliente) {
		super();
		this.id = id;
		this.nombredireccion = nombredireccion;
		this.referencia = referencia;
		this.interior = interior;
		this.latitud = latitud;
		this.longitud = longitud;
		this.distrito = distrito;
		this.cliente = cliente;
	}

	
}
