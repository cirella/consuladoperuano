package consulado.services;

import java.util.List;

import consulado.entities.Rol;

public interface RolService {
	   public Rol save(Rol rol);
	   public void delete(Rol rol);
	   public Rol findById(Long id);
	   public Rol findByIdUsuario(Long id);	   
	   public List<Rol> listAll();
}
