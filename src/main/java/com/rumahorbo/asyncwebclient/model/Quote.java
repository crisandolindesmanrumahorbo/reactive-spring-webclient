package com.rumahorbo.asyncwebclient.model;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Quote implements Serializable {
    private int id;
    private String quote;
    private String author;
}
