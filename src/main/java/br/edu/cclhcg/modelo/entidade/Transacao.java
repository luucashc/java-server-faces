package br.edu.cclhcg.modelo.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_TRANSACAO")
@NamedQuery(name = "Transacao.ListarTodos", query ="SELECT t FROM Transacao t")
public class Transacao implements Serializable {

	/**  **/
	private static final long serialVersionUID = 4759613309663352995L;
	
	@Id
	@SequenceGenerator(name = "SEQUENCIAL_TRANSACAO", sequenceName = "NUM_SEQ_TRAN", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCIAL_TRANSACAO")
	@Column(name = "NUM_SEQ_TRAN")
	private Long id;
	
	@NotNull
	@ManyToOne
	private Cartao cartao;
	
	@NotEmpty
	private String tipo_transacao;
	
	@NotNull
	private Double valor_transacao;
	
	@NotNull
	private Date data;
	
	@NotEmpty
	private String credor;
	
	
	/** Get set **/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public String getTipo_transacao() {
		return tipo_transacao;
	}

	public void setTipo_transacao(String tipo_transacao) {
		this.tipo_transacao = tipo_transacao;
	}
	
	public Double getValor_transacao() {
		return valor_transacao;
	}

	public void setValor_transacao(Double valor_transacao) {
		this.valor_transacao = valor_transacao;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	/**  **/
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
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
