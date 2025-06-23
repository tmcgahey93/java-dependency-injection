package com.example.config;

import java.beans.BeanProperty;

import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springFramework.context.annotation.Bean;
import org.springFramework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    @Bean
    public CloseableHttpClient pooledHttpClient() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(20);
        connManager.setDefaultMaxPerRoute(10);

        return HttpClients.custom()
            .setConnectionManager(connManager)
            .build();
    }
    
}
