package com.wallacegomes.automacaoedificacoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wallacegomes.automacaoedificacoes.domain.TipoEquipamento;

@Repository
public interface TipoEquipamentoRepository extends JpaRepository<TipoEquipamento, Integer>{
}