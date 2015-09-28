package br.uemg.nupsi.eventos.jsf;

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
import br.uemg.nupsi.eventos.modelo.Ocorrencia;

@ManagedBean
@ViewScoped
public class CriarOcorrenciaBean {

	private QueryIdUtil<Atividade> atividadeUtil;
	private Atividade atividade;
	private Ocorrencia ocorrencia;
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	@PostConstruct
	public void init() {
		atividadeUtil = new QueryIdUtil<>(em, Atividade.class);
	}
	
	public void carregarAtividade() {
		atividade = atividadeUtil.getEntidadeOrThrow();
		ocorrencia = new Ocorrencia(atividade);
	}
	
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public Atividade getAtividade() {
		return atividade;
	}
	
	public Integer getIdAtividade() {
		return atividadeUtil.getId();
	}
	
	public void setIdAtividade(Integer id) {
		atividadeUtil.setId(id);
	}
	
	public String salvar() {
		try {
			utx.begin();
			em.persist(ocorrencia);
			utx.commit();
			
			FacesUtil.addMessage("Ocorrência criada!");
			FacesUtil.keepMessages();
			
			return "admatividade?faces-redirect=true&atividade_id=" + atividade.getId();
			
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			FacesUtil.logException("Erro ao salvar ocorrencia", e);
			return null;
		}
	}
}
