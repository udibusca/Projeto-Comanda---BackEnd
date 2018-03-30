package br.com.projeto.comanda.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.comanda.domain.ItemPedido;
import br.com.projeto.comanda.domain.Mesa;
import br.com.projeto.comanda.domain.Pedido;
import br.com.projeto.comanda.domain.Usuario;
import br.com.projeto.comanda.domain.enums.StatusPedido;
import br.com.projeto.comanda.repositories.ItemPedidoRepository;
import br.com.projeto.comanda.repositories.PedidoRepository;
import br.com.projeto.comanda.repositories.ProdutoRepository;
import br.com.projeto.comanda.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private ProdutoRepository repoproduto;

	@Autowired
	private ItemPedidoRepository repoitempedido;

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

		if (pedido == null) {
			throw new ObjectNotFoundException("Não é possivel salvar o pedido");
		}
		pedido.setId(null);
		double valortotal = calcularTotal(pedido);
		int estado = StatusPedido.PENDENTE.ordinal();
		java.util.Date data = new java.util.Date();
		java.sql.Date dataatual = new java.sql.Date(data.getTime());
		Usuario usuario = pedido.getUsuario();
		Mesa mesa = pedido.getMesa();
		Pedido pedidosalva = new Pedido(valortotal, estado, dataatual, usuario, mesa);
		repo.salvarPedidoCompleto(pedidosalva);

		if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
			throw new ObjectNotFoundException("Pedido não tem itens para serrem salvos");
		}

		for (ItemPedido itemS : pedido.getItens()) {

			itemS.setProduto(repoproduto.findOne(itemS.getProduto().getId()));
			itemS.setPedido(repo.findOne(pedidosalva.getId()));
			itemS.setQuantidade(itemS.getQuantidade());
			itemS.setValorunitario(itemS.getProduto().getValor());
			itemS.setValortotal(itemS.getProduto().getValor() * itemS.getQuantidade());
			itemS.setObservacao(itemS.getObservacao());

		}
		repoitempedido.save(pedido.getItens());

		return pedido;

	}

	private double calcularTotal(Pedido pedido) {
		double valorTotal = 0.00;
		for (ItemPedido itemS : pedido.getItens()) {
			valorTotal += itemS.getValortotal();
		}
		return valorTotal;
	}	
	
}
