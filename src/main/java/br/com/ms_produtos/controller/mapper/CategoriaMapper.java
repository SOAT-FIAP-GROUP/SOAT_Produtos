package br.com.ms_produtos.controller.mapper;

import br.com.ms_produtos.controller.mapper.dto.request.CategoriaRequest;
import br.com.ms_produtos.controller.mapper.dto.response.CategoriaResponse;
import br.com.ms_produtos.entity.Categoria;
import br.com.ms_produtos.gateway.entity.CategoriaEntity;

public class CategoriaMapper {

    public static CategoriaResponse toResponse(Categoria entity){
        return new CategoriaResponse(entity.id(), entity.nome());
    }

    public static Categoria toEntity(CategoriaRequest categoria){
        return new Categoria(categoria.id(), categoria.nome());
    }

    public static CategoriaEntity toEntityPersistence(Categoria entity){
        return new CategoriaEntity(entity.id(), entity.nome());
    }

    public static Categoria toResponseByRes(CategoriaResponse response){
        return new Categoria(response.id(), response.nome());
    }
}
