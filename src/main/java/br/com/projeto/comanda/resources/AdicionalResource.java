package br.com.projeto.comanda.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.projeto.comanda.domain.Adicional;
import br.com.projeto.comanda.repositories.AdicionalRepository;
import br.com.projeto.comanda.services.AdicionalService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/adicional")
public class AdicionalResource {
	
	@Autowired
	private AdicionalRepository adicionalDAO;
	@Autowired
	private AdicionalService service;
	
	@PostMapping
	public Adicional criar(@Valid @RequestBody Adicional adicional, HttpServletResponse response) {
		Adicional adicionalSalva = adicionalDAO.save(adicional);
		return adicionalSalva;
		
	}
	
	@GetMapping
	public List<Adicional> listar() {
		return adicionalDAO.findAll();
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Adicional> buscarPeloCodigo(@PathVariable Long id){
		
		Adicional adicional = service.find(id);
		
		if(adicional != null){
			return ResponseEntity.ok().body(adicional);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		adicionalDAO.delete(id);
	}	
}