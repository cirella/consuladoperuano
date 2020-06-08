package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Direccion;

import consulado.repositories.DireccionRepository;
import consulado.services.DireccionService;

@Service
public class DireccionServiceImpl implements DireccionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DireccionRepository direccionRepository;

	@Override
	@Transactional
	public Direccion save(Direccion direccion) {
		// TODO Auto-generated method stub
		return direccionRepository.save(direccion);
	}

	@Override
	@Transactional
	public void delete(Direccion direccion) {
		direccionRepository.delete(direccion);
	}

	@Override
	public Direccion findById(Long id) {
		List<Direccion> direcciones=direccionRepository.findByIdDireccion(id);
		if (direcciones.isEmpty()) {
			return null;
		} else {
			return direcciones.get(0);
		}
	}

	@Override
	public List<Direccion> listAll() {
		// TODO Auto-generated method stub
		return (List<Direccion>)direccionRepository.findAll();
	}



	@Override
	public List<Direccion> findByNombreDireccion(String nombredireccion) {
		return direccionRepository.findByDireccion(nombredireccion);
	}

	@Override
	public List<Direccion> findByClienteId(Long id) {
		// TODO Auto-generated method stub
		return direccionRepository.findByIdCliente(id);
	}

}
