package com.bricklink.web.api;

import com.bricklink.api.html.model.v2.WantedItem;
import com.bricklink.api.html.model.v2.WantedList;
import com.bricklink.web.model.Item;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public interface BricklinkWebService {
    void authenticate();

    void logout();

    void updateInventoryCondition(Long blInventoryId, String invNew, String invComplete);

    void updateExtendedDescription(Long blInventoryId, String extendedDescription);

    void uploadInventoryImage(Long blInventoryId, Path imagePath);

    List<WantedList> getWantedLists();

    Set<WantedItem> getWantedListItems(final String name);

    Set<WantedItem> getWantedListItems(final Long id);

    Set<Item> getAllSetTypeCatalogItems();
    Set<Item> getAllBookTypeCatalogItems();
    Set<Item> getAllGearTypeCatalogItems();

    HttpClient getHttpClient();

    byte[] downloadWantedList(Long wantedListId, String wantedListName);

    void addOldNewFormField(ClassicRequestBuilder requestBuilder, Long inventoryId, String formFieldName, String oldValue, String newValue);

    void addPlaceholderOldNewFormField(ClassicRequestBuilder requestBuilder, Long inventoryId, String formFieldName);

    void setInventoryUpdateFormFieldsForConditionUpdate(ClassicRequestBuilder requestBuilder, Long inventoryId, String invNew, String invComplete);

    void setInventoryUpdateFormFieldsForExtendedDescriptionUpdate(ClassicRequestBuilder requestBuilder, Long inventoryId, String invExtended);
}
