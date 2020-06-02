package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Distrito;
import consulado.entities.Local;
import consulado.repositories.LocalRepository;
import consulado.services.DistritoService;
import consulado.services.LocalService;


@Service
public class LocalServiceImpl implements LocalService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private LocalRepository localRepository;
	@Autowired
	private DistritoService distritoService;
	
	@Override
	@Transactional
	public Local save(Local local) {
		// TODO Auto-generated method stub
		return localRepository.save(local);
	}

	@Override
	@Transactional
	public void delete(Local local) {
		List<Distrito> distritos=distritoService.findByLocalId(local.getId());
		for (Distrito distrito : distritos) 
		{ 
		    distritoService.desasociaDistritoDeLocal(distrito.getId());
		}
		localRepository.delete(local);
	}

	@Override
	public Local findById(Long id) {

		List<Local> locales = localRepository.findByIdLocal(id);
		if (locales.isEmpty()) {
			return null;
		} else {
			return locales.get(0);
		}

	}

	@Override
	public List<Local> listAll() {
		return (List<Local>) localRepository.findAll();
	}

}
