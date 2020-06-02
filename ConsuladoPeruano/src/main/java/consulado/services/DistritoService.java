package consulado.services;

import java.util.List;

import consulado.entities.Distrito;


public interface DistritoService {
	   public Distrito save(Distrito distrito);
	   public void delete(Distrito distrito);
	   public Distrito findById(Long id);
	   
	   public List<Distrito> listAll();
	   public List<Distrito> findByLocalId(Long id);
	   public List<Distrito> findByLocalIdNull();
	   
	   public void asociaDistritoConLocal(Long iddistrito, Long idlocal);

	   public void desasociaDistritoDeLocal(Long iddistrito);

}
