package pl.migibud.conference.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.migibud.conference.room.dto.ConferenceRoomDto;
import pl.migibud.conference.room.dto.CreateConferenceRoomRequest;

@Slf4j
@RestController
@RequestMapping("/api/conferencerooms")
@RequiredArgsConstructor
class ConferenceRoomController {

    private final ConferenceRoomFacade conferenceRoomFacade;

    @PostMapping
    ResponseEntity<ConferenceRoomDto> registerConferenceRoom(@RequestBody CreateConferenceRoomRequest request){
        ConferenceRoomDto conferenceRoomDto = conferenceRoomFacade.registerConferenceRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomDto);
    }
}
