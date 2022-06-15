package com.rumahorbo.asyncwebclient.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO implements Serializable {
    private String username;
    private String postTitle;
    private String commentBody;
}
