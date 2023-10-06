package com.drem.gasto.service.impl;

import com.drem.gasto.model.api.request.CreateGastoRequest;
import com.drem.gasto.model.api.request.FindAllMethodGastoRequest;
import com.drem.gasto.model.api.response.CreateGastoResponse;
import com.drem.gasto.model.api.response.FindAllGastoResponse;
import com.drem.gasto.model.entities.Gasto;
import com.drem.gasto.repository.GastoRepository;
import com.drem.gasto.repository.UsuarioRepository;
import com.drem.gasto.service.GastoService;
import com.drem.gasto.util.mapping.GastoBuilder;
import com.drem.gasto.util.mapping.MessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GastoServiceImpl implements GastoService {
    @Autowired
    GastoRepository gastoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Mono<CreateGastoResponse> create(CreateGastoRequest GastoRequest) {
        log.info("Empezó a crear Gasto");
        return usuarioRepository.findByIdentifier(GastoRequest.getUserIdentifier())
                .flatMap(user -> {
                    Gasto Gasto = GastoBuilder.builderGastoOfCreate(GastoRequest);
                    Gasto.setUsuario_id(user.getId());
                    return gastoRepository.save(Gasto)
                            .flatMap(p -> Mono.just(MessageBuilder.buildCreateGastoResponse(p)));
                })
                .doOnSuccess(p -> log.info("se guardó el siguiente Gasto: {}", p))
                .doOnError(ex -> log.info("ocurrio un error: {}", ex));

    }

    @Override
    public Mono<FindAllGastoResponse> findAllRangeDate(FindAllMethodGastoRequest gastoRequest) {
        log.info("Empezó a buscar todos los Gastos");
        return usuarioRepository.findByIdentifier(gastoRequest.getIdentifierUser())
                .flatMapMany(user -> gastoRepository.findAllByRangeDate(user.getId(), gastoRequest.getDate()))
                .collectList()
                .map(a -> MessageBuilder.buildFindAllGastoResponse(a))
                .doOnTerminate(() -> log.info("do on terminate"))
                .doOnError(ex -> log.info("ocurrio un error: {}", ex));
    }

    @Override
    public Mono<FindAllGastoResponse> findAllByPresupuesto(FindAllMethodGastoRequest gastoRequest) {
        return usuarioRepository.findByIdentifier(gastoRequest.getIdentifierUser())
                .flatMapMany(user -> gastoRepository.findByPresupuesto(user.getId(), gastoRequest.getPresupuestoId()))
                .collectList()
                .map(a -> MessageBuilder.buildFindByPresupuestoGastoResponse(a))
                .doOnTerminate(() -> log.info("do on terminate"))
                .doOnError(ex -> log.info("ocurrio un error: {}", ex));
    }

    /*@Override
    public Mono<FindByNameGastoResponse> findAllByName(FindAllMethodGastoRequest GastoRequest) {
        log.info("Empezó a buscar Gastos por nombre");

        return usuarioRepository.findByIdentifier(GastoRequest.getIdentifierUser())
                .flatMap(user -> GastoRepository.findByName(user.getId(),
                        GastoRequest.getNameGasto()))
                .map(a -> MessageBuilder.buildFindByNameGastoResponse(a))
                .doOnTerminate(() -> log.info("do on terminate"))
                .doOnError(ex -> log.info("ocurrio un error: {}", ex));
    }*/
}
