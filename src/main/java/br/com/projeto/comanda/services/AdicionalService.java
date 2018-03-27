package br.com.projeto.comanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.comanda.domain.Adicional;
import br.com.projeto.comanda.repositories.AdicionalRepository;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

@Service
public class AdicionalService {

	@Autowired
	private AdicionalRepository adicionalrepository;
	
	public Adicional find(Long id) {
		Adicional adicional = adicionalrepository.findOne(id);
		if (adicional == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Adicional.class.getName());
		}
		return adicional;
	}
	
}
