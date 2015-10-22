package br.uemg.nupsi.eventos.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.uemg.nupsi.eventos.modelo.Atividade;
import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@ViewScoped
public class InscricaoEventoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Evento evento;
	private Atividade atividade;
	private List<Evento> eventos;
	private List<Atividade> atividades;
	
	@PersistenceContext
	private EntityManager em;

	public InscricaoEventoBean() {
	}	

	public void carregarAtividades() {
		System.out.println("OIIIIIIIIIIIIIIIIIIIII");
		atividades = new ArrayList<>();
		atividades = em.createQuery("SELECT a FROM Atividade a"
				+ " WHERE a.evento_id = " + evento.getId()
				+ " ORDER BY a.nome", Atividade.class)
				.getResultList();	
	}

	public void setEvento(Evento evento) {
		System.out.println("evento mudou: " + evento.getId());
		this.evento = evento;
		atividades = null;
	}

	public Evento getEvento() {
		return evento;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<Evento> getEventos() {
		if (eventos == null)			
			eventos = em.createQuery("SELECT e FROM Evento e ORDER BY e.nome", Evento.class)
				.getResultList();
		
		return eventos;		
	}

	public List<Atividade> getAtividades() {
		if (atividades == null)
			carregarAtividades();
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
}
