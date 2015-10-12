package br.uemg.nupsi.eventos.jsf;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@ViewScoped
public class EditarEventoBean{

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;
	
	private Evento evento;
	
	private QueryIdUtil<Evento> eventoUtil;
	
	@PostConstruct
	public void init() {
		eventoUtil = new QueryIdUtil<>(em, Evento.class);
	}
	
	public void carregarEvento() {
		evento = eventoUtil.getEntidadeOrThrow();
	}
	
	public String salvar() {
		
		try {
			ut.begin();
			evento = em.merge(evento);
			ut.commit();
			
			FacesUtil.addMessage("Evento alteado!");
			FacesUtil.keepMessages();
			
			return "/admin/admevento?faces-redirect=true&evento_id=" + eventoUtil.getId();
			
		} catch (Exception e) {
			FacesUtil.logException("Erro ao salvar", e);
		}
		
		return null;
	}
	
	public Integer getIdEvento() {
		return eventoUtil.getId();
	}

	public void setIdEvento(Integer idEvento) {
		eventoUtil.setId(idEvento);
	}

	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
