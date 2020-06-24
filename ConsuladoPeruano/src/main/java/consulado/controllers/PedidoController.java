package consulado.controllers;

import java.util.ArrayList;
import java.util.List;

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

import consulado.entities.Pedido;
import consulado.entities.Producto;

import consulado.entities.DetallePedido;
import consulado.entities.Direccion;
import consulado.services.CategoriaService;

import consulado.services.DireccionService;

import consulado.services.PedidoService;
import consulado.services.ProductoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private DireccionService direccionService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private CategoriaService categoriaService;


	/*
	 * VISUALIZAR TUS PEDIDOS _ VISTA DESDE EL CLIENTE
	 */
	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-list-my-pedido")
	public String showListaMyPedidos(Model model, HttpSession session) {

		session.removeAttribute("detallespedidos");
		session.removeAttribute("cuenta_total");
		session.removeAttribute("cantidad_total");
		session.removeAttribute("categoria_id");
		session.removeAttribute("direccion_id");
		session.removeAttribute("local_id");

		Long id_cliente = (Long) session.getAttribute("id_persona_logeado");

		model.addAttribute("listPedido", pedidoService.findByIdCliente(id_cliente));
		return "/pedidos/list-my-pedido";
	}

	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-view-my-pedido/{id}")
	public String showDetalleMyPedido(@PathVariable("id") long id, Model model, HttpSession session) {

		Pedido pedido = pedidoService.findById(id);
		model.addAttribute("pedido", pedido);

		return "/pedidos/view-my-pedido";
	}

	/*
	 * ELEGIR DIRECCION DESTINO _ VISTA DESDE EL CLIENTE
	 */

	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-select-direccion/{existente}")
	public String showSelectDireccion(@PathVariable("existente") int existente, Model model, HttpSession session) {

		if (existente == 0) {
			session.removeAttribute("detallespedidos");
			session.removeAttribute("cuenta_total");
			session.removeAttribute("cantidad_total");
			session.removeAttribute("categoria_id");
			session.removeAttribute("direccion_id");
			session.removeAttribute("local_id");
			session.setAttribute("detallespedidos", new ArrayList<DetallePedido>());
			session.setAttribute("cuenta_total", 0.00);
			session.setAttribute("cantidad_total", 0);
			session.setAttribute("categoria_id", (long) 0);
			session.setAttribute("direccion_id", (long) 0);
			session.setAttribute("local_id", (long) 0);
		}

		
		List<Direccion> direcciones = direccionService.findByClienteId((Long) session.getAttribute("id_persona_logeado"));
		model.addAttribute("listDireccion", direcciones);
		return "/pedidos/select-direccion";
	}

	@Secured({ "ROLE_CLIENTE" })
	@PostMapping("/do-select-direccion")
	public String doSelectDireccion(@RequestParam("direccion") Direccion direccion, Model model, HttpSession session) {

		session.setAttribute("direccion_id", direccion.getId());
		session.setAttribute("local_id", direccion.getDistrito().getLocal().getId());
		model.addAttribute("listCategoria", categoriaService.listAll());
		return "/pedidos/select-categoria";
	}

	/*
	 * ELEGIR CATEGORIA _ VISTA DESDE EL CLIENTE
	 */

	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-select-categoria/{id}")
	public String showSelectCategoria(@PathVariable("id") Long id_categoria, Model model, HttpSession session) {

		session.setAttribute("categoria_id", id_categoria);

		preparaMostrarCatalogo(model, session);

		return "/pedidos/add-my-pedido";
	}

	/*
	 * AGREGAR NUEVO PEDIDO _ VISTA DESDE EL CLIENTE
	 */

	public void preparaMostrarCatalogo(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<DetallePedido> carrito = (List<DetallePedido>) session.getAttribute("detallespedidos");
		Long categoria_id = (Long) session.getAttribute("categoria_id");
		Long local_id = (Long) session.getAttribute("local_id");
		List<Producto> productos = productoService.findByCategoriaIdActivado(categoria_id, 1);
		model.addAttribute("productos", productos);
		int[] cantidades = pedidoService.cantidadesProductoEnCarrito(productos, carrito);

		int[] stocks = pedidoService.stocksProductoEnTienda(productos,local_id);
		
		//stocks= pedidoService.stocksRecalculadoPorCarrito(productos,carrito, stocks);
		

		model.addAttribute("cantidades", cantidades);
		model.addAttribute("stocks", stocks);
		session.setAttribute("categoria_id", categoria_id);
	}

	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-add-my-pedido/{id}")
	public String showAddMyPedido(@PathVariable("id") Long categoria_id, Model model, HttpSession session) {

		session.setAttribute("categoria_id", categoria_id);
		preparaMostrarCatalogo(model, session);

		return "/pedidos/add-my-pedido";
	}

	@Secured({ "ROLE_CLIENTE" })
	@PostMapping("/do-add-my-detallepedido")
	public String doAddMyDetallePedido(@RequestParam("producto_id") Long producto_id,
			@RequestParam("cantidad") int cantidad, Model model, HttpSession session) {

		if (cantidad > 0) {
			@SuppressWarnings("unchecked")
			List<DetallePedido> detallespedidos = (List<DetallePedido>) session.getAttribute("detallespedidos");

			detallespedidos = pedidoService.actualizaEnCarrito(detallespedidos, producto_id, cantidad);

			session.setAttribute("detallespedidos", detallespedidos);// actualiza el carrito
			session.setAttribute("cuenta_total", pedidoService.precioTotalProductosEnCarrito(detallespedidos));
			session.setAttribute("cantidad_total", pedidoService.cantidadTotalProductosEnCarrito(detallespedidos));
		}

		preparaMostrarCatalogo(model, session);

		return "/pedidos/add-my-pedido";
	}

	/*
	 * VER EL CARRITO _ VISTA DESDE EL CLIENTE
	 */

	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-view-my-carrito")
	public String showViewMyCarrito(Model model, HttpSession session) {

		return "/pedidos/view-my-carrito";
	}

	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-select-categoria")
	public String showSelectCategoria(Model model, HttpSession session) {
		
		model.addAttribute("listCategoria", categoriaService.listAll());
		
		return "/pedidos/select-categoria";
	}
	
	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/do-delete-my-detallepedido/{id}")
	public String doDeleteMyDetallePedido(@PathVariable("id") Long producto_id, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<DetallePedido> detallespedidos = (List<DetallePedido>) session.getAttribute("detallespedidos");

		detallespedidos = pedidoService.actualizaEnCarrito(detallespedidos, producto_id, 0);

		session.setAttribute("detallespedidos", detallespedidos);// actualiza el carrito
		session.setAttribute("cuenta_total", pedidoService.precioTotalProductosEnCarrito(detallespedidos));
		session.setAttribute("cantidad_total", pedidoService.cantidadTotalProductosEnCarrito(detallespedidos));

		preparaMostrarCatalogo(model, session);

		return "/pedidos/add-my-pedido";
	}

	/*
	 * CHECKOUT DEL PEDIDO
	 */

	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/do-delete-my-detallepedido-checkout/{id}")
	public String doDeleteMyDetallePedidoCheckout(@PathVariable("id") Long producto_id, Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<DetallePedido> detallespedidos = (List<DetallePedido>) session.getAttribute("detallespedidos");

		detallespedidos = pedidoService.actualizaEnCarrito(detallespedidos, producto_id, 0);

		session.setAttribute("detallespedidos", detallespedidos);// actualiza el carrito
		session.setAttribute("cuenta_total", pedidoService.precioTotalProductosEnCarrito(detallespedidos));
		session.setAttribute("cantidad_total", pedidoService.cantidadTotalProductosEnCarrito(detallespedidos));

		preparaMostrarCatalogo(model, session);

		return "/pedidos/checkout-my-pedido";
	}
	
	
	@Secured({ "ROLE_CLIENTE" })
	@GetMapping("/c-checkout-my-pedido")
	public String showCheckoutMyPedido(Model model, HttpSession session) {
		//Long id_cliente = (Long) session.getAttribute("id_persona_logeado");

		return "/pedidos/checkout-my-pedido";
	}

	@Secured({ "ROLE_CLIENTE" })
	@PostMapping("/do-checkout-pedido")
	public String doAddMyPedido(@RequestParam("observacion") String observacion, Model model, HttpSession session) {

		Long id_cliente = (Long) session.getAttribute("id_persona_logeado");
		Long id_direccion = (Long) session.getAttribute("direccion_id");
	
		@SuppressWarnings("unchecked")
		List<DetallePedido> detallespedidos = (List<DetallePedido>) session.getAttribute("detallespedidos");
		double cuentaTotal = (double) session.getAttribute("cuenta_total");

		pedidoService.makeNuevoPedido(id_cliente, id_direccion, observacion, cuentaTotal, detallespedidos);
		session.removeAttribute("detallespedidos");
		session.removeAttribute("cuenta_total");
		session.removeAttribute("cantidad_total");
		session.removeAttribute("categoria_id");
		session.removeAttribute("direccion_id");
		session.removeAttribute("local_id");
		
		model.addAttribute("listPedido", pedidoService.findByIdCliente(id_cliente));
		return "/pedidos/list-my-pedido";
	}

}
