package br.edu.cclhcg.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Messages;
import br.edu.cclhcg.modelo.entidade.Transacao;
import br.edu.cclhcg.modelo.servico.ServicoTransacao;

/**
 * Bean para fazer as transações com os cartões.
 * 
 * @author LucasCardoso
 *
 */
@Named
@ViewScoped
public class TransacaoBean implements Serializable {

	/**  **/
	private static final long serialVersionUID = 3505412485115315635L;
	
	/** Declarando valores soltos para manipulação do que vem do front **/
	private Transacao transacao;
	
	@NotEmpty
	private String numero;
	
	@NotEmpty
	private String cvv;
	
	@NotEmpty
	private String tipo_transacao;
	
	@NotNull
	private Date data;
	
	@NotNull
	private Double valor_transacao;
	
	@NotEmpty
	private String credor;
	
	private List<Transacao> transacoes;
		
	private String numCartao;
	
	/**  **/
	@Inject
	private ServicoTransacao servicoTransacao;
	
	/**  **/
	public TransacaoBean() {
		this.transacao = new Transacao();
	}
	
	/**  **/
	@PostConstruct
	public void init() {
		this.atualizarTransacoes();
	}
	
	/**  **/
	private void atualizarTransacoes() {
		this.transacoes = this.servicoTransacao.listar();
	}
	
	/**  **/
	public void cadastrar() {
		try {
			this.servicoTransacao.cadastrar(numero, cvv, tipo_transacao, data, valor_transacao, credor);
			numero = null;
			cvv = null;
			tipo_transacao = null;
			valor_transacao = null;
			credor = null;
			this.transacao = new Transacao();
			this.atualizarTransacoes();
			Messages.addFlashGlobalInfo("Transação realizada com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void buscarTransacoesCartao() {
		this.transacoes = this.servicoTransacao.buscarTransacoes(this.numCartao);
	}

	/** Get e set **/
	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	/**  Get e set dos valores que estão vindo do front soltos para a manipulação **/
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getTipo_transacao() {
		return tipo_transacao;
	}

	public void setTipo_transacao(String tipo_transacao) {
		this.tipo_transacao = tipo_transacao;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor_transacao() {
		return valor_transacao;
	}

	public void setValor_transacao(Double valor_transacao) {
		this.valor_transacao = valor_transacao;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
}
