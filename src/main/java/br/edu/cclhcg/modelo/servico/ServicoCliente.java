package br.edu.cclhcg.modelo.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.cclhcg.modelo.entidade.Cliente;

/**
 * Classe para operar percistencia
 * 
 * @author LucasCardoso
 *
 */

/** Injeção de dependencia **/
@Stateless
public class ServicoCliente extends ServicoComum<Cliente>{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void validarCadastramento(Cliente t) throws Exception {
		// TODO Auto-generated method stub
		
		Cliente clienteCadastrado = buscaCpf(t.getCpf());
		
		if (clienteCadastrado != null) { 
			throw new Exception("CPF já cadastrado!");
		}
	}
	
	/**  **/
	@Override
	public void validarEdição(Cliente t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**  **/
	@Override
	public void validarExclusao(Cliente t) throws Exception {
		// TODO Auto-generated method stub
		Long qtdCartoes = this.buscarQtdCartoes(t);
		
		if(qtdCartoes > 0) {
			throw new Exception("Não é possivel excluir um cliente com cartão cadastrado");
		}
	}
	
	
	/** Busca cpf para ver se já está cadastrado **/
	public Cliente buscaCpf(String cpf) {
		Query query = em.createQuery("FROM Cliente c WHERE c.cpf = :p1").setParameter("p1", cpf);
		try {
			return (Cliente) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Long buscarQtdCartoes(Cliente cliente)  {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(ct) FROM Cartao ct WHERE ct.cliente =:p1", Long.class).setParameter("p1", cliente);
		
		return query.getSingleResult();
	}
	
	/**  **/
	public List<Cliente> listar() {
		return em.createNamedQuery("Cliente.ListarTodos", Cliente.class).getResultList();
	}
	
	
	/** Relatorio **/
	public List<Cliente> buscarClienteCpf(String cpf) {
		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :p1", Cliente.class).setParameter("p1", cpf);
			
		return query.getResultList();
	}
}