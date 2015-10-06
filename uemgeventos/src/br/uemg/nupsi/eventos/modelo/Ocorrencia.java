package br.uemg.nupsi.eventos.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa uma ocorrencia de uma atividade.
 * Normalmente, a atividade possui apenas uma ocorrencia.
 * Atividades que possuem mais de uma ocorrencia sao
 * minicursos que duram mais de um dia, ou que ocorrem
 * na manh� e a tarde, por exemplo.
 * 
 * @author DB
 *
 */
@Entity
@Table(name="ocorrencia")
public class Ocorrencia {

	@Id
	@GeneratedValue
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Temporal(TemporalType.TIME)
	@Column(name="horario_inicio")
	private Date horarioInicio;
	
	@Temporal(TemporalType.TIME)
	@Column(name="horario_termino")
	private Date horarioTermino;
	
	private String local;
	
	@ManyToOne
	@JoinColumn(name="atividade_id")
	private Atividade atividade;
	
	Ocorrencia() {}

	public Ocorrencia(Atividade atividade) {
		this.atividade = atividade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Date getHorarioTermino() {
		return horarioTermino;
	}

	public void setHorarioTermino(Date horarioTermino) {
		this.horarioTermino = horarioTermino;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
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
		Ocorrencia other = (Ocorrencia) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ocorrencia [id=" + id + ", atividade=" + atividade + "]";
	}
	
	
	
}
