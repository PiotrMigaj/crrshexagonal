package pl.migibud.reservation.mapper;

import pl.migibud.conference.room.ConferenceRoom;
import pl.migibud.reservation.Reservation;
import pl.migibud.reservation.dto.CreateReservationRequest;
import pl.migibud.reservation.dto.ReservationDto;

public class ReservationMapper {

    private ReservationMapper() {
    }

    public static ReservationDto mapReservationToDto(Reservation reservation){
        return ReservationDto.builder()
                .id(reservation.getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .reservationName(reservation.getReservationName())
                .conferenceRoomId(reservation.getConferenceRoom().getId())
                .build();
    }

    public static Reservation mapRequestToReservation(CreateReservationRequest request, ConferenceRoom conferenceRoom){
        return new Reservation(
                request.getStartDate(),
                request.getEndDate(),
                request.getReservationName(),
                conferenceRoom
        );
    }
}
