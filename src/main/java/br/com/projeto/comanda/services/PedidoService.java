package br.com.projeto.comanda.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.comanda.domain.Pedido;
import br.com.projeto.comanda.repositories.PedidoRepository;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PedidoRepository repo;

	public Pedido pesquisaPedidoPorId(Long id) {
		Pedido retornoPedido = repo.pesquisaPedidoPorId(id);
		logger.info("Pedido -> {}", retornoPedido);
		return retornoPedido;

	}

	public List<Pedido> pesquisaTodosPedidos() {
		List<Pedido> retornoPedido = repo.pesquisaTodosPedidos();
		return retornoPedido;
	}

	public Pedido salvarPedido(Pedido pedido) {
		try {
			System.err.println("salvar pedido criar metodo de salvar o pedido");

		} catch (Exception e) {
			throw new ObjectNotFoundException("Objeto não encontrado! : " + Pedido.class.getName());
		}
		return pedido;
	}

}
