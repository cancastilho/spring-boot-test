package br.com.cancastilho.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import br.com.cancastilho.modelo.Contribuinte;

@Transactional
@RepositoryRestResource(path = "contribuintes")
public interface ContribuinteRepositorio extends JpaRepository<Contribuinte, Long>, ContribuinteRepositorioCustom {

	List<Contribuinte> findByNomeStartsWith(@Param("nome") String nome);

	// SQL => LIKE 'model%'
	List<Contribuinte> findByNomeStartingWithIgnoreCase(@Param("nome") String nome);

	// SQL => LIKE '%model'
	List<Contribuinte> findByNomeEndingWithIgnoreCase(@Param("nome") String nome);

	// SQL => LIKE '%model%'
	List<Contribuinte> findByNomeContainingIgnoreCase(@Param("nome") String nome);

	List<Contribuinte> findByBairroContaining(@Param("bairro") String bairro);

	List<Contribuinte> findByNome(String nome);

	UserContribuinte findById(Long id);
}
