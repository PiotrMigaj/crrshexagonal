package pl.migibud.conference.room.mapper;

import pl.migibud.conference.room.ConferenceRoom;
import pl.migibud.conference.room.dto.ConferenceRoomDto;
import pl.migibud.conference.room.dto.CreateConferenceRoomRequest;
import pl.migibud.organisation.Organisation;

public class ConferenceRoomMapper {

    public static ConferenceRoomDto mapConferenceRoomToDto(ConferenceRoom conferenceRoom){
        return ConferenceRoomDto.builder()
                .id(conferenceRoom.getId())
                .name(conferenceRoom.getName())
                .identifier(conferenceRoom.getIdentifier())
                .level(conferenceRoom.getLevel())
                .availability(conferenceRoom.getAvailability())
                .numberOfSeats(conferenceRoom.getNumberOfSeats())
                .organisationId(conferenceRoom.getOrganisation().getId())
                .build();
    }

    public static ConferenceRoom mapRequestToConferenceRoom(CreateConferenceRoomRequest request, Organisation organisation){
        return new ConferenceRoom(
                request.getName(),
                request.getIdentifier(),
                request.getLevel(),
                request.getAvailability(),
                request.getNumberOfSeats(),
                organisation
        );
    }
}
