package au.com.unohomeloans.challenge.controller.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ErrorInfoTest {

    public static final String HTTP_LOCALHOST_CALCULATE_DATES_DIFF = "http://localhost/calculate/dates-diff";
    public static final String SOME_EXCEPTION_HAPPENED_HERE = "Some exception happened here.";

    @Test
    public void shouldCreateAnErrorInfoWithStringAndException() {

        ErrorInfo errorInfo = new ErrorInfo(HTTP_LOCALHOST_CALCULATE_DATES_DIFF,
                new Exception(SOME_EXCEPTION_HAPPENED_HERE));

        assertThat(errorInfo.getUrl(), is(HTTP_LOCALHOST_CALCULATE_DATES_DIFF));
        assertThat(errorInfo.getEx(), is(SOME_EXCEPTION_HAPPENED_HERE));

    }

}