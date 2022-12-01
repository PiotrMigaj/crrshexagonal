package pl.migibud.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.migibud.conference.room.ConferenceRoom;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reservationName;
    private ConferenceRoom conferenceRoom;

    public Reservation(LocalDateTime startDate, LocalDateTime endDate, String reservationName, ConferenceRoom conferenceRoom) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationName = reservationName;
        this.conferenceRoom = conferenceRoom;
    }
}
