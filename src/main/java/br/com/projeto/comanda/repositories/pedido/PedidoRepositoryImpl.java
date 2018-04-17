package br.com.projeto.comanda.repositories.pedido;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.projeto.comanda.domain.Pedido;
import br.com.projeto.comanda.repositories.PedidoRepository;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

public class PedidoRepositoryImpl implements PedidoRepositoryQuery {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PedidoRepository repo;

	@Override
	public Pedido pesquisaPedidoPorId(Long id) {
		Pedido pedido = null;
		try {
			TypedQuery<Pedido> query = em.createQuery("select distinct(p) from Pedido p WHERE p.id= :id", Pedido.class);
			query.setParameter("id", id);
			pedido = query.getSingleResult();
		} catch (NoResultException e) {
			throw new ObjectNotFoundException(Pedido.class.getSimpleName() + " não encontrado! Id: " + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}

	@Override
	public List<Pedido> pesquisaTodosPedidos() {
		List<Pedido> pedidos = null;
		try {
			pedidos = em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
			if (pedidos == null || pedidos.isEmpty()) {
				throw new ObjectNotFoundException(Pedido.class.getSimpleName() + " não encontrados.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}

	public Pedido salvarPedidoCompleto(Pedido pedido) {
		try {
			repo.save(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;

	}

	public Long buscaUltimoPedidoSalvo() {
		Long id = null;
		try {
			TypedQuery<Pedido> query = em.createQuery("select max(p.id) from Pedido p", Pedido.class);
			id = (long) query.getFirstResult();
		} catch (NoResultException e) {
			throw new ObjectNotFoundException(Pedido.class.getSimpleName() + "Erro ao busca ultimo pedido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
