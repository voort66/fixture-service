package com.wvoort.wc2022.scoreservice.services;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;

class ScoreAuthenticationHeaderInterceptor implements ClientHttpRequestInterceptor {

    private static final String SCORE_NAME="score_service";

    private static final String SCORE_CRED = "NERXLjYzS2lyckRoSQo=";

    private final String headerName = "Authorization";

    private String authenticationValue;

    public ScoreAuthenticationHeaderInterceptor() {
        String userAndPwd = SCORE_NAME + ":" + SCORE_CRED;
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
