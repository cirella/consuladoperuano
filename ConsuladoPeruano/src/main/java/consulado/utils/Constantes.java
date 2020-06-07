package consulado.utils;

import java.util.ArrayList;
import java.util.List;

public class Constantes {

	public List<TipoDocumento> TiposDocumento;
	public List<TipoEmpleado> TiposEmpleado;
	public List<TipoUsuario> TiposUsuario;
	public List<TipoActivo> TiposActivo;

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
		TiposUsuario.add(new TipoUsuario("SuperAdmin", 6));
		
		TiposActivo = new ArrayList<TipoActivo>();
		TiposActivo.add(new TipoActivo("Inactivo", 0));
		TiposActivo.add(new TipoActivo("Activo", 1));
	}

	public String[] ArregloStringTiposActivo() {
		
		String[] elementos = new String[TiposActivo.size()];
		for(TipoActivo activo: TiposActivo) {
			elementos[activo.getId()]=activo.getTipo();
		}
		
		return elementos;
	}
	
	
	public String[] ArregloStringTiposDocumento() {
		
		String[] elementos = new String[TiposDocumento.size()+1];
		elementos[0]="Ninguno";
		for(TipoDocumento documento: TiposDocumento) {
			elementos[documento.getId()]=documento.getTipo();
		}
		
		return elementos;
	}
	
	public String[] ArregloStringTiposUsuario() {
		
		String[] elementos = new String[TiposUsuario.size()+1];
		elementos[0]="Ninguno";
		for(TipoUsuario usuario: TiposUsuario) {
			elementos[usuario.getId()]=usuario.getTipo();
		}
		
		return elementos;
	}
	
	public String[] ArregloStringTiposEmpleado() {
		
		String[] elementos = new String[TiposEmpleado.size()+1];
		elementos[0]="Ninguno";
		for(TipoEmpleado empleado: TiposEmpleado) {
			elementos[empleado.getId()]=empleado.getTipo();
		}
		
		return elementos;
	}
	
	
	public int findByTipoDocumento(String tipo) {
		for(TipoDocumento documento: TiposDocumento) {
			if (documento.getTipo()==tipo) {
				return documento.getId();
			}
				
		}		
		return -1;
	}
	
	public int findByTipoEmpleado(String tipo) {
		for(TipoEmpleado empleado: TiposEmpleado) {
			if (empleado.getTipo()==tipo) {
				return empleado.getId();
			}
				
		}		
		return -1;
	}
	
	public int findByTipoUsuario(String tipo) {
		for(TipoUsuario usuario: TiposUsuario) {
			if (usuario.getTipo()==tipo) {
				return usuario.getId();
			}
				
		}		
		return -1;
	}
	
};
