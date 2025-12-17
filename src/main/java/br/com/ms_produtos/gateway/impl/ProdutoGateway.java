package br.com.ms_produtos.gateway.impl;

import br.com.ms_produtos.controller.mapper.ProdutoMapper;
import br.com.ms_produtos.entity.Produto;
import br.com.ms_produtos.gateway.IProdutoGateway;
import br.com.ms_produtos.gateway.entity.ProdutoEntity;
import br.com.ms_produtos.gateway.persistence.jpa.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoGateway implements IProdutoGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id).map(ProdutoEntity::toModel);
    }

    @Override
    public List<Produto> findByCategoriaCodigo(Long id) {
        return produtoRepository.findByCategoriaCodigo(id).stream()
                .map(ProdutoEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void removerProduto(Produto entity) {
        produtoRepository.delete(ProdutoMapper.toEntityPersistence(entity));
    }

    @Override
    public Produto save(Produto entity) {
        return produtoRepository.save(ProdutoMapper.toEntityPersistence(entity))
                .toModel();
    }

    @Override
    public Set<Produto> findAllById(Set<Long> listCodigoProdutos) {
        return produtoRepository.findAllById(listCodigoProdutos).stream()
                .map(ProdutoEntity::toModel).collect(Collectors.toSet());
    }
}
