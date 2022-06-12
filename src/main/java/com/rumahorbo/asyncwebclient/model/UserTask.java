package com.rumahorbo.asyncwebclient.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class UserTask implements Serializable {
    private String username;
    private String todo;
}
