package com.example.igniteclient.config;

import org.apache.ignite.cache.store.CacheStore;
import org.springframework.context.ApplicationContext;

import javax.cache.configuration.Factory;
import java.io.Serializable;

public class IgniteClassFactory implements Factory, Serializable {

    private CacheStore cacheStore;

    public IgniteClassFactory(CacheStore cacheStore){
        this.cacheStore=cacheStore;
    }

    @Override
    public Object create() {
        return cacheStore;
    }

}
