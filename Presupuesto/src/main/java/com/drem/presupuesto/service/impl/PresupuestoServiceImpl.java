package com.drem.presupuesto.service.impl;

import com.drem.presupuesto.model.api.request.CreatePresupuestoRequest;
import com.drem.presupuesto.model.api.request.FindAllMethodPresupuestoRequest;
import com.drem.presupuesto.model.api.response.CreatePresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindAllPresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindByNamePresupuestoResponse;
import com.drem.presupuesto.model.entities.Presupuesto;
import com.drem.presupuesto.repository.PresupuestoRepository;
import com.drem.presupuesto.repository.UsuarioRepository;
import com.drem.presupuesto.service.PresupuestoService;
import com.drem.presupuesto.util.mapping.MessageBuilder;
import com.drem.presupuesto.util.mapping.PresupuestoBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PresupuestoServiceImpl implements PresupuestoService {
    @Autowired
    PresupuestoRepository presupuestoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Mono<CreatePresupuestoResponse> create(CreatePresupuestoRequest presupuestoRequest) {
        log.info("Empezó a crear presupuesto");
        return usuarioRepository.findByIdentifier(presupuestoRequest.getUserIdentifier())
                .flatMap(user -> {
                    Presupuesto presupuesto = PresupuestoBuilder.builderPresupuestoOfCreate(presupuestoRequest);
                    presupuesto.setUsuario_id(user.getId());
                    return presupuestoRepository.save(presupuesto)
                            .flatMap(p -> Mono.just(MessageBuilder.buildCreatePresupuestoResponse(p)));
                })
                .doOnSuccess(p -> log.info("se guardó el siguiente presupuesto: {}", p))
                .doOnError(ex -> log.info("ocurrio un error: {}", ex));

    }

    @Override
    public Mono<FindAllPresupuestoResponse> findAllRangeDate(FindAllMethodPresupuestoRequest presupuestoRequest) {
        log.info("Empezó a buscar todos los presupuestos");
        return usuarioRepository.findByIdentifier(presupuestoRequest.getIdentifierUser())
                .flatMapMany(user -> presupuestoRepository.findAllByRangeDate(user.getId(), presupuestoRequest.getDate()))
                .collectList()
                .map(a -> MessageBuilder.buildFindAllPresupuestoResponse(a))
                .doOnTerminate(() -> log.info("do on terminate"))
                .doOnError(ex -> log.info("ocurrio un error: {}", ex));
    }

    @Override
    public Mono<FindByNamePresupuestoResponse> findAllByName(FindAllMethodPresupuestoRequest presupuestoRequest) {
        log.info("Empezó a buscar presupuestos por nombre");

        return usuarioRepository.findByIdentifier(presupuestoRequest.getIdentifierUser())
                .flatMap(user -> presupuestoRepository.findByName(user.getId(),
                        presupuestoRequest.getNamePresupuesto()))
                .map(a -> MessageBuilder.buildFindByNamePresupuestoResponse(a))
                .doOnTerminate(() -> log.info("do on terminate"))
                .doOnError(ex -> log.info("ocurrio un error: {}", ex));
    }
}
