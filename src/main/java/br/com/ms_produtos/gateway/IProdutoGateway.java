package br.com.ms_produtos.gateway;

import br.com.ms_produtos.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface IProdutoGateway {

    Optional<Produto> findById(Long id);

    List<Produto> findByCategoriaCodigo(Long id);

    void removerProduto(Produto entity);

    Produto save(Produto entity);

}
