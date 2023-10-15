package com.drem.fileprocessing.chunk.listener;

import com.drem.fileprocessing.entities.Gastos;
import com.drem.fileprocessing.recipient.GastosRecipient;
import org.springframework.batch.core.ItemProcessListener;

public class Listener implements ItemProcessListener<GastosRecipient, Gastos> {

    @Override
    public void beforeProcess(GastosRecipient item) {
        ItemProcessListener.super.beforeProcess(item);
    }

    @Override
    public void afterProcess(GastosRecipient item, Gastos result) {
        ItemProcessListener.super.afterProcess(item, result);
    }

    @Override
    public void onProcessError(GastosRecipient item, Exception e) {
        ItemProcessListener.super.onProcessError(item, e);
    }
}
