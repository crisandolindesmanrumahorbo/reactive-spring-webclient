package com.rumahorbo.asyncwebclient.factory;

import com.rumahorbo.asyncwebclient.model.Quote;
import org.springframework.stereotype.Component;

@Component
public class Factory {

    public Quote constructQuote() {
        return Quote.builder()
                .id(1)
                .author("cris")
                .quote("yaudalah")
                .build();
    }
}
