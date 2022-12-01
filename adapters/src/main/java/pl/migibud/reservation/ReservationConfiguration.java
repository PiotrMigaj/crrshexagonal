package pl.migibud.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.migibud.conference.room.ConferenceRoomRepository;

@Configuration
@RequiredArgsConstructor
class ReservationConfiguration {

    @Bean
    ReservationFacade reservationFacade(ReservationRepository reservationRepository, ConferenceRoomRepository conferenceRoomRepository){
        return new ReservationFacade(reservationRepository,conferenceRoomRepository);
    }
}
