package br.com.projeto.comanda.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.comanda.domain.Pedido;
import br.com.projeto.comanda.eventos.RecursoCriadoEvent;
import br.com.projeto.comanda.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PedidoService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Pedido> findPedidos() {
		return service.pesquisaTodosPedidos();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findPedidoPorId(@PathVariable Long id) {
		Pedido retorno = service.pesquisaPedidoPorId(id);
		if (retorno != null) {
			return ResponseEntity.ok().body(retorno);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity<Pedido> criar(@Valid @RequestBody Pedido pedido, HttpServletResponse response) {
		logger.info("Pedido criar -> {}", pedido);
		Pedido pedidoSalva = service.salvarPedido(pedido);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pedidoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalva);
	}

}
