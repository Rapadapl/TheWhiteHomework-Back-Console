package com.example.demo.controller;

import com.example.demo.entity.MathExpressions;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnablePostgresIntegrationTest
class LoggerCalculationControllerTest   {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    @DataSet(cleanAfter = true, cleanBefore = true, value = "mathExpression_4items.json")
    @ExpectedDataSet(value = "mathExpression_4items.json")
    void getCalculationResults4Items() {
        //Act
        List<String> actual = webTestClient
                .get()
                .uri("/calculator/log/successful/list")
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<String>>() {
                })
                .returnResult().getResponseBody();

        List<String> expected = new ArrayList<>(Arrays.asList(
                "{Avg=2, Min=1, Max=4, Sum=10}",
                "{Avg=5, Min=4, Max=7, Sum=22}",
                "{Avg=4, Min=3, Max=6, Sum=18}",
                "{Avg=4, Min=2, Max=6, Sum=20}"));

        assertEquals(expected, actual);
    }

    @Test
    @DataSet(cleanAfter = true, cleanBefore = true, value = "mathExpression_empty.json")
    @ExpectedDataSet(value = "mathExpression_empty.json")
    void getCalculationResultsFromEmptyTable() {
        //Act
        List<String> actual = webTestClient
                .get()
                .uri("/calculator/log/successful/list")
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<String>>() {
                })
                .returnResult().getResponseBody();

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }


    @ParameterizedTest
    @MethodSource("dataForSearchFunctionalityTest")
    @DataSet(cleanAfter = true, cleanBefore = true, value = "mathExpression_4items.json")
    @ExpectedDataSet(value = "mathExpression_4items.json")
    void searchFunctionalityTest(LinkedMultiValueMap<String, String> params, MathExpressions expected) {
        //Act
        List<MathExpressions> listOfActual = webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/log/successful/list/filter")
                        .queryParams(params)
                        .build())
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<MathExpressions>>() {
                })
                .returnResult().getResponseBody();

        assertNotNull(listOfActual);
        assertEquals(1, listOfActual.size());

        MathExpressions actual = listOfActual.get(0);

        assertThat(actual).usingRecursiveComparison().withStrictTypeChecking().isEqualTo(expected);
    }

    static Stream<Arguments> dataForSearchFunctionalityTest() {

        LinkedMultiValueMap<String, String> searchByDate = new LinkedMultiValueMap<String, String>() {{
            add("fromDate", "2021-08-12T18:39:32.636");
        }};

        LinkedMultiValueMap<String, String> searchByResultText = new LinkedMultiValueMap<String, String>() {{
            add("result", "Sum=20");
        }};

        LinkedMultiValueMap<String, String> searchByNumber = new LinkedMultiValueMap<String, String>() {{
            add("number", "1234");
        }};


        LinkedMultiValueMap<String, String> searchByResultTextAndDate = new LinkedMultiValueMap<String, String>() {{
            add("result", "Sum=10");
            add("toDate", "2021-08-12T18:39:33.636");
        }};

        MathExpressions firstExpectedResult = MathExpressions.builder()
                                                     .number(1234)
                                                     .result("{Avg=2, Min=1, Max=4, Sum=10}")
                                                     .id(UUID.fromString("01cc975d-7614-4610-800b-1fc8781e0a58"))
                                                     .creationDate(LocalDateTime.parse("2021-08-12T18:39:32.636"))
                                                     .build();

        MathExpressions secondExpectedResult = MathExpressions.builder()
                                                      .number(23456)
                                                      .result("{Avg=4, Min=2, Max=6, Sum=20}")
                                                      .id(UUID.fromString("073f8b30-9e79-4c81-b686-2dfe03455fde"))
                                                      .creationDate(LocalDateTime.parse("2021-08-06T19:59:12.393"))
                                                      .build();

        return Stream.of(
                arguments(
                        searchByDate,
                        firstExpectedResult),

                arguments(
                        searchByResultText,
                        secondExpectedResult),

                arguments(
                        searchByNumber,
                        firstExpectedResult),

                arguments(
                        searchByResultTextAndDate,
                        firstExpectedResult)
                        );
    }
}