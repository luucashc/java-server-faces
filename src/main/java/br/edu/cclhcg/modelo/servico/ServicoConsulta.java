package br.edu.cclhcg.modelo.servico;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.cclhcg.modelo.dto.SumarioTransacao;

@Stateless
public class ServicoConsulta {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<SumarioTransacao> consultarUnicoCartao(String numCartao, Date dataPesquisaInicial, Date dataPesquisaFinal) {
		Query query = em.createQuery("SELECT NEW br.edu.cclhcg.modelo.dto.SumarioTransacao(COUNT(t), SUM(t.valor_transacao), t.tipo_transacao) FROM Transacao t WHERE t.cartao.numero = :p1 AND t.data BETWEEN :p2 AND :p3 GROUP BY t.tipo_transacao");
		query.setParameter("p1", numCartao);
		query.setParameter("p2", dataPesquisaInicial);
		query.setParameter("p3", dataPesquisaFinal);
		@SuppressWarnings("unchecked")
		List<SumarioTransacao> resultado = query.getResultList();
		return resultado;
	}
	
	public List<SumarioTransacao> consultarCartoesCpf(String cpf, Date dataPesquisaInicial, Date dataPesquisaFinal) {
		Query query = em.createQuery("SELECT NEW br.edu.cclhcg.modelo.dto.SumarioTransacao(COUNT(t), SUM(t.valor_transacao), t.tipo_transacao) FROM Transacao t WHERE t.cartao.cliente.cpf = :p1 AND t.data BETWEEN :p2 AND :p3 GROUP BY t.tipo_transacao");
		query.setParameter("p1", cpf);
		query.setParameter("p2", dataPesquisaInicial);
		query.setParameter("p3", dataPesquisaFinal);
		@SuppressWarnings("unchecked")
		List<SumarioTransacao> resultado = query.getResultList();
		return resultado;
	}
	
	public List<SumarioTransacao> consultarTransacoesCredor(String credor, Date dataPesquisaInicial, Date dataPesquisaFinal) {
		Query query = em.createQuery("SELECT NEW br.edu.cclhcg.modelo.dto.SumarioTransacao(COUNT(t), SUM(t.valor_transacao), t.tipo_transacao) FROM Transacao t WHERE t.credor = :p1 AND t.data BETWEEN :p2 AND :p3 GROUP BY t.tipo_transacao");	
		query.setParameter("p1", credor);
		query.setParameter("p2", dataPesquisaInicial);
		query.setParameter("p3", dataPesquisaFinal);
		@SuppressWarnings("unchecked")
		List<SumarioTransacao> resultado = query.getResultList();
		return resultado;
	}
}
