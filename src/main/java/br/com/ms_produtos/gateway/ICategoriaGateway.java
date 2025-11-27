package br.com.ms_produtos.gateway;

import br.com.ms_produtos.entity.Categoria;

import java.util.Optional;

public interface ICategoriaGateway {

    Optional<Categoria> findById(Long id);

}
