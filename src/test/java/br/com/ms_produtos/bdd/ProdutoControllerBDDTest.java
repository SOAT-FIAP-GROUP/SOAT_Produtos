package br.com.ms_produtos.bdd;

import br.com.ms_produtos.controller.ProdutoController;
import br.com.ms_produtos.controller.mapper.CategoriaMapper;
import br.com.ms_produtos.controller.mapper.ProdutoMapper;
import br.com.ms_produtos.controller.mapper.dto.request.ProdutoRequest;
import br.com.ms_produtos.controller.mapper.dto.response.ProdutoResponse;
import br.com.ms_produtos.entity.Produto;
import br.com.ms_produtos.mocks.MockGenerator;
import br.com.ms_produtos.usecase.IProdutoUseCase;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProdutoControllerBDDTest {
    private ProdutoController produtoController;
    private IProdutoUseCase produtoUseCase;
    private ProdutoMapper produtoMapper;
    private CategoriaMapper categoriaMapper;

    private ProdutoRequest request;
    private Produto produto;
    private ProdutoResponse response;
    private ProdutoResponse resultado;

    @Before
    public void setUp() {
        produtoUseCase = mock(IProdutoUseCase.class);
        produtoMapper = mock(ProdutoMapper.class);
        categoriaMapper = mock(CategoriaMapper.class);
        produtoController = new ProdutoController(
                produtoUseCase, produtoMapper, categoriaMapper
        );
    }

    @Given("que foram passadas as caracteristicas de um novo produto")
    public void que_foram_passadas_as_caracteristicas_de_um_novo_produto() {
        this.request = MockGenerator.generateProdutoRequestMock();
        this.produto = MockGenerator.generateProdutoMock();
    }

    @When("o servico invocar a funcao de cadastro de novos produtos")
    public void o_servico_invocar_a_funcao_de_cadastro_de_novos_produtos () {
        this.response = MockGenerator.generateProdutoResponseMock();
        when(produtoUseCase.cadastrarProduto(any(Produto.class))).thenReturn(produto);
        when(produtoMapper.toResponse(any(Produto.class))).thenReturn(response);
        this.resultado = produtoController.cadastrarProduto(request);
    }

    @Then("a funcao deve ser executada com sucesso")
    public void a_funcao_deve_ser_executada_com_sucesso() {
        assertEquals(response, resultado);
        verify(produtoUseCase).cadastrarProduto(any(Produto.class));
        verify(produtoMapper).toResponse(produto);
    }
}
