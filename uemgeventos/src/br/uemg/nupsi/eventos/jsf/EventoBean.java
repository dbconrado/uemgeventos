package br.uemg.nupsi.eventos.jsf;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@RequestScoped
public class EventoBean {
	
	@PersistenceContext
	private EntityManager em;
	
	private List<Evento> eventos;
	
	public List<Evento> getEventos() {
		
		if (eventos == null)
			
			eventos = em.createQuery("SELECT e FROM Evento e ORDER BY e.dataInicio DESC", Evento.class)
				.getResultList();
		
		return eventos;
		
	}

}
