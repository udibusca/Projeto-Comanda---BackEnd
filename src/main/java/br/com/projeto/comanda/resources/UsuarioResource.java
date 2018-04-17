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

import br.com.projeto.comanda.domain.Usuario;
import br.com.projeto.comanda.repositories.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioDAO;

	@PostMapping
	public Usuario criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalva = usuarioDAO.save(usuario);
		return usuarioSalva;
	}

	@GetMapping
	public List<Usuario> listar() {
		return usuarioDAO.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long id) {

		Usuario usuario = usuarioDAO.findOne(id);

		if (usuario != null) {
			return ResponseEntity.ok().body(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		usuarioDAO.delete(id);
	}
}