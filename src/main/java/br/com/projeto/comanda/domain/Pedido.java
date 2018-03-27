package br.com.projeto.comanda.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "web_pedido")
public class Pedido  implements Serializable {
	private static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "valor_total")
	private Double valortotal;

	@NotNull
	private Integer estado;

	@Column(name = "data_pedido")
	private Date datapedido;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_mesa")
	private Mesa mesa;
	
	@JsonIgnore
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();

	public Pedido() {

	}

	/**
	 * @param valortotal
	 * @param estado
	 * @param datapedido
	 * @param usuario
	 * @param mesa
	 * @param itensPedido
	 */
	public Pedido(Double valortotal, Integer estado, Date datapedido, Usuario usuario, Mesa mesa,
			List<ItemPedido> itensPedido) {
		super();
		this.valortotal = valortotal;
		this.estado = estado;
		this.datapedido = datapedido;
		this.usuario = usuario;
		this.mesa = mesa;
		this.itensPedido = itensPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValortotal() {
		return valortotal;
	}

	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getDatapedido() {
		return datapedido;
	}

	public void setDatapedido(Date datapedido) {
		this.datapedido = datapedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", valortotal=" + valortotal + ", estado=" + estado + ", datapedido=" + datapedido
				+ ", usuario=" + usuario + ", mesa=" + mesa + ", itensPedido=" + itensPedido + "]";
	}

}
