package com.rumahorbo.asyncwebclient.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestService {

    private final String BASE_URL = "https://dummyjson.com";
    private final WebClient client = WebClient.builder()
            .baseUrl(BASE_URL)
            .build();

    public <T> Mono<T> get(String url, Class<T> body) {
        Mono<T> response;
        response = this.client.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))
                .bodyToMono(body);
        return response;
    }

    public <T> Mono<T> post(String url, T requestBody, Class<T> responseBody) {
        Mono<T> response;
        response = this.client.post()
                .uri(url)
                .body(Mono.just(requestBody), requestBody.getClass())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))
                .bodyToMono(responseBody);
        return response;
    }
}
