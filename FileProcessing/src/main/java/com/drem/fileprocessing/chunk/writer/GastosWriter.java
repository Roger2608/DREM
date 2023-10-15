package com.drem.fileprocessing.chunk.writer;

import com.drem.fileprocessing.entities.Gastos;
import com.drem.fileprocessing.repository.GastoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class GastosWriter implements ItemWriter<Gastos> {

    @Autowired
    GastoRepository gastoRepository;

    @Override
    public void write(Chunk<? extends Gastos> chunk) throws Exception {
        log.info("estoy en el writer ===> \n {}", chunk);

        List<Gastos> lista = (List<Gastos>) gastoRepository.saveAll(chunk.getItems());
        log.info("se guardó la siguiente lista ===> {}", lista);
    }
}
