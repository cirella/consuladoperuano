package consulado.utils;

public class TipoActivo {
	// INACTIVO(0), ACTIVO(1);
	public String tipo;
	public int id;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoActivo(String tipo, int id) {
		super();
		this.tipo = tipo;
		this.id = id;
	}

	public TipoActivo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
