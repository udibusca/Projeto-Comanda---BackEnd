package br.com.projeto.comanda.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "web_item_pedido")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "valor_unitario")
	private Double valorunitario;

	@Column(name = "valor_total")
	private Double valortotal;

	@Column(name = "observacao")
	private String observacao;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto")
	private Produto produto;

	public ItemPedido() {

	}

	/**
	 * @param quantidade
	 * @param valorunitario
	 * @param valortotal
	 * @param observacao
	 * @param pedido
	 * @param produto
	 */
	public ItemPedido(Integer quantidade, Double valorunitario, Double valortotal, String observacao, Pedido pedido,
			Produto produto) {
		super();
		this.quantidade = quantidade;
		this.valorunitario = valorunitario;
		this.valortotal = valortotal;
		this.observacao = observacao;
		this.pedido = pedido;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorunitario() {
		return valorunitario;
	}

	public void setValorunitario(Double valorunitario) {
		this.valorunitario = valorunitario;
	}

	public Double getValortotal() {
		return valortotal;
	}

	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", quantidade=" + quantidade + ", valorunitario=" + valorunitario
				+ ", valortotal=" + valortotal + ", observacao=" + observacao + ", pedido=" + pedido + ", produto="
				+ produto + "]";
	}

}
