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
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombres;
	private String apellidos;
	private String numerodocumento;
	private String telefono;
	private String email;
	private int tipodocumento;
	private int tipoempleado;

	
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Usuario usuario;
	@ManyToOne(optional = true)
	@JoinColumn(nullable = true)
	private Local local;
	
	@PrePersist
	public void TrimAllFields() {
		this.nombres=this.nombres.trim();
		this.apellidos=this.apellidos.trim();
		this.telefono=this.telefono.trim();
		this.email=this.email.trim();
		this.numerodocumento=this.numerodocumento.trim();
		
	}
	
	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(int tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public int getTipoempleado() {
		return tipoempleado;
	}

	public void setTipoempleado(int tipoempleado) {
		this.tipoempleado = tipoempleado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		result = prime * result + ((numerodocumento == null) ? 0 : numerodocumento.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + tipodocumento;
		result = prime * result + tipoempleado;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Empleado other = (Empleado) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		if (numerodocumento == null) {
			if (other.numerodocumento != null)
				return false;
		} else if (!numerodocumento.equals(other.numerodocumento))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipodocumento != other.tipodocumento)
			return false;
		if (tipoempleado != other.tipoempleado)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", numerodocumento="
				+ numerodocumento + ", telefono=" + telefono + ", email=" + email + ", tipodocumento=" + tipodocumento
				+ ", tipoempleado=" + tipoempleado + ", usuario=" + usuario + ", local=" + local + "]";
	}

	public Empleado(long id, String nombres, String apellidos, String numerodocumento, String telefono, String email,
			int tipodocumento, int tipoempleado, Usuario usuario, Local local) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.numerodocumento = numerodocumento;
		this.telefono = telefono;
		this.email = email;
		this.tipodocumento = tipodocumento;
		this.tipoempleado = tipoempleado;
		this.usuario = usuario;
		this.local = local;
	}

	
}
