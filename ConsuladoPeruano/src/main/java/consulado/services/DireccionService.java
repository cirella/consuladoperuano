package consulado.services;

import java.util.List;

import consulado.entities.Direccion;

public interface DireccionService {
	   public Direccion save(Direccion direccion);
	   public void delete(Direccion direccion);
	   public Direccion findById(Long id);
	   
	   public List<Direccion> listAll();
	   public List<Direccion> findByNombreDireccion(String nombredireccion);
	   public List<Direccion> findByClienteId(Long id);
	   
	   
	   
}
