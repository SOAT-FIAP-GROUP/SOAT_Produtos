package br.com.ms_produtos.entity;

import java.math.BigDecimal;
import java.sql.Time;

public record Produto(Long id, String nome, String descricao, Categoria categoria, BigDecimal preco, Time tempopreparo) {
}
