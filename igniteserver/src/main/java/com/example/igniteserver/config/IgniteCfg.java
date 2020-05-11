package com.example.igniteserver.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        Ignite ignite=Ignition.start(configuration);
        return ignite;
    }
}