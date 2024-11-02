package com.bricklink.web.configuration;

import com.bricklink.web.BricklinkWebException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.Map;
import java.util.Optional;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "bricklink.web")
public class BricklinkWebProperties {
    private Pool pool;
    private Credential credential;
    private Map<String, URL> urls;

    public URL getURL(String name) {
        return Optional.ofNullable(getUrls().get(name)).orElseThrow(() -> new BricklinkWebException("Unknown page requested [" + name + "]"));
    }

    @Data
    public static class Pool {
        private Integer defaultMaxPerRoute;
        private Integer maxPerRoute;
        private Integer maxTotal;
    }

    @Data
    public static class Credential {
        private String username;
        private String password;
    }
}

