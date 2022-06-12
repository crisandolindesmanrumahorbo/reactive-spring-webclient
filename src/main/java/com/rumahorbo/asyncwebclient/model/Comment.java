package com.rumahorbo.asyncwebclient.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {
    private int id;
    private String body;
    private int postId;
    private int userId;
    private User user;
}
