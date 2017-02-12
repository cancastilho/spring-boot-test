package br.com.cancastilho.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "HB_SEQ", sequenceName = "ContribuinteSeq", initialValue = 2000)
public class Contribuinte implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "HB_SEQ")
	private Long id;
	private String nome;

	@Embedded
	private Cpf cpf;
	private String endereco;
	private String numero;
	private String bairro;
	private String complemento;
	private String email;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "contribuinteId")
	private Set<Telefone> telefones = new HashSet();

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "contribuinteId")
	private Set<Imovel> imoveis = new HashSet();

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(Set<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public void adicionarImovel(Imovel i) {
		this.imoveis.add(i);

	}

}
