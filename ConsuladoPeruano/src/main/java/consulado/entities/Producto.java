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
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombreproducto;
	private String descripcion;
	private String imagen;
	private int activado;
	private double precio;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Categoria categoria;

	@PrePersist
	public void TrimAllFields() {
		this.nombreproducto=this.nombreproducto.trim();
		this.descripcion=this.descripcion.trim();
		this.imagen=this.imagen.trim();	
	}
	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombreproducto() {
		return nombreproducto;
	}


	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public int getActivado() {
		return activado;
	}


	public void setActivado(int activado) {
		this.activado = activado;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + activado;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nombreproducto == null) ? 0 : nombreproducto.hashCode());
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
		Producto other = (Producto) obj;
		if (activado != other.activado)
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id != other.id)
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (nombreproducto == null) {
			if (other.nombreproducto != null)
				return false;
		} else if (!nombreproducto.equals(other.nombreproducto))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombreproducto=" + nombreproducto + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", activado=" + activado + ", precio=" + precio + ", categoria=" + categoria + "]";
	}


	public Producto(long id, String nombreproducto, String descripcion, String imagen, int activado, double precio,
			Categoria categoria) {
		super();
		this.id = id;
		this.nombreproducto = nombreproducto;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.activado = activado;
		this.precio = precio;
		this.categoria = categoria;
	}

	

}
