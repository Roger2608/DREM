package com.drem.fileprocessing.chunk.writer;

import com.drem.fileprocessing.entities.Gastos;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class GastosWriter implements ItemWriter<Gastos> {
    @Override
    public void write(Chunk<? extends Gastos> chunk) throws Exception {

    }
}
