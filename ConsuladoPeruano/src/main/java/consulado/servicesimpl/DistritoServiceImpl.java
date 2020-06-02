package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Distrito;
import consulado.entities.Local;
import consulado.repositories.DistritoRepository;
import consulado.repositories.LocalRepository;
import consulado.services.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DistritoRepository distritoRepository;
	@Autowired
	private LocalRepository localRepository;
	
	@Override
	@Transactional
	public Distrito save(Distrito distrito) {
		return distritoRepository.save(distrito);
	}

	@Override
	@Transactional
	public void delete(Distrito distrito) {
		distritoRepository.delete(distrito);
	}

	@Override
	public List<Distrito> listAll() {
		return (List<Distrito>)distritoRepository.findAll();
	}

	@Override
	public Distrito findById(Long id) {
		// TODO Auto-generated method stub
		//return distritoRepository.findById(id);
		
		List<Distrito> distritos=distritoRepository.findByIdDistrito(id);
		if (distritos.isEmpty()) {
			return null;
		} else {
			return distritos.get(0);
		}
		
		
	}

	@Override
	public List<Distrito> findByLocalId(Long id) {
		return (List<Distrito>)distritoRepository.findByLocalId(id);
	}

	@Override
	public List<Distrito> findByLocalIdNull() {
		return (List<Distrito>)distritoRepository.findByLocalIdNull();
	}

	
	@Override
	@Transactional
	public void desasociaDistritoDeLocal(Long iddistrito) {
		List<Distrito> distritos=distritoRepository.findByIdDistrito(iddistrito);
		Distrito distrito=distritos.get(0);
		distrito.setLocal(null);
		save(distrito);
		
		
	}

	@Override
	@Transactional
	public void asociaDistritoConLocal(Long iddistrito, Long idlocal) {
		List<Distrito> distritos=distritoRepository.findByIdDistrito(iddistrito);
		List<Local> locales=localRepository.findByIdLocal(idlocal);
		
		Distrito distrito=distritos.get(0);
		distrito.setLocal(locales.get(0));
		save(distrito);
		
	}

}
