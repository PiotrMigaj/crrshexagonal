package pl.migibud.reservation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.migibud.conference.room.ConferenceRoomRepository;
import pl.migibud.reservation.dto.CreateReservationRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationFacadeTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ConferenceRoomRepository conferenceRoomRepository;

    @InjectMocks
    ReservationFacade reservationFacade;


    @Test
    void givenReservationEndDateBeforeStartDate_whenRegisterReservation_thenThrowsException(){
        //given
        CreateReservationRequest request = new CreateReservationRequest(
                LocalDateTime.of(2022,12,10,10,30,00),
                LocalDateTime.of(2022,12,10,9,30,00),
                "res1",
                UUID.randomUUID().toString()
        );
        //when
        //then
        assertThatThrownBy(()->reservationFacade.registerReservation(request))
                .isNotNull()
                .isInstanceOf(ReservationException.class);
    }

}