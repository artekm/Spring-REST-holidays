package pl.artur.service;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HolidaySupplierTest {

    @Test
    public void prepareHolidaysForYear_returnsAll_13_Holidays_forGivenYear() {
        List<Holiday> returnedHolidays = new HolidaySupplier().prepareHolidaysForYear(2019);
        assertEquals(13, returnedHolidays.size());
    }

    @Test
    public void prepareHolidaysForPeriod_returnsAll_26_Holidays_forGivenTwoYears() {
        List<Holiday> returnedHolidays = new HolidaySupplier().prepareHolidaysForPeriod(LocalDate.of(2019, 1, 1),
                LocalDate.of(2020, 12, 31));
        assertEquals(26, returnedHolidays.size());
    }

    @Test
    public void prepareHolidaysForPeriod_returns_1_Holiday_forGivenOneDayPeriod() {
        List<Holiday> returnedHolidays = new HolidaySupplier().prepareHolidaysForPeriod(LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 1, 1));
        assertEquals(1, returnedHolidays.size());
        assertEquals(returnedHolidays.get(0).getISO(), LocalDate.of(2019, 1, 1));
    }
}
