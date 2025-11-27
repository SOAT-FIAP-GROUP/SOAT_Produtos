package br.com.ms_produtos.usecase;

import br.com.ms_produtos.entity.Categoria;
import br.com.ms_produtos.entity.Produto;

import java.util.List;

public interface IProdutoUseCase {

    Produto cadastrarProduto(Produto entity);

    Produto buscarProduto(Long id);

    Produto atualizarProduto(Long id, Produto entity);

    void removerProduto(Long id);

    List<Produto> buscarProdutosPorCategoria(Long id);

    Categoria buscarCategoria(Long id);
}
