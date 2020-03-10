package au.com.unohomeloans.challenge.service;

import au.com.unohomeloans.challenge.domain.DateCalculationItem;
import au.com.unohomeloans.challenge.repository.DateCalculationItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@SpringBootTest
class DatesDiffCalculatorServiceTest {

    @Mock
    private DateCalculationItemRepository dateCalculationItemRepository;

    private DatesDiffCalculatorService datesDiffCalculatorService;

    @BeforeEach
    public void setup() {
        datesDiffCalculatorService = new DatesDiffCalculatorService(dateCalculationItemRepository);
    }

    @Test
    public void shouldCalculateDatesDifferenceOnTheSameMonth() {
        final String startDate = "01.01.2020";
        final String endDate = "03.01.2020";

        DateCalculationItem expected = DateCalculationItem.builder()
                .startDate(startDate)
                .endDate(endDate)
                .result(1)
                .build();

        assertThat(datesDiffCalculatorService.calculateDatesDiff(startDate, endDate), is(1));

        verify(dateCalculationItemRepository).save(expected);

    }

    @Test
    public void shouldCalculateDatesDifferenceOnDifferentYears() {
        final String startDate = "01.01.2020";
        final String endDate = "01.01.2030";

        DateCalculationItem expected = DateCalculationItem.builder()
                .startDate(startDate)
                .endDate(endDate)
                .result(3652)
                .build();

        assertThat(datesDiffCalculatorService.calculateDatesDiff(startDate, endDate), is(3652));
        verify(dateCalculationItemRepository).save(expected);
    }

    @Test
    public void shouldCalculateDatesDifferenceOnSameDay() {
        final String startDate = "01.01.2020";
        final String endDate = "01.01.2020";

        DateCalculationItem expected = DateCalculationItem.builder()
                .startDate(startDate)
                .endDate(endDate)
                .result(0)
                .build();

        assertThat(datesDiffCalculatorService.calculateDatesDiff(startDate, endDate), is(0));
        verify(dateCalculationItemRepository).save(expected);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenPassingStartDateGreaterThanEndDate() {
        final String startDate = "09.01.2020";
        final String endDate = "05.01.2020";

        assertThrows(IllegalArgumentException.class, () -> {
            datesDiffCalculatorService.calculateDatesDiff(startDate, endDate);
        });
        verifyNoInteractions(dateCalculationItemRepository);
    }

    @Test
    public void shouldHandleInvalidStartDate() {
        final String startDate = "eeeee";
        final String endDate = "05.01.2020";
        assertThat(datesDiffCalculatorService.calculateDatesDiff(startDate, endDate), is(Integer.MIN_VALUE));
        verifyNoInteractions(dateCalculationItemRepository);
    }

    @Test
    public void shouldThrowParseExceptionWhenPassingInvalidEndDate() {
        final String startDate = "01.01.2020";
        final String endDate = "ppppp";
        assertThat(datesDiffCalculatorService.calculateDatesDiff(startDate, endDate), is(Integer.MIN_VALUE));
        verifyNoInteractions(dateCalculationItemRepository);
    }

}