package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Categoria;

import consulado.repositories.CategoriaRepository;

import consulado.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	@Transactional
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	@Transactional
	public void delete(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}

	@Override
	public Categoria findById(Long id) {
		List<Categoria> categorias = categoriaRepository.findByIdCategoria(id);
		if (categorias.isEmpty()) {
			return null;
		} else {
			return categorias.get(0);
		}

	}

	@Override
	public List<Categoria> listAll() {
		return (List<Categoria>) categoriaRepository.findAll();
	}

}
