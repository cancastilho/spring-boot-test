package br.com.cancastilho.modelo;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
public class Contribuinte {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;

	@Embedded
	private Cpf cpf;
	private String endereco;
	private String numero;
	private String bairro;
	private String complemento;

	@OneToMany(mappedBy = "contribuinteId")
	private List<Telefone> telefones;

	@OneToMany(mappedBy = "contribuinteId")
	private List<Imovel> imoveis;

}
