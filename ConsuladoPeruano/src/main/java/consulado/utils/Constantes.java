
package consulado.utils;

import java.util.ArrayList;
import java.util.List;

public class Constantes {

	public List<Tipo> TiposDocumento;
	public List<Tipo> TiposEmpleado;
	public List<Tipo> TiposUsuario;
	public List<Tipo> TiposActivo;
	public List<Tipo> TiposRol;
	
	

	public Constantes() {
		Carga();
	}

	public void Carga() {
		TiposDocumento = new ArrayList<Tipo>();
		TiposDocumento.add(new Tipo("DNI", 1));
		TiposDocumento.add(new Tipo("Carnet Extranjería", 2));
		TiposDocumento.add(new Tipo("Pasaporte", 3));
		TiposDocumento.add(new Tipo("Otros", 4));

		TiposEmpleado = new ArrayList<Tipo>();
		TiposEmpleado.add(new Tipo("Administrador", 1));
		TiposEmpleado.add(new Tipo("Cocina", 2));
		TiposEmpleado.add(new Tipo("Atención al Público", 3));
		TiposEmpleado.add(new Tipo("Delivery", 4));

		TiposUsuario = new ArrayList<Tipo>();
		TiposUsuario.add(new Tipo("Administrador", 1));
		TiposUsuario.add(new Tipo("Cocina", 2));
		TiposUsuario.add(new Tipo("Atención al Público", 3));
		TiposUsuario.add(new Tipo("Delivery", 4));
		TiposUsuario.add(new Tipo("Cliente", 5));
		TiposUsuario.add(new Tipo("SuperAdmin", 6));
		
		TiposRol = new ArrayList<Tipo>();
		TiposRol.add(new Tipo("ROLE_ADMIN_TIENDA", 1));
		TiposRol.add(new Tipo("ROLE_COCINA", 2));
		TiposRol.add(new Tipo("ROLE_ATENCION", 3));
		TiposRol.add(new Tipo("ROLE_DELIVERY", 4));
		TiposRol.add(new Tipo("ROLE_CLIENTE", 5));
		TiposRol.add(new Tipo("ROLE_SUPER_ADMIN", 6));
		
		
		TiposActivo = new ArrayList<Tipo>();
		TiposActivo.add(new Tipo("Activo", 1));
		TiposActivo.add(new Tipo("Inactivo", 2));
		
		
	}

	public List<Tipo> ListaPorNombre(String nombre_lista) {
		List<Tipo> lista=null;
		switch(nombre_lista) {
			case "TiposDocumento":
				lista=this.TiposDocumento;
			break;
			case "TiposEmpleado":
				lista=this.TiposEmpleado;
			break;
			case "TiposUsuario":
				lista=this.TiposUsuario;
			break;
			case "TiposActivo":
				lista=this.TiposActivo;
			break;	
			case "TiposRol":
				lista=this.TiposRol;
			break;	
		}
		return lista;
	}
	
	
	public String[] ArregloString(String nombre_lista) {
		

		List<Tipo> lista=ListaPorNombre(nombre_lista);
		
		String[] elementos = new String[lista.size()];
		for(Tipo tipo: lista) {
			elementos[tipo.getId()-1]=tipo.getTipo();
		}
		
		return elementos;
	}
	
	

	
	public int findByTipo(String nombre_lista, String nombre_tipo) {
		
		List<Tipo> lista=ListaPorNombre(nombre_lista);
		
		for(Tipo tipo: lista) {
			if (tipo.getTipo()==nombre_tipo) {
				return tipo.getId();
			}
				
		}		
		return -1;
	}
	
	public String findById(String nombre_lista, int id_tipo) {
		
		List<Tipo> lista=ListaPorNombre(nombre_lista);
		
		for(Tipo tipo: lista) {
			if (tipo.getId()==id_tipo) {
				return tipo.getTipo();
			}
				
		}		
		return "";
	}
	
	
};
