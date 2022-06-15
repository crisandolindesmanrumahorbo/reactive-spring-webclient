package com.rumahorbo.asyncwebclient.factory;

import com.rumahorbo.asyncwebclient.model.Quote;
import com.rumahorbo.asyncwebclient.model.User;
import com.rumahorbo.asyncwebclient.model.UserTask;
import com.rumahorbo.asyncwebclient.model.Todo;
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
}
