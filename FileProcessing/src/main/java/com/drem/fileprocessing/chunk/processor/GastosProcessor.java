package com.drem.fileprocessing.chunk.processor;

import com.drem.fileprocessing.entities.Gastos;
import com.drem.fileprocessing.recipient.GastosRecipient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class GastosProcessor implements ItemProcessor<GastosRecipient, Gastos> {
    @Override
    public Gastos process(GastosRecipient item) throws Exception {
      log.info("ingresé en el processor los datos son los siguientes: \n {}",item);
        return Gastos.builder()
                .descripcion(item.getDescription())
                .fecha(LocalDateTime.parse(item.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .monto(item.getMonto())
                .usuario_id(item.getUsuario())
                .presupuesto_id(item.getPresupuesto())
                .build();
    }
}
