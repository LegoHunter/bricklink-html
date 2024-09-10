package com.bricklink.web.configuration;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BricklinkWebPropertiesTest {
    @Test
    void writeJson() throws Exception {
        BricklinkWebProperties bricklinkWebProperties = new BricklinkWebProperties();
        bricklinkWebProperties.setBricklink(new BricklinkWebProperties.Bricklink());
        BricklinkWebProperties.Bricklink bricklink = bricklinkWebProperties.getBricklink();
        bricklink.setCredential(new BricklinkWebProperties.Credential());
        BricklinkWebProperties.Credential credential = bricklink.getCredential();
        credential.setUsername("tvattima");
        credential.setPassword("xxxxxxxxxxxxx");
        bricklink.setUrls(new HashMap<>());
        Map<String, URL> pages = bricklink.getUrls();
        pages.put("page1", new URL("https://www.bricklink.com/v2/login.page"));
        pages.put("page2", new URL("https://www.bricklink.com/v2/someother.page"));
        pages.put("page3", new URL("https://www.bricklink.com/v2/acool.page"));
        bricklinkWebProperties.writeJson();
        assertThat(bricklinkWebProperties).isNotNull();
    }
}