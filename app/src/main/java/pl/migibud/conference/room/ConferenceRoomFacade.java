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

    public ConferenceRoomDto registerConferenceRoom(CreateConferenceRoomRequest request) {

        if (conferenceRoomRepository.existsByNameAndOrganisation_Id(request.getName(), request.getOrganisationId())) {
            throw new ConferenceRoomException(ConferenceRoomError.CONFERENCE_ROOM_NAME_NOT_UNIQUE_FOR_ORGANISATION);
        }
        Organisation organisation = organisationRepository.findByIdAndStatus(request.getOrganisationId(), Organisation.Status.ACTIVE)
                .orElseThrow(() -> new OrganisationException(OrganisationError.ORGANISATION_NOT_FOUND));

        ConferenceRoom conferenceRoom = ConferenceRoomMapper.mapRequestToConferenceRoom(request, organisation);
        ConferenceRoom save = conferenceRoomRepository.save(conferenceRoom);
        return ConferenceRoomMapper.mapConferenceRoomToDto(save);
    }

}
