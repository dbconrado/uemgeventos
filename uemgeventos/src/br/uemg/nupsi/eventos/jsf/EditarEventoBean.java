package br.uemg.nupsi.eventos.jsf;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@ViewScoped
public class EditarEventoBean {

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;
	
	private Integer idEvento;
	private Evento evento;
	
	public String salvar() {
		
		try {
			ut.begin();
			evento = em.merge(evento);
			ut.commit();
			
			return "/admin/admevento?faces-redirect=true&evento_id=" + idEvento;
			
		} catch (Exception e) {
			FacesUtil.logException("Erro ao salvar", e);
		}
		
		return null;
	}
	
	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public Evento getEvento() {
		if (evento == null) {
			if (idEvento == null)
				throw new IllegalStateException("Evento nao foi informado.");
			
			evento = em.find(Evento.class, idEvento);
			
			if (evento == null)
				throw new IllegalArgumentException("Evento não encontrado.");
		}
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
