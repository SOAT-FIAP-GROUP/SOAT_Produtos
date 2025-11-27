package br.com.ms_produtos.usecase.impl;

import br.com.ms_produtos.entity.Categoria;
import br.com.ms_produtos.entity.Produto;
import br.com.ms_produtos.exception.EntityNotFoundException;
import br.com.ms_produtos.gateway.ICategoriaGateway;
import br.com.ms_produtos.gateway.IProdutoGateway;
import br.com.ms_produtos.usecase.IProdutoUseCase;

import java.util.List;

public class ProdutoUseCase implements IProdutoUseCase {

    private final IProdutoGateway produtoGateway;
    private final ICategoriaGateway categoriaGateway;

    public ProdutoUseCase(IProdutoGateway produtoGateway, ICategoriaGateway categoriaGateway) {
        this.produtoGateway = produtoGateway;
        this.categoriaGateway = categoriaGateway;
    }

    @Override
    public Produto cadastrarProduto(Produto entity) {
        return produtoGateway.save(entity);
    }

    @Override
    public Produto buscarProduto(Long id) {
        return produtoGateway.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Produto.class, id));
    }

    @Override
    public Produto atualizarProduto(Long id, Produto entity) {
        this.buscarProduto(id);
        return this.cadastrarProduto(entity);
    }

    @Override
    public void removerProduto(Long id) {
        produtoGateway.removerProduto(this.buscarProduto(id));
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(Long id) {
        return produtoGateway.findByCategoriaCodigo(id);
    }

    @Override
    public Categoria buscarCategoria(Long id) {
        return categoriaGateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Categoria.class, id));
    }
}
