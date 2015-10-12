package br.uemg.nupsi.eventos.jsf;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.uemg.nupsi.eventos.modelo.Atividade;

@ManagedBean
@ViewScoped
public class EditarAtividadeBean{

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;
	
	private Atividade atividade;
	
	private QueryIdUtil<Atividade> atividadeUtil;
	
	@PostConstruct
	public void init() {
		atividadeUtil = new QueryIdUtil<>(em, Atividade.class);
	}
	
	public void carregarAtividade() {
		atividade = atividadeUtil.getEntidadeOrThrow();
	}
	
	public String salvar() {
		
		try {
			ut.begin();
			atividade = em.merge(atividade);
			ut.commit();
			
			FacesUtil.addMessage("Atividade alteada!");
			FacesUtil.keepMessages();
			
			return "/admin/admatividade?faces-redirect=true&atividade_id=" + atividadeUtil.getId();
			
		} catch (Exception e) {
			FacesUtil.logException("Erro ao salvar", e);
		}
		
		return null;
	}
	
	public Integer getIdAtividade() {
		return atividadeUtil.getId();
	}

	public void setIdAtividade(Integer idAtividade) {
		atividadeUtil.setId(idAtividade);
	}

	public Atividade getAtividade() {
		return atividade;
	}
	
	public void setAtividade(Atividade ativifdade) {
		this.atividade = ativifdade;
	}
}
