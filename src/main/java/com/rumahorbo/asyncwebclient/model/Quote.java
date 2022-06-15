package com.rumahorbo.asyncwebclient.model;


import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote implements Serializable {
    private int id;
    private String quote;
    private String author;
}
