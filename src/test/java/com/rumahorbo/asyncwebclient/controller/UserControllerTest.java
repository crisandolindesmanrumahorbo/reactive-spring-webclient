package com.rumahorbo.asyncwebclient.controller;

import com.rumahorbo.asyncwebclient.model.Quote;
import com.rumahorbo.asyncwebclient.service.RestService;
import com.rumahorbo.asyncwebclient.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
@Import(UserService.class)
class UserControllerTest {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private RestService restService;

    @Test
    void getUserTaskByTodoId() {

    }

    @Test
    void getRandomQuote() {
        Quote quote = Quote.builder()
                .id(1)
                .author("cris")
                .quote("yaudalah")
                .build();
        Mockito.when(this.restService.get("/quotes/random", Quote.class)).thenReturn(Mono.just(quote));
        this.testClient
                .get()
                .uri("/web-client/async/quotes/random")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("id").isEqualTo(1)
                .jsonPath("author").isEqualTo("cris")
                .jsonPath("quote").isEqualTo("yaudalah");

    }

    @Test
    void getUserQuoteByUserId() {
    }

    @Test
    void createTodo() {
    }

    @Test
    void createComment() {
    }
}
