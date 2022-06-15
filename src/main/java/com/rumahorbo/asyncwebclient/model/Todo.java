package com.rumahorbo.asyncwebclient.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo implements Serializable {
    private String todo;
    private boolean completed;
    private int userId;
}
