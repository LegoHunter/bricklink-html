package com.bricklink.web.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import org.apache.commons.text.StringEscapeUtils;

@Data
public class Item {
    @JacksonXmlProperty(localName = "ITEMTYPE")
    private String itemType;


    @JacksonXmlProperty(localName = "ITEMID")
    private String itemId;

    @JacksonXmlProperty(localName = "ITEMNAME")
    private String itemName;

    @JacksonXmlProperty(localName = "CATEGORY")
    private Integer categoryId;

    @JacksonXmlProperty(localName = "ITEMYEAR")
    private String itemYear;

    public String getItemName() {
        return StringEscapeUtils.unescapeHtml4(itemName);
    }
}
