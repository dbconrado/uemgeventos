package br.uemg.nupsi.eventos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.uemg.nupsi.eventos.modelo.Evento;

@FacesConverter(forClass = Evento.class)
public class EventoConverter implements Converter {
	EntityManager em;
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
		if(s == null || s.isEmpty()){
	       return null;
		}else{
            int id = Integer.parseInt(s);
            System.out.println("id = " + id);
            Evento evento = em.createQuery("SELECT e FROM Evento e"
            		+ "WHERE e.id = " + id, Evento.class).getSingleResult();
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
