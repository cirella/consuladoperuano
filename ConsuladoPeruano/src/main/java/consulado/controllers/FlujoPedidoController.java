package consulado.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import consulado.entities.Empleado;

import consulado.services.EmpleadoService;
import consulado.services.PedidoService;


@Controller
@RequestMapping("/flujopedidos")
public class FlujoPedidoController {


	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private PedidoService pedidoService;
	
	/*
	 * VISUALIZAR PEDIDOS REGISTRADOS PARA SER PREPARADOS
	 */
	@Secured({ "ROLE_COCINA", "ROLE_ADMIN_TIENDA" })
	@GetMapping("/c-list-pedido-registrado")
	public String showListaPedidosRegistrado(Model model, HttpSession session) {
		Long id_empleado = (Long) session.getAttribute("id_persona_logeado");
		model.addAttribute("listPedido", pedidoService.listByIdLocalRegistrado(empleadoService.findById(id_empleado).getLocal().getId()));
		
		return "/flujopedidos/list-pedido-registrado";
	}

	
	@Secured({ "ROLE_COCINA", "ROLE_ADMIN_TIENDA"  })
	@PostMapping("/do-pedido-preparado")
	public String doAvanzarPedido(@RequestParam("id") long id, Model model, HttpSession session) {
		pedidoService.avanzarPreparado(id);
		
		return showListaPedidosRegistrado(model,session);
	}
	
	/*
	 * VISUALIZAR PEDIDOS PREPARADOS PARA SER ASIGNADOS
	 */
	
	@Secured({ "ROLE_ATENCION", "ROLE_ADMIN_TIENDA"  })
	@GetMapping("/c-list-pedido-preparado")
	public String showListaPedidosPreparado(Model model, HttpSession session) {
		Long id_empleado = (Long) session.getAttribute("id_persona_logeado");
		Empleado empleado=empleadoService.findById(id_empleado);
		model.addAttribute("listPedido", pedidoService.listByIdLocalPreparado(empleado.getLocal().getId()));
		return "/flujopedidos/list-pedido-preparado";
	}
	

	
	/*
	 * VISUALIZAR PEDIDOS ASIGNADOS PARA SER ENTREGADOS
	 */
	
	
	@Secured({ "ROLE_DELIVERY" })
	@GetMapping("/c-list-my-pedido-asignado")
	public String showListaPedidosMyAsignado(Model model, HttpSession session) {
		Long id_empleado = (Long) session.getAttribute("id_persona_logeado");
		model.addAttribute("listPedido", pedidoService.listByIdEmpleadoSinEntregar(id_empleado));
		return "/flujopedidos/list-my-pedido-asignado";
	}
	
	
	@Secured({ "ROLE_DELIVERY"  })
	@PostMapping("/do-pedido-entregado")
	public String doEntregarPedido(@RequestParam("id") long id,Model model, HttpSession session) {
		
		pedidoService.avanzarEntregado(id);
		return showListaPedidosMyAsignado(model,session);

	}
	
	
	
	
	/*
	 * CANCELAR UN PEDIDO
	 */
	
	@Secured({ "ROLE_DELIVERY" })
	@GetMapping("/c-cancel-pedido/{id}")
	public String showCancelacionPedido(@PathVariable("id") long id,Model model, HttpSession session) {
		model.addAttribute("pedido", pedidoService.findById(id));
		return "/flujopedidos/cancel-pedido";
	}
	
	@Secured({ "ROLE_DELIVERY"  })
	@PostMapping("/do-pedido-cancelado")
	public String doCancelarPedido(@RequestParam("id") long id,@RequestParam("motivo") String motivo,Model model, HttpSession session) {
		
		pedidoService.avanzarCancelado(id,motivo);
		return showListaPedidosMyAsignado(model,session);

	}

	
}
