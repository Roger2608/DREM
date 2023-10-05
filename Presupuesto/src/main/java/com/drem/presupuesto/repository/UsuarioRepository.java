package com.drem.presupuesto.repository;

import com.drem.presupuesto.model.entities.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository extends ReactiveCrudRepository<Usuario,Integer> {
    Mono<Usuario> findByIdentifier(String identifier);
}
