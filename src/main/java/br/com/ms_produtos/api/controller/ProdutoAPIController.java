package br.com.ms_produtos.api.controller;

import br.com.ms_produtos.controller.ProdutoController;
import br.com.ms_produtos.controller.mapper.dto.request.ProdutoRequest;
import br.com.ms_produtos.controller.mapper.dto.response.CategoriaResponse;
import br.com.ms_produtos.controller.mapper.dto.response.ProdutoResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoAPIController {

    private final ProdutoController produtoController;

    public ProdutoAPIController(ProdutoController produtoController) {
        this.produtoController = produtoController;
    }

    @Operation(summary = "Buscar produto por codigo", description = "Retorna um produto especifico cadastrado no codigo")
    @GetMapping("/buscar/produto/{codigoProduto}")
    public ResponseEntity<ProdutoResponse> buscarPorCodigoProduto(@PathVariable Long codigoProduto) throws Exception {
        var response = produtoController.buscarProduto(codigoProduto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Buscar uma lista de produtos por codigo", description = "Retorna uma lista de produtos")
    @GetMapping("/buscar/lista")
    public ResponseEntity<Set<ProdutoResponse>> buscarListaProdutos(@RequestParam Set<Long> listCodigoProdutos) throws Exception {
        var response = produtoController.buscarListaProdutos(listCodigoProdutos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Buscar por categoria", description = "Retorna uma categoria especifica do codigo enviado")
    @GetMapping("/buscar/categoria/{codigoCategoria}")
    public ResponseEntity<CategoriaResponse> buscarPorCategoriaProduto(@PathVariable Long codigoCategoria) {
        var response = produtoController.buscarCategoria(codigoCategoria);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Busca lista de produtos do codigo de categoria", description = "Retorna lista de produtos especificos do codigo categoria enviado")
    @GetMapping("/buscar/categoria/{codigoCategoria}/produtos")
    public ResponseEntity<List<ProdutoResponse>> buscarProdutosPorCategoriaProduto(@PathVariable Long codigoCategoria) {
        var response = produtoController.buscarProdutosPorCategoria(codigoCategoria);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Cadastrar novo produto", description = "Inclusão de novo produto no banco de dados")
    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produto) {
        var response = produtoController.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Remover produto cadastrado", description = "Remover produto cadastrado do banco de dados")
    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity<Void> removerProduto(@PathVariable Long codigo) {
        produtoController.removerProduto(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Atualizar informações de produto", description = "Atualiza informações do produto cadastrado no banco de dados")
    @PutMapping("/{codigo}")
    @Transactional
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long codigo, @RequestBody ProdutoRequest produto) throws Exception {
        var response = produtoController.atualizarProduto(produto, codigo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
