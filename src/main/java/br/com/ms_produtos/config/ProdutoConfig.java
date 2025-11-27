package br.com.ms_produtos.config;

import br.com.ms_produtos.api.controller.ProdutoAPIController;
import br.com.ms_produtos.controller.ProdutoController;
import br.com.ms_produtos.controller.mapper.CategoriaMapper;
import br.com.ms_produtos.controller.mapper.ProdutoMapper;
import br.com.ms_produtos.gateway.ICategoriaGateway;
import br.com.ms_produtos.gateway.IProdutoGateway;
import br.com.ms_produtos.gateway.impl.CategoriaGateway;
import br.com.ms_produtos.gateway.impl.ProdutoGateway;
import br.com.ms_produtos.gateway.persistence.jpa.CategoriaRepository;
import br.com.ms_produtos.gateway.persistence.jpa.ProdutoRepository;
import br.com.ms_produtos.usecase.IProdutoUseCase;
import br.com.ms_produtos.usecase.impl.ProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    @Bean
    ProdutoGateway produtoGateway(ProdutoRepository produtoRepository) {
        return new ProdutoGateway(produtoRepository);
    }

    @Bean
    ProdutoController produtoController(IProdutoUseCase produtoUseCase, ProdutoMapper produtoMapper, CategoriaMapper categoriaMapper) {
        return new ProdutoController(produtoUseCase, produtoMapper, categoriaMapper);
    }

    @Bean
    ProdutoAPIController produtoAPIController(ProdutoController produtoController) {
        return new ProdutoAPIController(produtoController);
    }

    @Bean
    ProdutoMapper produtoMapper(){
        return new ProdutoMapper();
    }

    @Bean
    CategoriaMapper categoriaMapper(){
        return new CategoriaMapper();
    }

    @Bean
    ProdutoUseCase produtoUserCase(IProdutoGateway produtoGateway, ICategoriaGateway categoriaGateway){
        return new ProdutoUseCase(produtoGateway, categoriaGateway);
    }

    @Bean
    CategoriaGateway categoriaGateway(CategoriaRepository categoriaRepository) {
        return new CategoriaGateway(categoriaRepository);
    }
}