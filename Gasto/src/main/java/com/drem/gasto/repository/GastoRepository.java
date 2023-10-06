package com.drem.gasto.repository;

import com.drem.gasto.model.entities.Gasto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface GastoRepository extends ReactiveCrudRepository<Gasto, Integer> {
    @Query("SELECT * FROM gastos_diarios g WHERE g.fecha < CURRENT_TIMESTAMP() " +
            "AND g.fecha >= :date AND g.usuario_id = :user")
    Flux<Gasto> findAllByRangeDate(Integer user, LocalDateTime date);

    @Query("SELECT * FROM gastos_diarios g WHERE g.usuario_id = :user AND g.presupuesto_id = :presupuestoId")
    Mono<Gasto> findByPresupuesto(Integer user, Integer presupuestoId);

}
