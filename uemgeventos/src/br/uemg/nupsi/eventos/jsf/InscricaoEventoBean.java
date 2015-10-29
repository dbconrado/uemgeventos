package br.uemg.nupsi.eventos.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.uemg.nupsi.eventos.modelo.Atividade;
import br.uemg.nupsi.eventos.modelo.Evento;

@ManagedBean
@ViewScoped
public class InscricaoEventoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer eventoId;
	private Atividade atividade;
	private List<Evento> eventos;
	private List<Atividade> atividades;
	
	@PersistenceContext
	private EntityManager em;

	public void carregarAtividades() {
		System.out.println("OIIIII id = " + eventoId);
		atividades = new ArrayList<>();
		atividades = em.createQuery("SELECT a FROM Atividade a"
				+ " WHERE a.evento.id = :id"
				+ " ORDER BY a.nome", Atividade.class)
				.setParameter("id", eventoId)
				.getResultList();	
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

	public Integer getEventoId() {
		return eventoId;
	}

	public void setEventoId(Integer eventoId) {
		atividades = null;
		this.eventoId = eventoId;
	}
	
}
