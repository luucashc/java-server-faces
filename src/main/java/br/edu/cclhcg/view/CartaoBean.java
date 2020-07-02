package br.edu.cclhcg.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.edu.cclhcg.modelo.entidade.Cartao;
import br.edu.cclhcg.modelo.servico.ServicoCartao;

/**
 * Bean para fazer as operações com os cartoes.
 * 
 * @author LucasCardoso
 *
 */
@Named
@ViewScoped
public class CartaoBean implements Serializable {

	/**  **/
	private static final long serialVersionUID = -1834634843005216740L;
	
	private Cartao cartao;
	
	private String cpf;
	
	private String numExibir;
	private String cvvExibir;
	private String nomeExibir;
	private String dataExibir;
	
	private List<Cartao> cartoes;
	
	/**  **/
	@Inject
	private ServicoCartao servicoCartao;
	
	/**  **/
	public CartaoBean() {
		this.cartao = new Cartao();
	}
	
	/**  **/
	private void atualizarCartoes() {
		this.cartoes = this.servicoCartao.listar();
	}
	
	@PostConstruct
	public void init() {
		this.atualizarCartoes();
	}
	
	/**  **/
	public void cadastrar() {
		try {
			this.servicoCartao.cadastrar(this.cartao);
			this.atualizarCartoes();
			setNumExibir(cartao.getNumero());
			setCvvExibir(cartao.getCvv());
			setNomeExibir(cartao.getCliente().getNome());
			setDataExibir(cartao.getVencimento());
			this.cartao = new Cartao();
			Messages.addFlashGlobalInfo("Cartão cadastrado!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void buscarCartoesCpf() {
		this.cartoes = this.servicoCartao.buscarCartoes(this.cpf);
	}
	
	/** Get e set **/
	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumExibir() {
		return numExibir;
	}

	public void setNumExibir(String numExibir) {
		this.numExibir = numExibir;
	}

	public String getCvvExibir() {
		return cvvExibir;
	}

	public void setCvvExibir(String cvvExibir) {
		this.cvvExibir = cvvExibir;
	}

	public String getNomeExibir() {
		return nomeExibir;
	}

	public void setNomeExibir(String nomeExibir) {
		this.nomeExibir = nomeExibir;
	}

	public String getDataExibir() {
		return dataExibir;
	}

	public void setDataExibir(String dataExibir) {
		this.dataExibir = dataExibir;
	}
}
