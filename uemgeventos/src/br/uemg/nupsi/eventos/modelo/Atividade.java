package br.uemg.nupsi.eventos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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
