package pl.migibud.reservation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.migibud.conference.room.ConferenceRoomEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reservationName;

    @ManyToOne
    @JoinColumn(name = "conference_room_id")
    private ConferenceRoomEntity conferenceRoomEntity;

    public static ReservationEntity of(Reservation reservation) {
        return new ReservationEntity(
                reservation.getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getReservationName(),
                ConferenceRoomEntity.of(reservation.getConferenceRoom())
        );
    }

    public Reservation toDomain() {
        return new Reservation(
                id,
                startDate,
                endDate,
                reservationName,
                conferenceRoomEntity.toDomain()
        );
    }
}
