package br.com.projeto.comanda.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.comanda.domain.ItemPedido;
import br.com.projeto.comanda.domain.Pedido;
import br.com.projeto.comanda.domain.Produto;
import br.com.projeto.comanda.domain.enums.StatusPedido;
import br.com.projeto.comanda.repositories.ItemPedidoRepository;
import br.com.projeto.comanda.repositories.PedidoRepository;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private ItemPedidoRepository itempedidorepo;

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
			double valor = 0.0;

			pedido.setEstado(StatusPedido.PENDENTE);
			pedido.setMesa(pedido.getMesa());
			pedido.setUsuario(pedido.getUsuario());
			pedido.setValor(valor);

			repo.salvarPedidoCompleto(pedido);			
			for (ItemPedido item : pedido.getItens()) {

				Produto produtoGet = item.getId().getProduto();
				item.setQuantidade(item.getQuantidade());
				item.setObservacao(item.getObservacao());
			

				//pedido.setValor(produtoGet.getValor() * item.getQuantidade());
				ItemPedido itempedido = new ItemPedido(pedido, produtoGet, item.getQuantidade(), item.getObservacao());
				itempedidorepo.save(itempedido);
			}

		} catch (Exception e) {
			throw new ObjectNotFoundException("Objeto não encontrado! : " + Pedido.class.getName());
		}
		return pedido;
	}

}
