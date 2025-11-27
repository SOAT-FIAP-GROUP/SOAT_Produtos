package br.com.ms_produtos.gateway.impl;

import br.com.ms_produtos.entity.Categoria;
import br.com.ms_produtos.gateway.ICategoriaGateway;
import br.com.ms_produtos.gateway.entity.CategoriaEntity;
import br.com.ms_produtos.gateway.persistence.jpa.CategoriaRepository;

import java.util.Optional;

public class CategoriaGateway implements ICategoriaGateway {

    private final CategoriaRepository categoriaRepository;

    public CategoriaGateway(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id).map(CategoriaEntity::toModel);
    }
}
