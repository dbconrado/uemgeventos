package br.uemg.nupsi.eventos.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="atividade")
public class Atividade {

	@Id
	@GeneratedValue
	private int id;

	/**
	 * Se a atividade aceita inscricao, a pessoa precisa marcar se quer
	 * inscrever-se nesta atividade ou nao. Alem disso, ha controle de vagas
	 * quando essa opcao eh escolhida.
	 * 
	 * Quando nao aceita inscricao, toda pessoa eh automaticamente inscrita na
	 * atividade (para fins de certificado).
	 */
	@Column(name = "aceita_inscricao")
	private boolean aceitaInscricao;

	@Column(name = "carga_horaria")
	private int cargaHoraria;

	/**
	 * Nome da pessoa que vai palestrar ou dar o minicurso. Importante para fins
	 * de geracao de certificado
	 */
	private String responsavel;

	private String nome;
	private int vagas;

	@ManyToOne
	@JoinColumn(name="evento_id")
	private Evento evento;

	/**
	 * A data inicial eh um campo derivado (calculado em tempo de execucao)
	 * e tem como valor a data da sua primeira ocorrencia.
	 * NOTA 1: A anotacao Formula eh propria do Hibernate.
	 * O id referenciado na JPQL eh o id da atividade.
	 * NOTA 2: esse metodo tem performance ruim, mas aceitavel pois
	 * o banco de dados eh pequeno.
	 */
	@Formula("(select min(o.data) from ocorrencia o where o.atividade_id = id)")
	private Date dataInicial;
	
	/**
	 * Construtor padrao com visibilidade pacote
	 * 
	 */
	Atividade() {
	}

	/**
	 * Construtor publico. Este eh o que os desenvolvedores devem utilizar.
	 * Garante a consistencia do objeto.
	 * @param evento
	 */
	public Atividade(Evento evento) {
		this.evento = evento;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAceitaInscricao() {
		return aceitaInscricao;
	}

	public void setAceitaInscricao(boolean aceitaInscricao) {
		this.aceitaInscricao = aceitaInscricao;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", nome=" + nome + "]";
	}

	
	
}
