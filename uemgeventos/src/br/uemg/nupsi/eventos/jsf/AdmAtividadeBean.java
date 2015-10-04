package br.uemg.nupsi.eventos.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.uemg.nupsi.eventos.modelo.Atividade;
import br.uemg.nupsi.eventos.modelo.Ocorrencia;

@ManagedBean
@ViewScoped
public class AdmAtividadeBean {

	private QueryIdUtil<Atividade> atividadeUtil;
	private Atividade atividade;
	private List<Ocorrencia> ocorrencias;
	
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
		
	}
	
	public List<Ocorrencia> getOcorrencias() {
		if (ocorrencias == null && atividadeUtil.getId() != null)
			
			ocorrencias = em.createQuery("SELECT o FROM Ocorrencia o WHERE o.atividade.id = :id", Ocorrencia.class)
				.setParameter("id", atividadeUtil.getId())
				.getResultList();

		return ocorrencias;
	}
	
	public String excluir() {
		FacesUtil.LOGGER.info("TENTOU DELETAR ATIVIDADE " + atividadeUtil.getId());
		
		int idEvento = 0;
		try {
			utx.begin();
			idEvento = atividade.getEvento().getId();
			
			// atividade, aqui, é detached.
			// entao, é preciso buscá-la novamente pra poder deletá-la.
			final Atividade a = em.find(Atividade.class, atividade.getId());
			em.remove(a);
			
			utx.commit();
			FacesUtil.addMessage("Atividade " + atividadeUtil.getId() + " excluída!");
		} catch (Exception e) {
			FacesUtil.logException("Erro ao deletar atividade " + atividadeUtil.getId(), e);
		}
		
		FacesUtil.keepMessages();
		return "admevento?faces-redirect=true&evento_id=" + idEvento;
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
