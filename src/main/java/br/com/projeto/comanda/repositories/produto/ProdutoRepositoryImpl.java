package br.com.projeto.comanda.repositories.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.projeto.comanda.domain.Pedido;
import br.com.projeto.comanda.domain.Produto;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Produto> findByCategory(Long idCategoria) {
		List<Produto> produtos = null;
		try {
			produtos = em.createQuery("select * from Produto p WHERE p.id_categoria= :idCategoria", Produto.class)
					.setParameter("idCategoria", idCategoria).getResultList();
		} catch (NoResultException e) {
			throw new ObjectNotFoundException(Pedido.class.getSimpleName() + " não encontrado! Id: " + idCategoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}

}
