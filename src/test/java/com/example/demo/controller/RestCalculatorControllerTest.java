package com.example.demo.controller;

import com.example.demo.controller.dto.ErrorDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnablePostgresIntegrationTest
class RestCalculatorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    static Stream<Arguments> dataForExceptionTest() {
        return Stream.of(
                arguments("/calculator/all",
                          "number",
                          Optional.of("123666456"),
                          HttpStatus.BAD_REQUEST,
                          "Input numbers contains 666"),

                arguments("/calculator/all",
                          "number",
                          Optional.of("99996"),
                          HttpStatus.BAD_REQUEST,
                          "Sum is equal to 42"),

                arguments("/calculator/all",
                          "number",
                          Optional.of(String.join("", Collections.nCopies(10, "9"))),
                          HttpStatus.BAD_REQUEST,
                          "Too big number for operating"),

                arguments("/calculator/all",
                          "number",
                          Optional.of(""),
                          HttpStatus.BAD_REQUEST,
                          "Input numbers have wrong format")
                        );
    }

    @Test
    @DataSet(cleanAfter = true)
    @ExpectedDataSet(value = "mathExpression_created.json")
    void successfullyReturnsStats() {
        //Act
        String actual = webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/all")
                        .queryParam("number", "123")
                        .build())
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        String expected = "{\"Avg\":2,\"Min\":1,\"Max\":3,\"Sum\":6}";

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("dataForExceptionTest")
    void exceptionTest(String path,
                       String paramName,
                       Optional<String> paramVal,
                       HttpStatus status,
                       String expectedMessage) {
        //Act
        ErrorDto actual = webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParamIfPresent(paramName, paramVal)
                        .build())
                .exchange()

                //Assert
                .expectStatus().isEqualTo(status)
                .expectBody(ErrorDto.class)
                .returnResult().getResponseBody();

        assertNotNull(actual);
        assertEquals(actual.getMessage(), expectedMessage);
    }
}