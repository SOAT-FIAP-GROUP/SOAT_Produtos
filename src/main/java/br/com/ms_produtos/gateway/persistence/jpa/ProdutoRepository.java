package br.com.ms_produtos.gateway.persistence.jpa;

import br.com.ms_produtos.gateway.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findByCategoriaCodigo(Long id);
}
