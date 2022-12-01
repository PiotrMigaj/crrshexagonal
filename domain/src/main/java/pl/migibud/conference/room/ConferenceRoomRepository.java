package pl.migibud.conference.room;

import java.util.Optional;

public interface ConferenceRoomRepository {
    boolean existsByNameAndOrganisation_Id(String name,Long organisationId);
    ConferenceRoom save(ConferenceRoom conferenceRoom);
    Optional<ConferenceRoom> findById(String id);
    void delete(ConferenceRoom conferenceRoom);
    Optional<ConferenceRoom> findByNameAndOrganisation_Id(String name,Long organisationId);
}
