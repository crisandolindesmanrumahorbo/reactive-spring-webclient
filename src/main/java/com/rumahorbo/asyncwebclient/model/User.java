package com.rumahorbo.asyncwebclient.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private int id;
    private String username;
}
