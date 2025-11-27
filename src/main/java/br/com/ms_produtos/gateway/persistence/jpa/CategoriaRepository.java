package br.com.ms_produtos.gateway.persistence.jpa;

import br.com.ms_produtos.gateway.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}