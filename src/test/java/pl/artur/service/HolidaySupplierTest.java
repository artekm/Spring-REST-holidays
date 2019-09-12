package pl.artur.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public class HolidaySupplierTest {

	@Test
	public void prepareHolidaysForYear_returnsAll_13_Holidays_forGivenYear() {
		List<Holiday> returnedHolidays = new HolidaySupplier().prepareHolidaysForYear(2019);
		assertTrue(returnedHolidays.size() == 13);
	}

	@Test
	public void prepareHolidaysForPeriod_returnsAll_26_Holidays_forGivenTwoYears() {
		List<Holiday> returnedHolidays = new HolidaySupplier().prepareHolidaysForPeriod(LocalDate.of(2019, 1, 1),
				LocalDate.of(2020, 12, 31));
		assertTrue(returnedHolidays.size() == 26);
	}

	@Test
	public void prepareHolidaysForPeriod_returns_1_Holiday_forGivenOneDayPeriod() {
		List<Holiday> returnedHolidays = new HolidaySupplier().prepareHolidaysForPeriod(LocalDate.of(2019, 1, 1),
				LocalDate.of(2019, 1, 1));
		assertTrue(returnedHolidays.size() == 1);
		assertEquals(returnedHolidays.get(0).getISO(), LocalDate.of(2019, 1, 1));
	}
}
