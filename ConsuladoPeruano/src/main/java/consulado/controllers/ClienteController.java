package consulado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import consulado.services.ClienteService;

import consulado.utils.Constantes;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
    private ClienteService clienteService;
	
	@GetMapping("/c-list-cliente")
    public String showListaClientes(Model model){
		
		Constantes constantes=new Constantes();
		model.addAttribute("listaTipodocumento",constantes.ArregloStringTiposDocumento());
		model.addAttribute("listEmpleado", clienteService.listAll());
        return "/clientes/list-cliente";
    }
	
}
