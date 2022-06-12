package com.rumahorbo.asyncwebclient.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Todo implements Serializable {
    private String todo;
    private boolean completed;
    private int userId;
}
