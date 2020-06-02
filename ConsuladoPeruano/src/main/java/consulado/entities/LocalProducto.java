package consulado.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LocalProducto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Producto producto;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Local local;
	private int stock;
	
	

	public LocalProducto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	public Local getLocal() {
		return local;
	}



	public void setLocal(Local local) {
		this.local = local;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		result = prime * result + stock;
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
		LocalProducto other = (LocalProducto) obj;
		if (id != other.id)
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "LocalProducto [id=" + id + ", producto=" + producto + ", local=" + local + ", stock=" + stock + "]";
	}



	public LocalProducto(long id, Producto producto, Local local, int stock) {
		super();
		this.id = id;
		this.producto = producto;
		this.local = local;
		this.stock = stock;
	}

	
	
}
