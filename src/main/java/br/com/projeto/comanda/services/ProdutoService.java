package br.com.projeto.comanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.comanda.domain.Produto;
import br.com.projeto.comanda.repositories.ProdutoRepository;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtorepository;
	
	public Produto find(Long id) {
		Produto produto = produtorepository.findOne(id);
		if (produto == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName());
		}
		return produto;
	}	
	
}
