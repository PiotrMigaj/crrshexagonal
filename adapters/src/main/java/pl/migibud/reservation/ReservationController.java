package pl.migibud.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.reservation.dto.CreateReservationRequest;
import pl.migibud.reservation.dto.ReservationDto;
import pl.migibud.reservation.dto.ReservationView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
class ReservationController {

    private final ReservationFacade reservationFacade;
    private final ReservationQueryRepository reservationQueryRepository;

    @PostMapping
    ResponseEntity<ReservationDto> registerReservation(@RequestBody CreateReservationRequest request){
        ReservationDto reservationDto = reservationFacade.registerReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationDto);
    }

    @GetMapping
    ResponseEntity<List<ReservationView>> getAllReservations(){
        return ResponseEntity.ok(reservationQueryRepository.findAllBy());
    }

    @PutMapping("/{reservationId}")
    ResponseEntity<ReservationDto> updateReservation(@PathVariable String reservationId,@RequestBody CreateReservationRequest request){
        ReservationDto reservationDto = reservationFacade.updateReservation(reservationId, request);
        return ResponseEntity.accepted().body(reservationDto);
    }

    @PatchMapping("/{reservationId}")
    ResponseEntity<ReservationDto> updateReservationName(@PathVariable String reservationId,@RequestBody CreateReservationRequest request){
        ReservationDto reservationDto = reservationFacade.updateReservationName(reservationId, request);
        return ResponseEntity.accepted().body(reservationDto);
    }

    @DeleteMapping("/{reservationId}")
    ResponseEntity<?> deleteReservationById(@PathVariable String reservationId){
        reservationFacade.deleteReservationById(reservationId);
        return ResponseEntity.accepted().build();
    }
}
