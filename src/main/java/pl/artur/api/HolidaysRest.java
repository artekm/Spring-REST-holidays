package pl.artur.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.artur.service.Holiday;
import pl.artur.service.HolidaySupplier;

@RestController
public class HolidaysRest {

	@Autowired
	private HolidaySupplier holidaySupplier;

	@RequestMapping("/annual")
	public List<Holiday> getAnnual(@RequestParam("year") int year) {
		return holidaySupplier.prepareHolidaysForYear(year);
	}

	@RequestMapping("/period")
	public List<Holiday> getPeriod(@RequestParam("begin") String begin, @RequestParam("end") String end) {
		LocalDate beginDate = LocalDate.parse(begin);
		LocalDate endDate = LocalDate.parse(end);
		return holidaySupplier.prepareHolidaysForPeriod(beginDate, endDate);
	}
}
