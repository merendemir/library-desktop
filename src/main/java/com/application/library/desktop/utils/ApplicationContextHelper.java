package com.application.library.desktop.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ApplicationContextHelper {

    private final ApplicationContext context;

    public ApplicationContextHelper(ApplicationContext context) {
        this.context = context;
    }

    public <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    public <T> Collection<T> getBeansOfType(Class<T> beanClass) {
        return context.getBeansOfType(beanClass).values();
    }
}
