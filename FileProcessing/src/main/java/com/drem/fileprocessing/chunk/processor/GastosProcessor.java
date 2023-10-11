package com.drem.fileprocessing.chunk.processor;

import com.drem.fileprocessing.entities.Gastos;
import com.drem.fileprocessing.recipient.GastosRecipient;
import org.springframework.batch.item.ItemProcessor;

public class GastosProcessor implements ItemProcessor<GastosRecipient,Gastos> {
    @Override
    public Gastos process(GastosRecipient item) throws Exception {
        return null;
    }
}
