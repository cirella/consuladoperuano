package consulado.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Cliente cliente;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Direccion direccion;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Local local;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Empleado empleado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date horapedido;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horapreparado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaentrega;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horacancelado;

	private double precioventa;
	private double igv;
	private double valorventa;
	private int idempleadodelivery;
	private String observacion;

	
	private int entregado;
	private int cancelado;
	private int preparado;

	@PrePersist
	public void TrimAllFields() {
		this.observacion=this.observacion.trim();
	}
	
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return Id;
	}


	public void setId(long id) {
		Id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}


	public Local getLocal() {
		return local;
	}


	public void setLocal(Local local) {
		this.local = local;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	public Date getHorapedido() {
		return horapedido;
	}


	public void setHorapedido(Date horapedido) {
		this.horapedido = horapedido;
	}


	public Date getHorapreparado() {
		return horapreparado;
	}


	public void setHorapreparado(Date horapreparado) {
		this.horapreparado = horapreparado;
	}


	public Date getHoraentrega() {
		return horaentrega;
	}


	public void setHoraentrega(Date horaentrega) {
		this.horaentrega = horaentrega;
	}


	public Date getHoracancelado() {
		return horacancelado;
	}


	public void setHoracancelado(Date horacancelado) {
		this.horacancelado = horacancelado;
	}


	public double getPrecioventa() {
		return precioventa;
	}


	public void setPrecioventa(double precioventa) {
		this.precioventa = precioventa;
	}


	public double getIgv() {
		return igv;
	}


	public void setIgv(double igv) {
		this.igv = igv;
	}


	public double getValorventa() {
		return valorventa;
	}


	public void setValorventa(double valorventa) {
		this.valorventa = valorventa;
	}


	public int getIdempleadodelivery() {
		return idempleadodelivery;
	}


	public void setIdempleadodelivery(int idempleadodelivery) {
		this.idempleadodelivery = idempleadodelivery;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public int getEntregado() {
		return entregado;
	}


	public void setEntregado(int entregado) {
		this.entregado = entregado;
	}


	public int getCancelado() {
		return cancelado;
	}


	public void setCancelado(int cancelado) {
		this.cancelado = cancelado;
	}


	public int getPreparado() {
		return preparado;
	}


	public void setPreparado(int preparado) {
		this.preparado = preparado;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + cancelado;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((empleado == null) ? 0 : empleado.hashCode());
		result = prime * result + entregado;
		result = prime * result + ((horacancelado == null) ? 0 : horacancelado.hashCode());
		result = prime * result + ((horaentrega == null) ? 0 : horaentrega.hashCode());
		result = prime * result + ((horapedido == null) ? 0 : horapedido.hashCode());
		result = prime * result + ((horapreparado == null) ? 0 : horapreparado.hashCode());
		result = prime * result + idempleadodelivery;
		long temp;
		temp = Double.doubleToLongBits(igv);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((observacion == null) ? 0 : observacion.hashCode());
		temp = Double.doubleToLongBits(precioventa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + preparado;
		temp = Double.doubleToLongBits(valorventa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Pedido other = (Pedido) obj;
		if (Id != other.Id)
			return false;
		if (cancelado != other.cancelado)
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (empleado == null) {
			if (other.empleado != null)
				return false;
		} else if (!empleado.equals(other.empleado))
			return false;
		if (entregado != other.entregado)
			return false;
		if (horacancelado == null) {
			if (other.horacancelado != null)
				return false;
		} else if (!horacancelado.equals(other.horacancelado))
			return false;
		if (horaentrega == null) {
			if (other.horaentrega != null)
				return false;
		} else if (!horaentrega.equals(other.horaentrega))
			return false;
		if (horapedido == null) {
			if (other.horapedido != null)
				return false;
		} else if (!horapedido.equals(other.horapedido))
			return false;
		if (horapreparado == null) {
			if (other.horapreparado != null)
				return false;
		} else if (!horapreparado.equals(other.horapreparado))
			return false;
		if (idempleadodelivery != other.idempleadodelivery)
			return false;
		if (Double.doubleToLongBits(igv) != Double.doubleToLongBits(other.igv))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (observacion == null) {
			if (other.observacion != null)
				return false;
		} else if (!observacion.equals(other.observacion))
			return false;
		if (Double.doubleToLongBits(precioventa) != Double.doubleToLongBits(other.precioventa))
			return false;
		if (preparado != other.preparado)
			return false;
		if (Double.doubleToLongBits(valorventa) != Double.doubleToLongBits(other.valorventa))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Pedido [Id=" + Id + ", cliente=" + cliente + ", direccion=" + direccion + ", local=" + local
				+ ", empleado=" + empleado + ", horapedido=" + horapedido + ", horapreparado=" + horapreparado
				+ ", horaentrega=" + horaentrega + ", horacancelado=" + horacancelado + ", precioventa=" + precioventa
				+ ", igv=" + igv + ", valorventa=" + valorventa + ", idempleadodelivery=" + idempleadodelivery
				+ ", observacion=" + observacion + ", entregado=" + entregado + ", cancelado=" + cancelado
				+ ", preparado=" + preparado + "]";
	}


	public Pedido(long id, Cliente cliente, Direccion direccion, Local local, Empleado empleado, Date horapedido,
			Date horapreparado, Date horaentrega, Date horacancelado, double precioventa, double igv, double valorventa,
			int idempleadodelivery, String observacion, int entregado, int cancelado, int preparado) {
		super();
		Id = id;
		this.cliente = cliente;
		this.direccion = direccion;
		this.local = local;
		this.empleado = empleado;
		this.horapedido = horapedido;
		this.horapreparado = horapreparado;
		this.horaentrega = horaentrega;
		this.horacancelado = horacancelado;
		this.precioventa = precioventa;
		this.igv = igv;
		this.valorventa = valorventa;
		this.idempleadodelivery = idempleadodelivery;
		this.observacion = observacion;
		this.entregado = entregado;
		this.cancelado = cancelado;
		this.preparado = preparado;
	}


	
	
}
