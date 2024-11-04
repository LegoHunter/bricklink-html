package com.bricklink.web.configuration;

import com.bricklink.web.api.BricklinkWebService;
import com.bricklink.web.support.BricklinkWebServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.HeaderElement;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.BasicHeaderElementIterator;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.http.protocol.HTTP.CONN_KEEP_ALIVE;
import static org.apache.http.protocol.HttpCoreContext.HTTP_TARGET_HOST;

@Configuration
public class BricklinkWebConfiguration {
    @Bean
    public BricklinkWebService bricklinkWebService(final HttpClientConnectionManager httpClientConnectionManager, final BricklinkWebProperties bricklinkWebProperties, final ObjectMapper objectMapper, final ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        return new BricklinkWebServiceImpl(httpClientConnectionManager, bricklinkWebProperties, objectMapper, connectionKeepAliveStrategy);
    }

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager(final BricklinkWebProperties bricklinkWebProperties) {
        BasicHttpClientConnectionManager cm = new BasicHttpClientConnectionManager();
        return cm;
    }

    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            // Honor 'keep-alive' header
            BasicHeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.next();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return TimeValue.ofMilliseconds(Long.parseLong(value) * 1000);
                    } catch (NumberFormatException ignore) {
                    }
                }
            }
            HttpHost target = (HttpHost) context.getAttribute(HTTP_TARGET_HOST);
            if ("www.bricklink.com".equalsIgnoreCase(target.getHostName())) {
                // Keep alive for 5 seconds only
                return TimeValue.ofMilliseconds(5 * 1000);
            } else {
                // otherwise keep alive for 30 seconds
                return TimeValue.ofMilliseconds(30 * 1000);
            }
        };
    }
}
