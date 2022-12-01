package pl.migibud.conference.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.conference.room.dto.ConferenceRoomDto;
import pl.migibud.conference.room.dto.ConferenceRoomView;
import pl.migibud.conference.room.dto.CreateConferenceRoomRequest;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/conferencerooms")
@RequiredArgsConstructor
class ConferenceRoomController {

    private final ConferenceRoomFacade conferenceRoomFacade;
    private final ConferenceRoomQueryRepository conferenceRoomQueryRepository;

    @PostMapping
    ResponseEntity<ConferenceRoomDto> registerConferenceRoom(@RequestBody CreateConferenceRoomRequest request){
        ConferenceRoomDto conferenceRoomDto = conferenceRoomFacade.registerConferenceRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomDto);
    }

    @GetMapping
    ResponseEntity<List<ConferenceRoomView>> getConferenceRooms(){
        return ResponseEntity.ok(conferenceRoomQueryRepository.findAllBy());
    }

    @GetMapping("/{id}")
    ResponseEntity<ConferenceRoomView> getConferenceRoomById(@PathVariable String id){
        ConferenceRoomView conferenceRoomView = conferenceRoomQueryRepository.findAllById(id).stream()
                .findFirst()
                .orElseThrow(() -> new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_NOT_FOUND));
        return ResponseEntity.ok(conferenceRoomView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteConferenceRoomById(@PathVariable String id){
        conferenceRoomFacade.deleteConferenceRoomById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<ConferenceRoomDto> updateConferenceRoom(
            @PathVariable String id,
            @RequestBody @Valid CreateConferenceRoomRequest request
    ){
        ConferenceRoomDto result = conferenceRoomFacade.updateConferenceRoom(id, request);
        return ResponseEntity.ok(result);
    }
}
