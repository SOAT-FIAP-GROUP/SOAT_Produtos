package br.com.ms_produtos.mocks;

import br.com.ms_produtos.controller.mapper.CategoriaMapper;
import br.com.ms_produtos.controller.mapper.ProdutoMapper;
import br.com.ms_produtos.controller.mapper.dto.request.ProdutoRequest;
import br.com.ms_produtos.controller.mapper.dto.response.CategoriaResponse;
import br.com.ms_produtos.controller.mapper.dto.response.ProdutoResponse;
import br.com.ms_produtos.entity.Categoria;
import br.com.ms_produtos.entity.Produto;

import java.math.BigDecimal;
import java.sql.Time;

public class MockGenerator {

    private static final Long ID = 1L;

    public static Produto generateProdutoMock() {
        Produto produto = new Produto(ID,
                "batata recheada",
                "batata recheada e é isso",
                generateCategoriaMock(),
                BigDecimal.valueOf(9.89D),
                Time.valueOf("0:10:00"));
        return produto;
    }

    public static Produto generateProdutoMock2() {
        Produto produto = new Produto(2L,
                "batata recheada",
                "batata recheada e é isso",
                generateCategoriaMock(),
                BigDecimal.valueOf(9.89D),
                Time.valueOf("0:10:00"));
        return produto;
    }

    public static Categoria generateCategoriaMock() {
        Categoria categoria = new Categoria(ID, "LANCHE");
        return categoria;
    }

    public static ProdutoResponse generateProdutoResponseMock() {
        ProdutoMapper produtoMapper = new ProdutoMapper();
        return produtoMapper.toResponse(generateProdutoMock());
    }

    public static ProdutoRequest generateProdutoRequestMock() {
        ProdutoRequest produtoRequest = new ProdutoRequest(
                "batata recheada",
                "batata recheada e é isso",
                ID,
                BigDecimal.valueOf(9.89D),
                Time.valueOf("0:10:00"));
        return produtoRequest;
    }

    public static CategoriaResponse generateCategoriaResponseMock() {
        CategoriaMapper categoriaMapper = new CategoriaMapper();
        return categoriaMapper.toResponse(generateCategoriaMock());
    }
}
