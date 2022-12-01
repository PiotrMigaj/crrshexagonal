package pl.migibud.conference.room;

import lombok.RequiredArgsConstructor;
import pl.migibud.conference.room.dto.ConferenceRoomDto;
import pl.migibud.conference.room.dto.CreateConferenceRoomRequest;
import pl.migibud.conference.room.mapper.ConferenceRoomMapper;
import pl.migibud.organisation.Organisation;
import pl.migibud.organisation.OrganisationError;
import pl.migibud.organisation.OrganisationException;
import pl.migibud.organisation.OrganisationRepository;

@RequiredArgsConstructor
class ConferenceRoomFacade {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganisationRepository organisationRepository;

    ConferenceRoomDto registerConferenceRoom(CreateConferenceRoomRequest request) {

        if (conferenceRoomRepository.existsByNameAndOrganisation_Id(request.getName(), request.getOrganisationId())) {
            throw new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_NAME_NOT_UNIQUE_FOR_ORGANISATION);
        }
        Organisation organisation = organisationRepository.findByIdAndStatus(request.getOrganisationId(), Organisation.Status.ACTIVE).orElseThrow(() -> new OrganisationException(OrganisationError.ORGANISATION_NOT_FOUND));

        ConferenceRoom conferenceRoom = ConferenceRoomMapper.mapRequestToConferenceRoom(request, organisation);
        ConferenceRoom save = conferenceRoomRepository.save(conferenceRoom);
        return ConferenceRoomMapper.mapConferenceRoomToDto(save);
    }

    void deleteConferenceRoomById(String conferenceRoomId) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(conferenceRoomId).orElseThrow(() -> new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_NOT_FOUND));
        conferenceRoomRepository.delete(conferenceRoom);
    }

    ConferenceRoomDto updateConferenceRoom(String id, CreateConferenceRoomRequest request) {

        conferenceRoomRepository.findByNameAndOrganisation_Id(request.getName(), request.getOrganisationId()).ifPresent(conferenceRoom -> {
            throw new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_ALREADY_EXISTS);
        });

        return conferenceRoomRepository.findById(id).map(conferenceRoom -> {
            conferenceRoom.setName(request.getName() != null ? request.getName() : conferenceRoom.getName());
            conferenceRoom.setIdentifier(request.getIdentifier() != null ? request.getIdentifier() : conferenceRoom.getIdentifier());
            conferenceRoom.setLevel(request.getLevel() != null ? request.getLevel() : conferenceRoom.getLevel());
            conferenceRoom.setAvailability(request.getAvailability() != null ? request.getAvailability() : conferenceRoom.getAvailability());
            conferenceRoom.setNumberOfSeats(request.getNumberOfSeats() != null ? request.getNumberOfSeats() : conferenceRoom.getNumberOfSeats());
            ConferenceRoom save = conferenceRoomRepository.save(conferenceRoom);
            return ConferenceRoomMapper.mapConferenceRoomToDto(save);
        }).orElseThrow(() -> new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_NOT_FOUND));
    }


}
