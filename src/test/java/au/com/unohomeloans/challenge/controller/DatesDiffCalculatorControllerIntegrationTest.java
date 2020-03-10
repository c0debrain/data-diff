package au.com.unohomeloans.challenge.controller;

import au.com.unohomeloans.challenge.repository.DateCalculationItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DatesDiffCalculatorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DateCalculationItemRepository dateCalculationItemRepository;

    private int previous = Integer.MAX_VALUE;

    @BeforeEach
    public void setup() {
        previous = dateCalculationItemRepository.countAllItems();
    }

    @Test
    public void shouldCalculateDatesDifferenceOnTheSameMonth() throws Exception {

        this.mockMvc.perform(get("/calculate/dates-diff?startDate=01.01.2020&endDate=03.01.2020"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", is(1)));

        int current = dateCalculationItemRepository.countAllItems();

        assertThat(current, is(greaterThan(previous)));

    }

    @Test
    public void shouldCalculateDatesDifferenceOnDifferentYears() throws Exception {

        this.mockMvc.perform(get("/calculate/dates-diff?startDate=01.01.2020&endDate=01.01.2030"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", is(3652)));

        int current = dateCalculationItemRepository.countAllItems();

        assertThat(current, is(greaterThan(previous)));
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenPassingStartDateGreaterThanEndDate() throws Exception {

        this.mockMvc.perform(get("/calculate/dates-diff?startDate=09.01.2020&endDate=03.01.2020"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.url", is("http://localhost/calculate/dates-diff")))
                .andExpect(jsonPath("$.ex", is("Start date is greater than End date.")));

        int current = dateCalculationItemRepository.countAllItems();

        assertThat(current, is(equalTo(previous)));

    }

}