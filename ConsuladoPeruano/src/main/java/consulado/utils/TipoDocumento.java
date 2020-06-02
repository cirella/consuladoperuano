package consulado.utils;

public class TipoDocumento {
	//DNI(1), CE(2), PASAPORTE(2), OTROS(3);
	public String tipo;
	public int id;
	
	
	
	
	public TipoDocumento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoDocumento(String tipo, int id) {
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
