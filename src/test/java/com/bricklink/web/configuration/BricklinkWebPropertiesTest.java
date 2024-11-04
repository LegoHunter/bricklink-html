package com.bricklink.web.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BricklinkWebPropertiesTest {
    @Test
    void writeJson() throws Exception {
        BricklinkWebProperties bricklinkWebProperties = new BricklinkWebProperties();
        bricklinkWebProperties.setCredential(new BricklinkWebProperties.Credential());
        BricklinkWebProperties.Credential credential = bricklinkWebProperties.getCredential();
        credential.setUsername("tvattima");
        credential.setPassword("xxxxxxxxxxxxx");
        bricklinkWebProperties.setUrls(new HashMap<>());
        Map<String, URL> pages = bricklinkWebProperties.getUrls();
        pages.put("page1", new URL("https://www.bricklink.com/v2/login.page"));
        pages.put("page2", new URL("https://www.bricklink.com/v2/someother.page"));
        pages.put("page3", new URL("https://www.bricklink.com/v2/acool.page"));
        new ObjectMapper().writeValueAsString(bricklinkWebProperties);
        assertThat(bricklinkWebProperties).isNotNull();
    }
}