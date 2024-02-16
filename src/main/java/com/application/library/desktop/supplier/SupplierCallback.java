package com.application.library.desktop.supplier;

@FunctionalInterface
public interface SupplierCallback<R> {
    R get() throws Exception;
}
