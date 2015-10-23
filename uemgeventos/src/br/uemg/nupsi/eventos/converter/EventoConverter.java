package br.uemg.nupsi.eventos.converter;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.uemg.nupsi.eventos.jsf.QueryIdUtil;
import br.uemg.nupsi.eventos.modelo.Evento;

@FacesConverter(forClass = Evento.class)
public class EventoConverter implements Converter {
	@PersistenceContext
	private EntityManager em;
	
	private Evento evento = new Evento();
	private QueryIdUtil<Evento> eventoUtil;
	
	@PostConstruct
	public void init(){
		eventoUtil = new QueryIdUtil<>(em, Evento.class);
	}
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
		if (s == null || s.isEmpty()) {
			return null;
		} else {
			Integer id = Integer.parseInt(s);
			System.out.println("id = " + id);
			eventoUtil.setId(id);
			evento = eventoUtil.getEntidadeOrThrow();
//			evento = em.createQuery("SELECT e FROM Evento e WHERE e.id = :id", Evento.class)
//					.setParameter("id", id)
//					.getSingleResult();
			return evento;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
		if (o instanceof Evento) {
			Evento evento = (Evento) o;
			return evento.getId() + "";
		}
		return "";
	}
}
