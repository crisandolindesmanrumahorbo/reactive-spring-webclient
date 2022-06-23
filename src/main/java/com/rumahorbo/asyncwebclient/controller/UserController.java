package com.rumahorbo.asyncwebclient.controller;

import com.rumahorbo.asyncwebclient.model.*;
import com.rumahorbo.asyncwebclient.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/web-client", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/sync/user-tasks/{todoId}")
    public Mono<UserTask> getUserTaskByTodoId(@PathVariable String todoId) {
        return userService.getUserTaskByTodoId(todoId);
    }

    @GetMapping("/async/quotes/random")
    public Mono<Quote> getRandomQuote() {
        return userService.getRandomQuote();
    }

    @GetMapping(value = "/async/user-quotes/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserQuote> getUserQuoteByUserId(@PathVariable String userId) {
        Mono<UserQuote> userQuoteByUserId = userService.getUserQuoteByUserId(userId);
        logger.info("from cont");
        return userQuoteByUserId;
    }

    @PostMapping(value = "/async/todos")
    public Mono<Todo> createTodo(@RequestBody Todo todo) {
        return userService.createTodo(todo);
    }

    @PostMapping(value = "/async/comments")
    public Mono<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        Mono<CommentDTO> comment = userService.createComment(commentDTO);
        logger.info("from cont");
        return comment;
    }

    @GetMapping(value = "/async/ok")
    public Mono<String> getOk() {
        return WebClient.create("http://localhost:8083")
                .get()
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping(value = "/sync/ok")
    public String getOkSync() {
        return WebClient.create("http://localhost:8083")
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
