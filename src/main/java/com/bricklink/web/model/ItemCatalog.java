package com.bricklink.web.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.Set;


@Data
@JacksonXmlRootElement(localName = "CATALOG")
public class ItemCatalog {
    @JacksonXmlProperty(localName = "ITEM")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Set<Item> items;
}
