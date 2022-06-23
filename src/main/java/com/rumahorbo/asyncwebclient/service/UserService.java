package com.rumahorbo.asyncwebclient.service;

import com.rumahorbo.asyncwebclient.model.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.time.Duration;

@Service
@AllArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final RestService restService;

    public Mono<Quote> getRandomQuote() {
        Mono<Quote> quoteMono = restService.get("/quotes/random", Quote.class);
        logger.info("from service");
        return quoteMono;
    }

    public Mono<UserTask> getUserTaskByTodoId(String todoId) {
        Mono<Todo> todoMono = restService.get("/todos/" + todoId, Todo.class);
        Todo todo = todoMono.block();
        return restService.get("/users/" + todo.getUserId(), User.class)
                .map(user -> UserTask.builder()
                        .todo(todo.getTodo())
                        .username(user.getUsername())
                        .build());
    }

    public Mono<UserQuote> getUserQuoteByUserId(String userId) {
        Mono<User> userMono = restService.get("/users/" + userId, User.class);
        logger.info("from service");
        return restService.get("/quotes/random", Quote.class)
                .zipWith(userMono)
                .map(tuple -> UserQuote.builder()
                        .username(tuple.getT2().getUsername())
                        .quote(tuple.getT1().getQuote())
                        .build());
    }

    public Mono<Todo> createTodo(Todo todo) {
        return restService.post("/todos/add", todo, Todo.class);
    }

    public Mono<CommentDTO> createComment(CommentDTO commentDTO) {
        User user = User.builder()
                .id(1)
                .username(commentDTO.getUsername())
                .build();
        Post post = Post.builder()
                .id(1)
                .title(commentDTO.getPostTitle())
                .userId(user.getId())
                .build();
        Comment comment = Comment.builder()
                .body(commentDTO.getCommentBody())
                .postId(post.getId())
                .userId(user.getId())
                .build();
        Mono<User> userMono = restService.post("/users/add", user, User.class).delayElement(Duration.ofSeconds(10));
        logger.info("from service");
        Mono<Post> postMono = restService.post("/posts/add", post, Post.class);
        Mono<Comment> commentMono = restService.post("/comments/add", comment, Comment.class);
        Mono<Tuple3<User, Post, Comment>> resultMono = Mono.zip(userMono, postMono, commentMono);
        return resultMono.map(objects -> CommentDTO.builder()
                .username(objects.getT1().getUsername())
                .postTitle(objects.getT2().getTitle())
                .commentBody(objects.getT3().getBody())
                .build());
    }
}
