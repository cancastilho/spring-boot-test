package br.com.cancastilho.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Imovel {

	@Id
	private Long id;

	private String tipo;

	@Column(name = "CONTRIBUINTE_ID")
	private Long contribuinteId;
}
