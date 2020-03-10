package au.com.unohomeloans.challenge.service;

import au.com.unohomeloans.challenge.domain.DateCalculationItem;
import au.com.unohomeloans.challenge.repository.DateCalculationItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class DatesDiffCalculatorService {

    private static final SimpleDateFormat DAY_MONTH_YEAR_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private final DateCalculationItemRepository dateCalculationItemRepository;

    @Autowired
    public DatesDiffCalculatorService(final DateCalculationItemRepository dateCalculationItemRepository) {
        this.dateCalculationItemRepository = dateCalculationItemRepository;
    }

    public int calculateDatesDiff(final String startDate, String endDate) {

        int diffInDays = Integer.MIN_VALUE;

        try {

            Date firstDate = DAY_MONTH_YEAR_DATE_FORMAT.parse(startDate);
            Date secondDate = DAY_MONTH_YEAR_DATE_FORMAT.parse(endDate);

            long startTime = firstDate.getTime();
            long endTime = secondDate.getTime();

            if (startTime > endTime) {
                throw new IllegalArgumentException("Start date is greater than End date.");
            }

            diffInDays = ((int) ((endTime - startTime) / (1000 * 60 * 60 * 24))) - 1;
            log.info("Difference in days : " + diffInDays);

            diffInDays = diffInDays < 0 ? 0 : diffInDays;

            dateCalculationItemRepository.save(DateCalculationItem.builder()
                    .startDate(startDate)
                    .endDate(endDate)
                    .result(diffInDays)
                    .build());

        } catch (ParseException ex) {
            log.error("An error has happened when trying to calculate the dates diff.", ex);
        }

        return diffInDays;

    }

}