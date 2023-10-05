package com.drem.presupuesto.repository;

import com.drem.presupuesto.model.entities.Presupuesto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface PresupuestoRepository extends ReactiveCrudRepository<Presupuesto,Integer> {
    @Query("SELECT * FROM Presupuestos p WHERE p.fecha_creacion < CURRENT_TIMESTAMP() AND p.fecha_creacion >= :date AND p.usuario_id = :user")
    Flux<Presupuesto> findAllByRangeDate(Integer user, LocalDateTime date);

    @Query("SELECT * FROM Presupuestos p WHERE p.usuario_id = :user AND p.nombre = :nombre")
    Mono<Presupuesto> findByName(Integer user, String nombre);

}
