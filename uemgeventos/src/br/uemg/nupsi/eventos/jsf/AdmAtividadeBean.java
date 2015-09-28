package br.uemg.nupsi.eventos.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.uemg.nupsi.eventos.modelo.Atividade;
import br.uemg.nupsi.eventos.modelo.Ocorrencia;

@ManagedBean
@RequestScoped
public class AdmAtividadeBean {

	private QueryIdUtil<Atividade> atividadeUtil;
	private Atividade atividade;
	private List<Ocorrencia> ocorrencias;
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		atividadeUtil = new QueryIdUtil<>(em, Atividade.class);
	}
	
	public void carregarAtividade() {
		
		atividade = atividadeUtil.getEntidadeOrThrow();
		
	}
	
	public List<Ocorrencia> getOcorrencias() {
		if (ocorrencias == null && atividadeUtil.getId() != null)
			
			ocorrencias = em.createQuery("SELECT o FROM Ocorrencia o WHERE o.atividade.id = :id", Ocorrencia.class)
				.setParameter("id", atividadeUtil.getId())
				.getResultList();

		return ocorrencias;
	}
	
	public Integer getIdAtividade() {
		return atividadeUtil.getId();
	}
	public void setIdAtividade(Integer idAtividade) {
		atividadeUtil.setId(idAtividade);
	}
	public QueryIdUtil<Atividade> getAtividadeUtil() {
		return atividadeUtil;
	}
	public void setAtividadeUtil(QueryIdUtil<Atividade> atividadeUtil) {
		this.atividadeUtil = atividadeUtil;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
}
