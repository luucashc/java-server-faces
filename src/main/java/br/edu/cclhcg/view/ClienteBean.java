package br.edu.cclhcg.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.edu.cclhcg.modelo.entidade.Cliente;
import br.edu.cclhcg.modelo.servico.ServicoCliente;

/**
 * Bean para fazer as operações com os clientes.
 * 
 * @author LucasCardoso
 *
 */
@Named
@ViewScoped
public class ClienteBean implements Serializable {

	/**  **/
	private static final long serialVersionUID = -6941546675778813466L;

	private Cliente cliente;
	
	private String cpf;
	
	private List<Cliente> clientes;
	
	@Inject
	private ServicoCliente servicoCliente;
	
	/**  **/
	public ClienteBean() {
		this.cliente = new Cliente();
	}
	
	@PostConstruct
	public void init() {
		this.atualizarClientes();
	}

	/**  **/
	private void atualizarClientes() {
		this.clientes = this.servicoCliente.listar();
	}
	
	/**  **/
	public void cadastrar() {
		try {
			this.servicoCliente.cadastrar(cliente);
			this.cliente = new Cliente();
			this.atualizarClientes();
			Messages.addGlobalInfo("Cliente cadastrado!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**  **/
	public void editar() {
		try {
			this.servicoCliente.editar(this.cliente);
			this.cliente = new Cliente();
			this.atualizarClientes();
			Messages.addGlobalInfo("Cliente atualizado com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**  **/
	public void paraEditar(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**  **/
	public void cancelarEdicao() {
		this.cliente = new Cliente();
	}
	
	/**  **/
	public void excluir(Cliente cliente) {
		try {
			this.servicoCliente.excluir(cliente);
			this.atualizarClientes();
			Messages.addGlobalInfo("Cliente excluido");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void buscarClienteCpf() {
		this.clientes = this.servicoCliente.buscarClienteCpf(this.cpf);
	}
	
	/** Get e set **/
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
