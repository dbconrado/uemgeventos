package br.uemg.nupsi.eventos.jsf;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.uemg.nupsi.eventos.modelo.Atividade;
import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@RequestScoped
public class AdmEventoBean {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Vem por viewParam.
	 */
	private Integer idEvento;
	
	private Evento evento;
	private List<Atividade> atividades;
	
	public Evento getEvento() {
		
		if (evento == null && idEvento != null)
			
			evento = em.find(Evento.class, idEvento);
		
		return evento;
	}

	public List<Atividade> getAtividades() {
		
		if (atividades == null && idEvento != null)
			
			atividades = em.createQuery("SELECT a FROM Atividade a WHERE a.evento.id = :id", Atividade.class)
				.setParameter("id", idEvento)
				.getResultList();
		
		return atividades;
	}
	
	
	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	
	
	
}
