package au.com.unohomeloans.challenge.controller;

import au.com.unohomeloans.challenge.service.DatesDiffCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class DatesDiffCalculatorController {

    private final DatesDiffCalculatorService datesDiffCalculatorService;

    @Autowired
    public DatesDiffCalculatorController(final DatesDiffCalculatorService datesDiffCalculatorService) {
        this.datesDiffCalculatorService = datesDiffCalculatorService;
    }

    @RequestMapping("/dates-diff")
    public int calculateDatesDiff(@RequestParam final String startDate, @RequestParam String endDate) {
        return datesDiffCalculatorService.calculateDatesDiff(startDate, endDate);
    }

}
