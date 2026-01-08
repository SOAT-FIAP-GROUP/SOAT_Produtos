package br.com.ms_produtos.controller;

import br.com.ms_produtos.controller.mapper.CategoriaMapper;
import br.com.ms_produtos.controller.mapper.ProdutoMapper;
import br.com.ms_produtos.controller.mapper.dto.request.ProdutoRequest;
import br.com.ms_produtos.controller.mapper.dto.response.CategoriaResponse;
import br.com.ms_produtos.controller.mapper.dto.response.ProdutoResponse;
import br.com.ms_produtos.entity.Categoria;
import br.com.ms_produtos.entity.Produto;
import br.com.ms_produtos.mocks.MockGenerator;
import br.com.ms_produtos.usecase.IProdutoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    private ProdutoController produtoController;
    private IProdutoUseCase produtoUseCase;
    private ProdutoMapper produtoMapper;
    private CategoriaMapper categoriaMapper;

    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        produtoUseCase = mock(IProdutoUseCase.class);
        produtoMapper = mock(ProdutoMapper.class);
        categoriaMapper = mock(CategoriaMapper.class);

        produtoController = new ProdutoController(
                produtoUseCase, produtoMapper, categoriaMapper
        );
    }

    @Test
    void devebuscarListaProdutosComSucesso() {
        Produto produto1 = MockGenerator.generateProdutoMock();
        Produto produto2 = MockGenerator.generateProdutoMock2();
        Set<Long> listCodigoProdutos = Set.of(produto1.id(), produto2.id());

        Set<Produto> produtosMock = Set.of(
                produto1,
                produto2
        );
        when(produtoUseCase.buscarListaProdutos(listCodigoProdutos)).thenReturn(produtosMock);

        for (Produto p : produtosMock) {
            when(produtoMapper.toResponse(p)).thenReturn(
                    new ProdutoResponse(
                            p.id(),
                            p.nome(),
                            p.descricao(),
                            new CategoriaResponse(p.categoria().id(), p.categoria().nome()),
                            p.preco(),
                            p.tempopreparo()
                    )
            );
        }

        Set<ProdutoResponse> resultado = produtoController.buscarListaProdutos(listCodigoProdutos);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(produtoUseCase, times(1)).buscarListaProdutos(listCodigoProdutos);
        verify(produtoMapper, times(1)).toResponse(produto1);
        verify(produtoMapper, times(1)).toResponse(produto2);
    }

    @Test
    void deveBuscarProdutoComSucesso() {
        Produto produto = MockGenerator.generateProdutoMock();
        ProdutoResponse response = MockGenerator.generateProdutoResponseMock();

        when(produtoUseCase.buscarProduto(anyLong())).thenReturn(produto);
        when(produtoMapper.toResponse(produto)).thenReturn(response);

        ProdutoResponse resultado = produtoController.buscarProduto(ID);

        assertEquals(response, resultado);
        verify(produtoUseCase).buscarProduto(ID);
        verify(produtoMapper).toResponse(produto);
    }

    @Test
    void deveBuscarCategoriaComSucesso() {
        Categoria categoria = MockGenerator.generateCategoriaMock();
        CategoriaResponse response = MockGenerator.generateCategoriaResponseMock();

        when(produtoUseCase.buscarCategoria(anyLong())).thenReturn(categoria);
        //when(categoriaMapper.toResponse(categoria)).thenReturn(response);

        CategoriaResponse resultado = produtoController.buscarCategoria(ID);

        assertEquals(response, resultado);
        verify(produtoUseCase).buscarCategoria(ID);
        //verify(categoriaMapper).toResponse(categoria);
    }

    @Test
    void deveBuscarProdutosPorCategoria() {
        Produto produto = MockGenerator.generateProdutoMock();
        ProdutoResponse response = MockGenerator.generateProdutoResponseMock();

        when(produtoUseCase.buscarProdutosPorCategoria(anyLong()))
                .thenReturn(List.of(produto));
        when(produtoMapper.toResponse(any(Produto.class))).thenReturn(response);

        List<ProdutoResponse> resultado = produtoController.buscarProdutosPorCategoria(ID);

        assertEquals(ID, resultado.size());
        assertTrue(resultado.contains(response));

        verify(produtoUseCase).buscarProdutosPorCategoria(anyLong());
        verify(produtoMapper).toResponse(produto);
    }

    @Test
    void deveRemoverProdutoComSucesso() {

        produtoController.removerProduto(ID);

        verify(produtoUseCase).removerProduto(ID);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        ProdutoRequest request = MockGenerator.generateProdutoRequestMock();
        Produto produto = MockGenerator.generateProdutoMock();
        ProdutoResponse response = MockGenerator.generateProdutoResponseMock();

        when(produtoUseCase.atualizarProduto(anyLong(), any(Produto.class))).thenReturn(produto);
        when(produtoMapper.toResponse(any(Produto.class))).thenReturn(response);

        ProdutoResponse resultado = produtoController.atualizarProduto(request, ID);

        assertEquals(response, resultado);
        verify(produtoUseCase).atualizarProduto(anyLong(), any(Produto.class));
        verify(produtoMapper).toResponse(produto);
    }
}