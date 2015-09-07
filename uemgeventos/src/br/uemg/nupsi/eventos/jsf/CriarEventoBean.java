package br.uemg.nupsi.eventos.jsf;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@RequestScoped
public class CriarEventoBean {

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;
	
	private Evento evento = new Evento();

	public String salvar() {
		
		try {
			ut.begin();
			em.persist(evento);
			ut.commit();
			
			FacesUtil.addMessage("Evento cadastrado!");
			FacesUtil.keepMessages();
			
			return "/admin/admevento?faces-redirect=true&evento_id=" + evento.getId();
			
		} catch (Exception e) {
			
			FacesUtil.logException("Erro!", e);
			
		}
		
		return null;
		
	}
	
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
}
