package com.rumahorbo.asyncwebclient.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class UserQuote implements Serializable {
    private String username;
    private String quote;
}
