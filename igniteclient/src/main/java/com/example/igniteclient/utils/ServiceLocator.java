package com.example.igniteclient.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class ServiceLocator implements BeanFactoryAware {
    private static BeanFactory beanFactory = null;
    private static ServiceLocator serviceLocator = null;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        ServiceLocator.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static ServiceLocator getInstance() {
        if (serviceLocator == null) {
            serviceLocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
        }
        return serviceLocator;
    }

    public static Object getService(String serviceName) {
        return beanFactory.getBean(serviceName);
    }
}
