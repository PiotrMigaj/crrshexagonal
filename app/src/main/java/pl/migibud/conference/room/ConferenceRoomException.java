package pl.migibud.conference.room;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConferenceRoomException extends RuntimeException{

	private ConferenceRoomError conferenceRoomError;
}
