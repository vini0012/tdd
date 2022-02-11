package com.example.tdd;

import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.example.tdd.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) //ponte entre os recursos do Spring com o Junit
public class BookingServiceTest {

    @Before
    public void setup() {
        LocalDate checkIn = LocalDate.parse("2022-02-11");
        LocalDate checkOut = LocalDate.parse("2022-02-21");
        BookingModel bookingModel = new BookingModel("1", "Marcos Vinícius", checkIn, checkOut, 2);

        when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
                .thenReturn(Optional.of(bookingModel));
    }

    @TestConfiguration
    static class BookinServiceTestConfiguration {

        @Bean
        public BookingService bookingService() {
            return new BookingService();
        }
    }

    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDaysCalculator() {
        String name = "Marcos Vinícius";
        int days = bookingService.daysCalculatorWithDatabase(name);

        assertEquals(days, 10);
    }
}
