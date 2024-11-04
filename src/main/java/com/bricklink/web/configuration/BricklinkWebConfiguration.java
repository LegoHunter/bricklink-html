package com.bricklink.web.configuration;

import com.bricklink.web.api.BricklinkWebService;
import com.bricklink.web.support.BricklinkWebServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
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
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch(NumberFormatException ignore) {
                    }
                }
            }
            HttpHost target = (HttpHost) context.getAttribute(
                    HttpClientContext.HTTP_TARGET_HOST);
            if ("www.bricklink.com".equalsIgnoreCase(target.getHostName())) {
                // Keep alive for 5 seconds only
                return 5 * 1000;
            } else {
                // otherwise keep alive for 30 seconds
                return 30 * 1000;
            }
        };
    }
}
