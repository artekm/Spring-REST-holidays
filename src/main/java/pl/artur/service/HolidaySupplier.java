package pl.artur.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class HolidaySupplier {
	
	public List<Holiday> prepareHolidaysForPeriod(LocalDate start, LocalDate end) {
		List<Holiday> list = new ArrayList<>();
		for (int year = start.getYear(); year <= end.getYear(); year++) {
			list.addAll(prepareHolidaysForYear(year));
		}
		return list.stream().filter(holi -> !holi.getISO().isBefore(start) && !holi.getISO().isAfter(end))
				.collect(Collectors.toList());
	}

	public List<Holiday> prepareHolidaysForYear(int year) {
		List<Holiday> days = new ArrayList<>();
		days.add(new Holiday(LocalDate.of(year, 1, 1), "Nowy Rok"));
		days.add(new Holiday(LocalDate.of(year, 1, 6), "Trzech Królów"));
		days.add(new Holiday(LocalDate.of(year, 5, 1), "Święto Pracy"));
		days.add(new Holiday(LocalDate.of(year, 5, 3), "Święto Konstytucji"));
		days.add(new Holiday(LocalDate.of(year, 8, 15), "Wniebowzięcie NMP"));
		days.add(new Holiday(LocalDate.of(year, 11, 1), "Wszystkich Świętych"));
		days.add(new Holiday(LocalDate.of(year, 11, 11), "Dzień Niepodległości"));
		days.add(new Holiday(LocalDate.of(year, 12, 25), "Boże Narodzenie"));
		days.add(new Holiday(LocalDate.of(year, 12, 26), "Boże Narodzenie"));

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

		days.add(new Holiday(easter, "Wielkanoc"));
		days.add(new Holiday(easter.plusDays(1), "Wielkanoc"));
		days.add(new Holiday(easter.plusDays(49), "Zielone Świątki"));
		days.add(new Holiday(easter.plusDays(60), "Boże Ciało"));
		days.sort((h1, h2) -> h1.getISO().compareTo(h2.getISO()));
		return days;
	}
}
