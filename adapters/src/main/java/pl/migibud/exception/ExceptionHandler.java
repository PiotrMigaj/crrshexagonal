package pl.migibud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.migibud.conference.room.ConferenceRoomError;
import pl.migibud.conference.room.ConferenceRoomException;
import pl.migibud.organisation.OrganisationError;
import pl.migibud.organisation.OrganisationException;
import pl.migibud.reservation.ReservationError;
import pl.migibud.reservation.ReservationException;

import java.util.Collections;

@RestControllerAdvice
class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = OrganisationException.class)
    ResponseEntity<ErrorInfo> handleOrganisationException(OrganisationException e){
        HttpStatus httpStatus = null;
        if (OrganisationError.ORGANISATION_NOT_FOUND.equals(e.getOrganisationError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (OrganisationError.ORGANISATION_ALREADY_EXISTS.equals(e.getOrganisationError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getOrganisationError().getMessage())));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ConferenceRoomException.class)
    ResponseEntity<ErrorInfo> handleConferenceRoomException(ConferenceRoomException e){
        HttpStatus httpStatus = null;
        if (ConferenceRoomError.CONFERENCE_ROOM_NOT_FOUND.equals(e.getConferenceRoomError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (ConferenceRoomError.CONFERENCE_ROOM_NAME_NOT_UNIQUE_FOR_ORGANISATION.equals(e.getConferenceRoomError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (ConferenceRoomError.CONFERENCE_ROOM_ALREADY_EXISTS.equals(e.getConferenceRoomError())){
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getConferenceRoomError().getMessage())));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ReservationException.class)
    ResponseEntity<ErrorInfo> handleReservationException(ReservationException e){
        HttpStatus httpStatus = null;
        if (ReservationError.RESERVATION_NOT_FOUND.equals(e.getReservationError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (ReservationError.RESERVATION_ALREADY_EXISTS.equals(e.getReservationError())){
            httpStatus = HttpStatus.CONFLICT;
        }
        if (ReservationError.RESERVATION_DURATION_TOO_SHORT.equals(e.getReservationError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (ReservationError.RESERVATION_DURATION_TOO_LONG.equals(e.getReservationError())){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (ReservationError.RESERVATION_DATE_TIME_MISMATCH.equals(e.getReservationError())){
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(Collections.singletonList(e.getReservationError().getMessage())));
    }
}
