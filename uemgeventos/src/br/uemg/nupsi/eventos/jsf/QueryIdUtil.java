package br.uemg.nupsi.eventos.jsf;

import javax.persistence.EntityManager;

/**
 * Classe auxiliar que lida com um parametro de id
 * proveniente da query string.
 * Caso o id informado for inv�lido,
 * o objeto lan�a uma exce��o que provavelmente
 * redirecionar� � p�gina de erro.
 * 
 * O id deve ser informado via viewParam
 * @author DB
 *
 */
public class QueryIdUtil<T> {
	private EntityManager em;
	private Integer id;
	private T entidade;
	private Class<T> clazz;
	
	public QueryIdUtil(EntityManager em, Class<T> clazz) {
		this.em = em;
		this.clazz = clazz;
	}

	public T getEntidadeOrThrow() {
		if (entidade == null) {
			if (id == null)
				throw new IllegalStateException("O id nao foi informado.");
			
			entidade = em.find(clazz, id);
			
			if (entidade == null)
				throw new IllegalArgumentException("O id "+id+" n�o foi encontrado.");
		}
		return entidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id != this.id)
			entidade = null;
		
		this.id = id;
	}
	
	
}
