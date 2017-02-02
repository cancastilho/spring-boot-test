package br.com.cancastilho.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Telefone {

	@Id
	private Long id;

	private String numero;

	@Column(name = "CONTRIBUINTE_ID")
	private Long contribuinteId;

}
