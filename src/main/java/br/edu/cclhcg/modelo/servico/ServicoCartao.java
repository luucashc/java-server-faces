package br.edu.cclhcg.modelo.servico;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.edu.cclhcg.modelo.entidade.Cartao;

/**
 * Classe para operar percistencia
 * 
 * @author LucasCardoso
 *
 */
@Stateless
public class ServicoCartao extends ServicoComum<Cartao> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void validarCadastramento(Cartao t) throws Exception {
		// TODO Auto-generated method stub
		
		t.setNumero(gerarNumero());
		t.setCvv(cvv());
		t.setVencimento(this.dtVencimento());
		t.setData_criacao(this.dtCriacao());
	}

	@Override
	public void validarEdição(Cartao t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validarExclusao(Cartao t) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**  **/
	public List<Cartao> listar() {
		return em.createNamedQuery("Cartao.ListarTodos", Cartao.class).getResultList();
	}
	
	/**  **/
	public String gerarNumero() {
		/** objeto java para conctenar stings **/
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 4; i++) {
			sb.append(numeroCartao());
			
			if ( i < 3) {
				sb.append(" ");
			}
		}
		
		return sb.toString();
	}
	
	/**  **/
	public String numeroCartao() {
		Double numero = Math.random();
		
		numero = numero * 9999;
		
		Integer numeroPreparado = numero.intValue();
		
		DecimalFormat df = new DecimalFormat("0000");
		
		String numeroFormatado = df.format(numeroPreparado);
		
		return numeroFormatado;
	}
	
	/**  **/
	public String cvv() {
		return String.format("%03d", new Random().nextInt(999) + 1);
	}
	
	/**  **/
	public String dtVencimento() {
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 5);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		
		return formatter.format(calendar.getTime());
	}
	
	/**  **/
	public String dtCriacao() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return formatter.format(date);
	}
	
	
	/** Relatorio **/
	public List<Cartao> buscarCartoes(String cpf)  {
		TypedQuery<Cartao> query = em.createQuery("SELECT ct FROM Cartao ct WHERE ct.cliente.cpf =:p1", Cartao.class).setParameter("p1", cpf);
		
		return query.getResultList();
	}
}
