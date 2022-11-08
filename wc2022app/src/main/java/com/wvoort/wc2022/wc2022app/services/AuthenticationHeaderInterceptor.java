package com.wvoort.wc2022.wc2022app.services;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.nio.charset.Charset;

class AuthenticationHeaderInterceptor implements ClientHttpRequestInterceptor {

    private final String headerName = "Authorization";

    private String authenticationValue;

    public AuthenticationHeaderInterceptor(Authentication authentication) {
        String userAndPwd = authentication.getName() + ":" + authentication.getCredentials();
        byte[] encodedAuth = Base64.encodeBase64(
                userAndPwd.getBytes(Charset.forName("US-ASCII")) );
        authenticationValue = "Basic " + new String( encodedAuth );
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(headerName,authenticationValue);
        return execution.execute(request, body);
    }
}
