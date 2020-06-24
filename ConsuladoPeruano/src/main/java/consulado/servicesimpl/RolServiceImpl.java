package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import consulado.entities.Rol;

import consulado.repositories.RolRepository;

import consulado.services.RolService;

@Service
public class RolServiceImpl implements RolService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RolRepository rolRepository;
	
	@Transactional
	@Override
	public Rol save(Rol rol) {
		// TODO Auto-generated method stub
		return rolRepository.save(rol);
	}
	@Override
	public void delete(Rol rol) {
		rolRepository.delete(rol);
	}
	@Override
	public Rol findById(Long id) {
		List<Rol> roles = rolRepository.findByIdRol(id);
		if (roles.isEmpty()) {
			return null;
		} else {
			return roles.get(0);
		}

	}
	@Override
	public List<Rol> listAll() {
		return (List<Rol>) rolRepository.findAll();
	}
	@Override
	public Rol findByIdUsuario(Long id) {
		List<Rol> roles = rolRepository.findByIdUsuario(id);
		if (roles.isEmpty()) {
			return null;
		} else {
			return roles.get(0);
		}
	}



}
