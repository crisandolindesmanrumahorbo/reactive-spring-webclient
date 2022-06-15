package com.rumahorbo.asyncwebclient.factory;

import com.rumahorbo.asyncwebclient.model.*;
import org.springframework.stereotype.Component;

@Component
public class Factory {

    public Quote constructQuote(String quote) {
        return Quote.builder()
                .id(1)
                .author("cris")
                .quote(quote)
                .build();
    }

    public User constructUser(String username) {
        return User.builder()
                .username(username)
                .build();
    }

    public Todo constructTodo(String todo, int userId) {
        return Todo.builder()
                .todo(todo)
                .userId(userId)
                .build();
    }

    public Post constructPost(int userId, String title) {
        return Post.builder()
                .userId(userId)
                .title(title)
                .build();
    }

    public Comment constructComment(int userId, int postId, String body, User user) {
        return Comment.builder()
                .userId(userId)
                .postId(postId)
                .body(body)
                .user(user)
                .build();
    }

    public CommentDTO constructCommentDTO(String username, String postTitle, String commentBody) {
        return CommentDTO.builder()
                .username(username)
                .postTitle(postTitle)
                .commentBody(commentBody)
                .build();
    }
}
