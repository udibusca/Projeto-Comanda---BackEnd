package br.com.projeto.comanda.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.comanda.domain.Mesa;
import br.com.projeto.comanda.eventos.RecursoCriadoEvent;
import br.com.projeto.comanda.repositories.MesaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/mesa")
public class MesaResource {
	
	@Autowired
	private MesaRepository mesaDAO;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Mesa> criar(@Valid @RequestBody Mesa mesa, HttpServletResponse response) {
		Mesa mesaSalva = mesaDAO.save(mesa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response,  mesaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(mesaSalva);
		
	}	
	
	@GetMapping
	public List<Mesa> listar() {
		return mesaDAO.findAll();
	}	
	
	@GetMapping("/{id}")
	public Mesa buscarPeloCodigo(@PathVariable Long id){
		
		Mesa mesa = mesaDAO.findOne(id);
		return mesa;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		mesaDAO.delete(id);
	}	
}