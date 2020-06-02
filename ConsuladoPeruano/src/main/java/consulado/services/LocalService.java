package consulado.services;

import java.util.List;

import consulado.entities.Local;

public interface LocalService {
	   public Local save(Local local);
	   public void delete(Local local);
	   public Local findById(Long id);
	   
	   public List<Local> listAll();
}
