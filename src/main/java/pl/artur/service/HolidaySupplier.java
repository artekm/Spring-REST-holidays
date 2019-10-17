package pl.artur.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class HolidaySupplier {

    public List<Holiday> prepareHolidaysForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Holiday> holidays = new ArrayList<>();
        for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
            holidays.addAll(prepareHolidaysForYear(year));
        }
        return holidays.stream()
                       .filter(holiday -> !holiday.getISO().isBefore(startDate) && !holiday.getISO().isAfter(endDate))
                       .collect(Collectors.toList());
    }

    public List<Holiday> prepareHolidaysForYear(int year) {
        List<Holiday> holidays = new ArrayList<>();
        holidays.add(new Holiday(LocalDate.of(year, 1, 1), "Nowy Rok"));
        holidays.add(new Holiday(LocalDate.of(year, 1, 6), "Trzech Królów"));
        holidays.add(new Holiday(LocalDate.of(year, 5, 1), "Święto Pracy"));
        holidays.add(new Holiday(LocalDate.of(year, 5, 3), "Święto Konstytucji"));
        holidays.add(new Holiday(LocalDate.of(year, 8, 15), "Wniebowzięcie NMP"));
        holidays.add(new Holiday(LocalDate.of(year, 11, 1), "Wszystkich Świętych"));
        holidays.add(new Holiday(LocalDate.of(year, 11, 11), "Dzień Niepodległości"));
        holidays.add(new Holiday(LocalDate.of(year, 12, 25), "Boże Narodzenie"));
        holidays.add(new Holiday(LocalDate.of(year, 12, 26), "Boże Narodzenie"));

        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (a * 19 + 24) % 30;
        int e = (2 * b + 4 * c + 6 * d + 5) % 7;
        if (d == 29 && e == 6)
            d -= 7;
        if (d == 28 && e == 6 && a > 10)
            d -= 7;
        LocalDate easter = LocalDate.of(year, 3, 22).plusDays(d + e);

        holidays.add(new Holiday(easter, "Wielkanoc"));
        holidays.add(new Holiday(easter.plusDays(1), "Wielkanoc"));
        holidays.add(new Holiday(easter.plusDays(49), "Zielone Świątki"));
        holidays.add(new Holiday(easter.plusDays(60), "Boże Ciało"));
        holidays.sort(Comparator.comparing(Holiday::getISO));
        return holidays;
    }
}
