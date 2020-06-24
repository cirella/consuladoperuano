package consulado.servicesimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import consulado.entities.Usuario;

import consulado.entities.Empleado;
import consulado.entities.Rol;
import consulado.repositories.EmpleadoRepository;
import consulado.services.EmpleadoService;
import consulado.services.RolService;
import consulado.services.UsuarioService;
import consulado.utils.Constantes;

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
	@Autowired
	private RolService rolService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
	@Transactional
	public Empleado save(Empleado empleado, boolean crea_usuario) {
		if (crea_usuario) {
			Constantes constantes = new Constantes();
			Usuario usuario=new Usuario();
					usuario.setNombreusuario(empleado.getEmail());
					usuario.setPassword(passwordEncoder.encode(empleado.getNumerodocumento()));
					usuario.setTipo(empleado.getTipoempleado());
					
					
					Rol nuevo_rol=new Rol();
					nuevo_rol.setRol(constantes.findById("TiposRol",empleado.getTipoempleado()));
					List<Rol> nuevos_roles=new ArrayList<Rol>();
					nuevos_roles.add(nuevo_rol);
					usuario.setRoles(nuevos_roles);
					
					usuario=usuarioService.save(usuario);
		
			empleado.setUsuario(usuario);
			this.save(empleado);
		} 
		return this.save(empleado);
		
	}

	@Override
	@Transactional
	public Empleado saveSinFK(Empleado empleado) {
		Usuario usuario=usuarioService.findByIdUsuario(findById(empleado.getId()).getUsuario().getId());
		//usuaario.
		
		Constantes constantes=new Constantes();
		
		Rol rol=rolService.findByIdUsuario(usuario.getId());
		
		
		//rol.setRol(constantes.findById("TiposRol", usuario.getTipo()));
		rol.setRol(constantes.findById("TiposRol", empleado.getTipoempleado()));
		rol=rolService.save(rol);
		usuario.setTipo(empleado.getTipoempleado());
		usuario.setNombreusuario(empleado.getEmail());
		usuario=usuarioService.save(usuario);
		empleado.setUsuario(usuario);
		return this.save(empleado);
	}

	@Override
	public void cambiaPassword(Empleado empleado, String nuevo_password) {
		usuarioService.cambiaPassword(empleado.getUsuario().getId(),nuevo_password);
		
		
	}

	@Override
	public Empleado findByIdUsuario(Long id) {
		List<Empleado> empleados=empleadoRepository.findByIdUsuario(id);
		if (empleados.isEmpty()) {
			return null;
		} else {
			return empleados.get(0);
		}
	}

	@Override
	public List<Empleado> listByIdLocal(Long idLocal) {
		// TODO Auto-generated method stub
		return empleadoRepository.listByIdLocal(idLocal);
	}

	@Override
	public List<Empleado> listByIdLocalIdTipo(Long idLocal, int idTipo) {
		// TODO Auto-generated method stub
		return empleadoRepository.listByIdLocalIdTipo(idLocal, idTipo);
	}

}
