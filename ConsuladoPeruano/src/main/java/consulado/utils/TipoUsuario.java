package consulado.utils;

public class TipoUsuario {
	//ADMIN_TIENDA(1), COCINA(2), ATENCION_PUBLICO(3), DELIVERY(4), CLIENTE(5),  SUPER_ADMIN(6);
	
	private String tipo;
	private int id;
		
	public TipoUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoUsuario(String tipo, int id) {
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
