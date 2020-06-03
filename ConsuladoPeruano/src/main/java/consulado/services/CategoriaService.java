package consulado.services;

import java.util.List;

import consulado.entities.Categoria;

public interface CategoriaService {
	   public Categoria save(Categoria categoria);
	   public void delete(Categoria categoria);
	   public Categoria findById(Long id);
	   
	   public List<Categoria> listAll();
}
