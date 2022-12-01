package pl.migibud.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationException extends RuntimeException{

	private ReservationError reservationError;
}
