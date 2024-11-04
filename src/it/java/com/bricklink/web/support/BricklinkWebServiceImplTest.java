package com.bricklink.web.support;

import com.bricklink.api.html.model.v2.WantedItem;
import com.bricklink.api.html.model.v2.WantedList;
import com.bricklink.web.api.BricklinkWebService;
import com.bricklink.web.configuration.BricklinkWebConfiguration;
import com.bricklink.web.configuration.BricklinkWebProperties;
import com.bricklink.web.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringJUnitConfig(classes = {BricklinkWebServiceImplTest.MyTestConfiguration.class, BricklinkWebConfiguration.class})
public class BricklinkWebServiceImplTest {
    @Autowired
    private ConnectionKeepAliveStrategy connectionKeepAliveStrategy;

    @Autowired
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    private BricklinkWebProperties properties;

    @Test
    void downloadCatalogItem() {
        StopWatch timer = new StopWatch();
        timer.start();
        try {
            ObjectMapper mapper = new ObjectMapper();

            BricklinkWebService bricklinkWebService = new BricklinkWebServiceImpl(httpClientConnectionManager, properties, mapper, connectionKeepAliveStrategy);
            bricklinkWebService.authenticate();

            Set<Item> allCatalogItems = bricklinkWebService.getAllSetTypeCatalogItems();

            bricklinkWebService.logout();
        } finally {
            timer.stop();
        }
    }

    @Test
    void extractWantedLists() {
        StopWatch timer = new StopWatch();
        timer.start();
        try {
            ObjectMapper mapper = new ObjectMapper();

            BricklinkWebService bricklinkWebService = new BricklinkWebServiceImpl(httpClientConnectionManager, properties, mapper, connectionKeepAliveStrategy);
            bricklinkWebService.authenticate();

            List<WantedList> wantedLists = bricklinkWebService.getWantedLists();

            assertThat(wantedLists).isNotEmpty();

            wantedLists.forEach(wl -> log.info("Id: {} Name: [{}] Wanted Items Count: [{}]", wl.getId(), wl.getName(), wl.getNum()));

            bricklinkWebService.logout();
        } finally {
            timer.stop();
        }
    }

    @Test
    void extractWantedListItems() {
        StopWatch timer = new StopWatch();
        timer.start();
        try {
            ObjectMapper mapper = new ObjectMapper();

            BricklinkWebService bricklinkWebService = new BricklinkWebServiceImpl(httpClientConnectionManager, properties, mapper, connectionKeepAliveStrategy);
            bricklinkWebService.authenticate();

            Set<WantedItem> wantedListItems = bricklinkWebService.getWantedListItems(9626930L);

            assertThat(wantedListItems).isNotEmpty();

            wantedListItems.forEach(wli -> log.info("Item No: {} Item Name: [{}] Color [{}] Wanted Quantity {} Wanted new {} ", wli.getItemNo(), wli.getItemName(), wli.getColorName(), wli.getWantedQty(), wli.getWantedNew()));

            bricklinkWebService.logout();
        } finally {
            timer.stop();
        }
    }

    @Disabled
    public void updateExtendedDescription() {
        BricklinkSession bricklinkSession = null;
        String extendedDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eu elit sit amet ligula convallis scelerisque eu sed urna. Cras fermentum vitae massa non sollicitudin. Fusce condimentum sem in tempus convallis. Donec vitae eros eget ante ultrices viverra in vel lorem. Pellentesque rhoncus gravida magna at aliquet. Vivamus pulvinar sollicitudin ultrices. Curabitur quis velit feugiat, sodales dolor nec, vestibulum massa. Aliquam enim est, gravida sit amet tempus eu, gravida ac risus. Cras suscipit, metus non varius hendrerit, nulla ipsum blandit nunc, a tristique nunc metus ac orci. Suspendisse in congue velit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum lacus sapien, consequat nec finibus vitae, sagittis nec nisi. Vivamus quis faucibus purus. Suspendisse vitae sodales eros. Nunc ultrices mi ante, eu mollis enim varius a.";
        StopWatch timer = new StopWatch();
        timer.start();
        try {
            ObjectMapper mapper = new ObjectMapper();

            BricklinkWebServiceImpl bricklinkWebService = new BricklinkWebServiceImpl(httpClientConnectionManager, properties, mapper, connectionKeepAliveStrategy);
            bricklinkWebService.authenticate();
            bricklinkWebService.updateExtendedDescription(174935081L, extendedDescription);
            bricklinkWebService.logout();
        } finally {
            timer.stop();
        }
        log.info("Updated extended description to [{}] for inventory [{}] in [{}] ms", extendedDescription, 174935081L, timer.getTotalTimeMillis());
    }

//    @Test
//    @Ignore
//    public void uploadInventory_multiThreaded() {
//        ImageScalingService imageScalingService = new ImageScalingService();
//        BricklinkWebService bricklinkWebService = new BricklinkWebService(httpClientConnectionManager, properties, mapper, connectionKeepAliveStrategy);
//        BricklinkSession bricklinkSession = bricklinkWebService.authenticate();
//        Map<Long, Path> inventoryPhotos = new HashMap<>();
////        inventoryPhotos.put(171947252L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6641-1-a89ad8dcf82e868a5092d4a2376f8981\\DSC_0593.JPG"));
////        inventoryPhotos.put(171947247L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\646-1-c24f8e2ac2e40a63ba3b253316c32fb9\\DSC_0582.JPG"));
////        inventoryPhotos.put(171947253L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6658-1-f81eb8d9ddc469b48dff9c45064be5eb\\DSC_0516.JPG"));
////        inventoryPhotos.put(171947228L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6627-1-9b665132a4001f281d6cce8b0033ae7b\\DSC_0628.JPG"));
////        inventoryPhotos.put(171947231L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6624-1-88ad09baa1f934961ada875b0c279273\\DSC_0657.JPG"));
////        inventoryPhotos.put(171947251L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6521-1-85179faa62d34993132558d1dcea6bc2\\DSC_0619.JPG"));
////        inventoryPhotos.put(171947248L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6633-1-3104970a366f472405a5ee30756b7e70\\DSC_0643.JPG"));
////        inventoryPhotos.put(171947250L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6657-1-bb994be7893e07178ab2c9cec6e0b95b\\DSC_0521.JPG"));
////        inventoryPhotos.put(171947256L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6643-1-f941eca96f5e873077fe3186631240fd\\DSC_0554.JPG"));
////        inventoryPhotos.put(171947259L, Paths.get("C:\\Users\\tvatt\\Desktop\\lego-collection-photos\\6653-1-739f32c367ed2aead1b4adcddb5a50a1\\DSC_0532.JPG"));
//        StopWatch timer = new StopWatch();
//        timer.start();
//        try {
//            inventoryPhotos.keySet().parallelStream().forEach(k -> {
//                Path p = inventoryPhotos.get(k);
//                Path resizedImagePath = imageScalingService.scale(p);
//                bricklinkWebService.uploadInventoryImage(k, resizedImagePath);
//            });
//        } finally {
//            bricklinkWebService.logout();
//            timer.stop();
//        }
//    }

    @Configuration
    static class MyTestConfiguration {
        @Bean
        public BricklinkWebProperties bricklinkWebProperties() {
            BricklinkWebProperties properties = new BricklinkWebProperties();
            properties.setPool(new BricklinkWebProperties.Pool());
            properties.getPool()
                    .setDefaultMaxPerRoute(20);
            properties.getPool()
                    .setMaxPerRoute(50);
            properties.getPool()
                    .setMaxTotal(200);
            return properties;
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }
}