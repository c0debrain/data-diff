package au.com.unohomeloans.challenge.controller;

import au.com.unohomeloans.challenge.service.DatesDiffCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
class DatesDiffCalculatorControllerTest {

    @Mock
    private DatesDiffCalculatorService datesDiffCalculatorService;

    private DatesDiffCalculatorController datesDiffCalculatorController;

    private static final String VALID_START_DATE = "01.01.2020";
    private static final String VALID_END_DATE = "03.01.2020";

    @BeforeEach
    public void setup() {
        this.datesDiffCalculatorController = new DatesDiffCalculatorController(datesDiffCalculatorService);
    }

    @Test
    public void shouldCalculateDatesDiff() {
        datesDiffCalculatorController.calculateDatesDiff(VALID_START_DATE, VALID_END_DATE);
        verify(datesDiffCalculatorService).calculateDatesDiff(VALID_START_DATE, VALID_END_DATE);
    }

}