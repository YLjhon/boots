package com.example.igniteclient.config;

import com.example.igniteclient.po.Person;
import com.example.igniteclient.store.PersonStore;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.FactoryBuilder;

@Configuration
public class IgniteCfg {

   @Bean
    public Ignite ignite(){
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setMulticastGroup("224.0.0.251");
        spi.setIpFinder(ipFinder);
        IgniteConfiguration configuration = new IgniteConfiguration();
        configuration.setDiscoverySpi(spi);
        //configuration.setClientMode(true);
        configuration.setPeerClassLoadingEnabled(true);
        Ignite ignite=Ignition.start(configuration);
        return ignite;
    }
    @Bean
    public Person getPerson(){
       Person p=new Person();
       p.setName("luck");
       return p;
    }

    @Bean
    public IgniteCache igniteCache(Ignite ignite){
        CacheConfiguration<Integer, Person> cfg = new CacheConfiguration<Integer,Person >(); //定义一个缓存配置
        cfg.setName("PersonCache");  //必须设置名字
        cfg.setCacheMode(CacheMode.PARTITIONED);//存储方式 PARTITIONED适合分布式存储
        cfg.setBackups(1);
        cfg.setWriteBehindEnabled(true);
        cfg.setReadThrough(true);

        cfg.setCacheStoreFactory(FactoryBuilder.factoryOf(PersonStore.class));

        cfg.setIndexedTypes(Integer.class, Person.class); //必须设置索引类否则只能以key-value方式查询
        IgniteCache<Integer, Person > cache = ignite.getOrCreateCache(cfg);//根据配置创建缓存
        return cache;
    }



}