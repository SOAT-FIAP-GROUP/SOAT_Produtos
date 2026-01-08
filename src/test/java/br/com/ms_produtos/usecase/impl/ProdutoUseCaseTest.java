package br.com.ms_produtos.usecase.impl;

import br.com.ms_produtos.entity.Categoria;
import br.com.ms_produtos.entity.Produto;
import br.com.ms_produtos.exception.EntityNotFoundException;
import br.com.ms_produtos.gateway.ICategoriaGateway;
import br.com.ms_produtos.gateway.IProdutoGateway;
import br.com.ms_produtos.mocks.MockGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProdutoUseCaseTest {

    private ProdutoUseCase produtoUserCase;
    private IProdutoGateway produtoGateway;
    private ICategoriaGateway categoriaGateway;

    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        produtoGateway = mock(IProdutoGateway.class);
        categoriaGateway = mock(ICategoriaGateway.class);
        produtoUserCase = new ProdutoUseCase(produtoGateway, categoriaGateway);
    }

    @Test
    void devebuscarListaProdutosQuandoIdExistir() {
        Produto produto1 = MockGenerator.generateProdutoMock();
        Produto produto2 = MockGenerator.generateProdutoMock2();
        Set<Produto> produtosMock = Set.of(produto1, produto2);
        Set<Long> produtosIdMock = Set.of(produto1.id(), produto2.id());


        when(produtoGateway.findAllById(anySet())).thenReturn(produtosMock);

        Set<Produto> resultado = produtoUserCase.buscarListaProdutos(produtosIdMock);

        assertNotNull(resultado);
        verify(produtoGateway).findAllById(produtosIdMock);
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = produtoUserCase.cadastrarProduto(produto);

        assertNotNull(resultado);
        verify(produtoGateway, times(1)).save(produto);
    }

    @Test
    void deveBuscarProdutoQuandoIdExistir() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.of(produto));

        Produto resultado = produtoUserCase.buscarProduto(ID);

        assertNotNull(resultado);
        verify(produtoGateway).findById(ID);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        Long id = 99L;
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                produtoUserCase.buscarProduto(ID));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.of(produto));
        when(produtoGateway.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = produtoUserCase.atualizarProduto(ID, produto);

        assertNotNull(resultado);
        verify(produtoGateway).findById(ID);
        verify(produtoGateway).save(produto);
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        when(produtoGateway.findById(anyLong())).thenReturn(Optional.of(produto));

        produtoUserCase.removerProduto(ID);

        verify(produtoGateway).findById(ID);
        verify(produtoGateway).removerProduto(produto);
    }

    @Test
    void deveBuscarProdutosPorCategoriaComSucesso() {
        List<Produto> produtos = List.of(MockGenerator.generateProdutoMock());
        when(produtoGateway.findByCategoriaCodigo(anyLong())).thenReturn(produtos);

        List<Produto> resultado = produtoUserCase.buscarProdutosPorCategoria(ID);

        assertEquals(ID, resultado.size());
        verify(produtoGateway).findByCategoriaCodigo(ID);
    }

    @Test
    void deveBuscarCategoriaQuandoIdExistir() {
        Categoria categoria = MockGenerator.generateCategoriaMock();
        when(categoriaGateway.findById(anyLong())).thenReturn(Optional.of(categoria));

        Categoria resultado = produtoUserCase.buscarCategoria(ID);

        assertNotNull(resultado);
        verify(categoriaGateway).findById(ID);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoForEncontrada() {
        when(categoriaGateway.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                produtoUserCase.buscarCategoria(ID));
    }
}