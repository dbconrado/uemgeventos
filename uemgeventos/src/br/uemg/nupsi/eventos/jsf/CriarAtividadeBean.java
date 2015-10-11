package br.uemg.nupsi.eventos.jsf;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.uemg.nupsi.eventos.modelo.Atividade;
import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@ViewScoped
public class CriarAtividadeBean {

	private QueryIdUtil<Evento> eventoUtil;
	private Evento evento;
	
	private Atividade atividade;
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	@PostConstruct
	public void init() {
		eventoUtil = new QueryIdUtil<>(em, Evento.class);
	}
	
	/**
	 * vai ser executado na inicialização da pagina
	 */
	public void carregarEvento() {
		
		evento = eventoUtil.getEntidadeOrThrow();
		atividade = new Atividade(evento);
		
	}
	
	public String salvar() {
		
		try {
			utx.begin();
			em.persist(atividade);
			utx.commit();
			
			FacesUtil.addMessage("Atividade criada!");
			FacesUtil.keepMessages();
			
			return "admatividade?faces-redirect=true&atividade_id=" + atividade.getId();
			
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			
			final String msg = "Erro ao criar atividade";
			FacesUtil.logException(msg, e);
			Logger.getGlobal().log(Level.SEVERE, msg, e);
			return null;
			
		}
	}

	public QueryIdUtil<Evento> getEventoUtil() {
		return eventoUtil;
	}

	public void setEventoUtil(QueryIdUtil<Evento> eventoUtil) {
		this.eventoUtil = eventoUtil;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	
	
}
