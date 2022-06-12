package com.rumahorbo.asyncwebclient.controller;

import com.rumahorbo.asyncwebclient.model.*;
import com.rumahorbo.asyncwebclient.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web-client")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/sync/user-tasks/{todoId}")
    public Mono<UserTask> getUserTaskByTodoId(@PathVariable String todoId) {
        return userService.getUserTaskByTodoId(todoId);
    }

    @GetMapping("/async/quotes/random")
    public Mono<Quote> getRandomQuote() {
        return userService.getRandomQuote();
    }

    @GetMapping("/async/user-quotes/{userId}")
    public Mono<UserQuote> getUserQuoteByUserId(@PathVariable String userId) {
        return userService.getUserQuoteByUserId(userId);
    }

    @PostMapping(value = "/async/todos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Todo> createTodo(@RequestBody Todo todo) {
        return userService.createTodo(todo);
    }

    @PostMapping(value = "/async/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        return userService.createComment(commentDTO);
    }
}
