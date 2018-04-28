package br.com.projeto.comanda.repositories.produto;

import java.util.List;

import br.com.projeto.comanda.domain.Produto;

public interface ProdutoRepositoryQuery {

	public List<Produto> findByCategory(Long idCategoria);
	
}
