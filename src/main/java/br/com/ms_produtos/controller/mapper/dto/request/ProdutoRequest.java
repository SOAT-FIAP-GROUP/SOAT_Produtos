package br.com.ms_produtos.controller.mapper.dto.request;

import java.math.BigDecimal;
import java.sql.Time;

public record ProdutoRequest(String nome, String descricao, Long categoriaId, BigDecimal preco, Time tempopreparo) {
}
