package pl.artur.api;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.artur.service.HolidaySupplier;
import pl.artur.service.Holidays;

@RestController
@RequestMapping("/getHolidays")
public class HolidaysRest {

    @Autowired
    private HolidaySupplier holidaySupplier;

    @RequestMapping(value = "/year/{year}", produces = "application/json")
    public ResponseEntity<Holidays> getAnnual(@PathVariable int year) {
        return ResponseEntity.ok(new Holidays(holidaySupplier.prepareHolidaysForYear(year)));
    }

    @RequestMapping(value = "/begin/{begin}/end/{end}", produces = "application/json")
    public Holidays getPeriod(@PathVariable String begin, @PathVariable String end) {
        LocalDate beginDate = LocalDate.parse(begin);
        LocalDate endDate = LocalDate.parse(end);
        return new Holidays(holidaySupplier.prepareHolidaysForPeriod(beginDate, endDate));
    }
}
