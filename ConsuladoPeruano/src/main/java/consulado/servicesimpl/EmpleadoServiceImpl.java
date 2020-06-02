package consulado.servicesimpl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consulado.entities.Usuario;
import consulado.entities.Empleado;
import consulado.repositories.EmpleadoRepository;
import consulado.services.EmpleadoService;
import consulado.services.UsuarioService;
@Service
public class EmpleadoServiceImpl implements EmpleadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EmpleadoRepository empleadoRepository;	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Empleado empleado) {
		usuarioService.delete(empleado.getUsuario());
		empleadoRepository.delete(empleado);
	}

	@Override
	public Empleado findById(Long id) {
		List<Empleado> empleados=empleadoRepository.findByIdEmpleado(id);
		if (empleados.isEmpty()) {
			return null;
		} else {
			return empleados.get(0);
		}
	}

	@Override
	public List<Empleado> listAll() {
		return (List<Empleado>)empleadoRepository.findAll();
	}

	@Override
	public Empleado save(Empleado empleado, boolean crea_usuario) {
		if (crea_usuario) {
			Usuario usuario=usuarioService.makeUsuario(empleado.getEmail(), empleado.getTipoempleado());
			usuario=usuarioService.save(usuario);
			empleado.setUsuario(usuario);
			this.save(empleado);
		} 
		return this.save(empleado);
		
	}

	@Override
	public Empleado saveSinFK(Empleado empleado) {
		Usuario usuario=usuarioService.findByIdUsuario(findById(empleado.getId()).getUsuario().getId());
		empleado.setUsuario(usuario);
		return this.save(empleado);
	}

}
