package pl.artur.api;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.artur.service.Holiday;
import pl.artur.service.HolidaySupplier;

@RunWith(SpringRunner.class)
@WebMvcTest(HolidaysRest.class)
public class HolidaysRestTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private HolidaySupplier holidaySupplier;

	@Test
	public void getAnnual_returnsHolidays_forGivenYear() throws Exception {

		List<Holiday> holi = Arrays.asList(new Holiday(LocalDate.of(2019, 1, 1), "abcdef"));
		when(holidaySupplier.prepareHolidaysForYear(2019)).thenReturn(holi);

		mvc.perform(get("/getHolidays/year/2019")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.holidays[*]").isArray())
				.andExpect(jsonPath("$.holidays[0].name", is("abcdef")))
				.andExpect(jsonPath("$.holidays[0].iso", is("2019-01-01")));
	}

	@Test
	public void getPeriod_returnsHolidays_forGivenPeriod() throws Exception {

		List<Holiday> holi = Arrays.asList(new Holiday(LocalDate.of(2019, 1, 1), "qwerty"));
		LocalDate begin = LocalDate.of(2019, 1, 1);
		LocalDate end = LocalDate.of(2019, 2, 1);
		when(holidaySupplier.prepareHolidaysForPeriod(begin, end)).thenReturn(holi);

		mvc.perform(get("/getHolidays/begin/2019-01-01/end/2019-02-01")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.holidays[*]").isArray())
				.andExpect(jsonPath("$.holidays[0].name", is("qwerty")))
				.andExpect(jsonPath("$.holidays[0].iso", is("2019-01-01")));
	}
}
