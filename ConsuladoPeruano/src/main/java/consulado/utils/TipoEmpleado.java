package consulado.utils;

public class TipoEmpleado {
//	ADMIN_TIENDA(1), COCINA(2), ATENCION_PUBLICO(2), DELIVERY(3);
	private String tipo;
	private int id;
		
	public TipoEmpleado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoEmpleado(String tipo, int id) {
		super();
		this.tipo = tipo;
		this.id = id;
	}
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
}
