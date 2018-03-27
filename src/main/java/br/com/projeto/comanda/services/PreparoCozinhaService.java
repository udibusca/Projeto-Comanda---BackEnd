package br.com.projeto.comanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.comanda.domain.PreparoCozinha;
import br.com.projeto.comanda.repositories.PreparoCozinhaRepository;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

@Service
public class PreparoCozinhaService {

	@Autowired
	private PreparoCozinhaRepository repo;

	public PreparoCozinha find(Long id) {
		PreparoCozinha obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + PreparoCozinha.class.getName());
		}
		return obj;
	}

}
