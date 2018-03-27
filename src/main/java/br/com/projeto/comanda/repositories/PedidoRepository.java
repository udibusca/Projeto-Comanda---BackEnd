package br.com.projeto.comanda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.comanda.domain.Pedido;
import br.com.projeto.comanda.repositories.pedido.PedidoRepositoryQuery;

public interface PedidoRepository extends JpaRepository<Pedido, Long>,PedidoRepositoryQuery {

}
