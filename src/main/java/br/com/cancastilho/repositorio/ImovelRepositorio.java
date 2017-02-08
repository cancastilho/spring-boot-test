package br.com.cancastilho.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cancastilho.modelo.Imovel;

public interface ImovelRepositorio extends JpaRepository<Imovel, Long> {

}
