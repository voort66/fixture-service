package com.wvoort.wc2022.wc2022app.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {

        CloseableHttpClient client = HttpClients.custom().setSSLHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }).build();
        HttpComponentsClientHttpRequestFactory req = new HttpComponentsClientHttpRequestFactory(client);


        return new RestTemplate(req);
    }

}
