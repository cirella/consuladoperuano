package consulado.utils;

import java.util.ArrayList;
import java.util.List;

public class Constantes {

	public List<TipoDocumento> TiposDocumento;
	public List<TipoEmpleado> TiposEmpleado;
	public List<TipoUsuario> TiposUsuario;

	public Constantes() {
		Carga();
	}

	public void Carga() {
		TiposDocumento = new ArrayList<TipoDocumento>();
		TiposDocumento.add(new TipoDocumento("DNI", 1));
		TiposDocumento.add(new TipoDocumento("Carnet Extranjería", 2));
		TiposDocumento.add(new TipoDocumento("Pasaporte", 3));
		TiposDocumento.add(new TipoDocumento("Otros", 4));

		TiposEmpleado = new ArrayList<TipoEmpleado>();
		TiposEmpleado.add(new TipoEmpleado("Administrador", 1));
		TiposEmpleado.add(new TipoEmpleado("Cocina", 2));
		TiposEmpleado.add(new TipoEmpleado("Atención al Público", 3));
		TiposEmpleado.add(new TipoEmpleado("Delivery", 4));

		TiposUsuario = new ArrayList<TipoUsuario>();
		TiposUsuario.add(new TipoUsuario("Administrador", 1));
		TiposUsuario.add(new TipoUsuario("Cocina", 2));
		TiposUsuario.add(new TipoUsuario("Atención al Público", 3));
		TiposUsuario.add(new TipoUsuario("Delivery", 4));
		TiposUsuario.add(new TipoUsuario("Cliente", 5));
		TiposUsuario.add(new TipoUsuario("SuperAdmin", 99));
	}

};
