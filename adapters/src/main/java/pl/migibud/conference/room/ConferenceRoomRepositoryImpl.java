package pl.migibud.conference.room;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlConferenceRoomQueryRepository extends ConferenceRoomQueryRepository,JpaRepository<ConferenceRoomEntity,String>{
}



interface SqlConferenceRoomRepository extends JpaRepository<ConferenceRoomEntity,String>{
    boolean existsByNameAndOrganisationEntity_Id(String name, Long id);
    Optional<ConferenceRoomEntity> findByNameAndOrganisationEntity_Id(String name, Long organisationEntityId);
}

@Repository
@RequiredArgsConstructor
class ConferenceRoomRepositoryImpl implements ConferenceRoomRepository {

    private final SqlConferenceRoomRepository sqlConferenceRoomRepository;
    @Override
    public boolean existsByNameAndOrganisation_Id(String name, Long organisationId) {
        return sqlConferenceRoomRepository.existsByNameAndOrganisationEntity_Id(name,organisationId);
    }

    @Override
    public ConferenceRoom save(ConferenceRoom conferenceRoom) {
        ConferenceRoomEntity conferenceRoomEntity = ConferenceRoomEntity.of(conferenceRoom);
        return sqlConferenceRoomRepository.save(conferenceRoomEntity).toDomain();
    }

    @Override
    public Optional<ConferenceRoom> findById(String id) {
        return sqlConferenceRoomRepository.findById(id)
                .map(ConferenceRoomEntity::toDomain);
    }

    @Override
    public void delete(ConferenceRoom conferenceRoom) {
        sqlConferenceRoomRepository.delete(ConferenceRoomEntity.of(conferenceRoom));
    }

    @Override
    public Optional<ConferenceRoom> findByNameAndOrganisation_Id(String name, Long organisationId) {
        return sqlConferenceRoomRepository.findByNameAndOrganisationEntity_Id(name,organisationId)
                .map(ConferenceRoomEntity::toDomain);
    }
}
