package com.bricklink.web.support;

import com.bricklink.web.model.AuthenticationResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.client.protocol.HttpClientContext;

@Getter
@RequiredArgsConstructor
public class BricklinkSession {
    private final HttpClientContext httpContext;

    @Setter
    private AuthenticationResult authenticationResult;
}
