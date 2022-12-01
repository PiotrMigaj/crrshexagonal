package pl.migibud.reservation;

import lombok.RequiredArgsConstructor;
import pl.migibud.conference.room.ConferenceRoom;
import pl.migibud.conference.room.ConferenceRoomError;
import pl.migibud.conference.room.ConferenceRoomException;
import pl.migibud.conference.room.ConferenceRoomRepository;
import pl.migibud.reservation.dto.CreateReservationRequest;
import pl.migibud.reservation.dto.ReservationDto;
import pl.migibud.reservation.mapper.ReservationMapper;

import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
class ReservationFacade {

    private final static long minimumDurationOfTimeReservationInMinutes = 15L;
    private final static long maximumDurationOfTimeReservationInMinutes = 120L;

    private final ReservationRepository reservationRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;

    ReservationDto registerReservation(CreateReservationRequest request) {
        validateDateTimeOfReservationRequest(request);

        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(request.getConferenceRoomId())
                .orElseThrow(() -> new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_NOT_FOUND));

        reservationRepository.findByConferenceRoom_IdAndStartDateLessThanAndEndDateGreaterThan(request.getConferenceRoomId(),request.getEndDate(),request.getStartDate())
                .ifPresent(reservation -> {
                    throw new ReservationException(ReservationError.RESERVATION_ALREADY_EXISTS);
                });

        Reservation save = reservationRepository.save(ReservationMapper.mapRequestToReservation(request, conferenceRoom));

        return ReservationMapper.mapReservationToDto(save);
    }

    ReservationDto updateReservation(String reservationId, CreateReservationRequest request){

        validateDateTimeOfReservationRequest(request);

        Reservation reservationToBeUpdated = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));

        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(request.getConferenceRoomId())
                .orElseThrow(() -> new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_NOT_FOUND));

        if (reservationToBeUpdated.getStartDate().isEqual(request.getStartDate())&&reservationToBeUpdated.getEndDate().isEqual(request.getEndDate())&&conferenceRoom.getId().equals(request.getConferenceRoomId()))
        {
            reservationToBeUpdated.setReservationName(request.getReservationName());
            Reservation save = reservationRepository.save(reservationToBeUpdated);
            return ReservationMapper.mapReservationToDto(save);
        }

        reservationRepository.findByConferenceRoom_IdAndStartDateLessThanAndEndDateGreaterThan(request.getConferenceRoomId(),request.getEndDate(),request.getStartDate())
                .ifPresent(reservation -> {
                    throw new ReservationException(ReservationError.RESERVATION_ALREADY_EXISTS);
                });

        reservationToBeUpdated.setReservationName(request.getReservationName());
        reservationToBeUpdated.setConferenceRoom(conferenceRoom);
        reservationToBeUpdated.setStartDate(request.getStartDate());
        reservationToBeUpdated.setEndDate(request.getEndDate());

        Reservation save = reservationRepository.save(reservationToBeUpdated);

        return ReservationMapper.mapReservationToDto(save);
    }

    ReservationDto updateReservationName(String reservationId, CreateReservationRequest request) {

        Reservation reservationToBeUpdated = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
        reservationToBeUpdated.setReservationName(request.getReservationName());

        Reservation save = reservationRepository.save(reservationToBeUpdated);

        return ReservationMapper.mapReservationToDto(save);
    }

    void deleteReservationById(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
        reservationRepository.delete(reservation);
    }

    private void validateDateTimeOfReservationRequest(CreateReservationRequest request) {
        long minutes = ChronoUnit.MINUTES.between(request.getStartDate(), request.getEndDate());

        if (minutes<0){
            throw new ReservationException(ReservationError.RESERVATION_DATE_TIME_MISMATCH);
        }
        if (minutes<minimumDurationOfTimeReservationInMinutes){
            throw new ReservationException(ReservationError.RESERVATION_DURATION_TOO_SHORT);
        }
        if (minutes>maximumDurationOfTimeReservationInMinutes){
            throw new ReservationException(ReservationError.RESERVATION_DURATION_TOO_LONG);
        }
    }
}
