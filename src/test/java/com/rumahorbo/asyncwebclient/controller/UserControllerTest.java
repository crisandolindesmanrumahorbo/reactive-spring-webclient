package com.rumahorbo.asyncwebclient.controller;

import com.rumahorbo.asyncwebclient.factory.Factory;
import com.rumahorbo.asyncwebclient.model.Quote;
import com.rumahorbo.asyncwebclient.model.Todo;
import com.rumahorbo.asyncwebclient.model.User;
import com.rumahorbo.asyncwebclient.service.RestService;
import com.rumahorbo.asyncwebclient.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
@Import(UserService.class)
@ComponentScan("com.rumahorbo.asyncwebclient.factory")
class UserControllerTest {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private RestService restService;

    @Autowired
    private Factory factory;

    @BeforeEach
    public void init() {
        Mockito.reset(restService);
    }

    @Test
    void getUserTaskByTodoId() {
        String username = "cris";
        String todoBody = "wash someone brain";
        int userId = 1;
        User user = factory.constructUser(username);
        Todo todo = factory.constructTodo(todoBody, userId);
        Mockito.when(this.restService.get("/todos/1", Todo.class)).thenReturn(Mono.just(todo));
        Mockito.when(this.restService.get("/users/1", User.class)).thenReturn(Mono.just(user));

        this.testClient
                .get()
                .uri("/web-client/sync/user-tasks/1")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("username").isEqualTo(username)
                .jsonPath("todo").isEqualTo(todoBody);
    }

    @Test
    void getRandomQuote() {
        String quoteBody = "yaudalah";
        Quote quote = factory.constructQuote(quoteBody);
        Mockito.when(this.restService.get("/quotes/random", Quote.class)).thenReturn(Mono.just(quote));

        this.testClient
                .get()
                .uri("/web-client/async/quotes/random")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("quote").isEqualTo(quoteBody);

    }

    @Test
    void getUserQuoteByUserId() {
        String username = "cris";
        String quoteBody = "yaudalah";
        User user = factory.constructUser(username);
        Quote quote = factory.constructQuote(quoteBody);
        Mockito.when(this.restService.get("/users/1", User.class)).thenReturn(Mono.just(user));
        Mockito.when(this.restService.get("/quotes/random", Quote.class)).thenReturn(Mono.just(quote));

        this.testClient
                .get()
                .uri("/web-client/async/user-quotes/1")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("username").isEqualTo(username)
                .jsonPath("quote").isEqualTo(quoteBody);
    }

    @Test
    void createTodo() {
    }

    @Test
    void createComment() {
    }
}
