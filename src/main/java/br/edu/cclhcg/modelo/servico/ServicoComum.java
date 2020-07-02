package br.edu.cclhcg.modelo.servico;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Classe abstrata, não pode ser instanciada e serve como uma classe pai **/
public abstract class ServicoComum<T> {
	
	@PersistenceContext
	protected EntityManager em;
	
	/**  **/
	protected Class<T> tipoGenericoT;
	
	public ServicoComum() {
		
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		ParameterizedType tiposGenericos = (ParameterizedType) getClass().getGenericSuperclass();
		Type[] tipos = tiposGenericos.getActualTypeArguments();
		this.tipoGenericoT = (Class<T>) tipos[0];
	}
	
	/**  **/
	public T cadastrar(T t) throws Exception {
		validarCadastramento(t);
		em.persist(t);
		return t;
	}
	
	public T editar(T t) {
		return em.merge(t);
	}
	
	public void excluir(T t) throws Exception {
		validarExclusao(t);
		em.remove(em.merge(t));
	}
	
	/**  **/
	public abstract void validarCadastramento(T t) throws Exception;
	
	public abstract void validarEdição(T t) throws Exception;
	
	public abstract void validarExclusao(T t) throws Exception;
}
