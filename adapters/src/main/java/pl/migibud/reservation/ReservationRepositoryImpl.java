package pl.migibud.reservation;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

interface SqlReservationQueryRepository extends ReservationQueryRepository, JpaRepository<ReservationEntity,String>{
}

interface SqlReservationRepository extends JpaRepository<ReservationEntity,String>{
    Optional<ReservationEntity> findByConferenceRoomEntity_IdAndStartDateLessThanAndEndDateGreaterThan(String conferenceRoomEntityId, LocalDateTime endDate, LocalDateTime startDate);
}

@Repository
@RequiredArgsConstructor
class ReservationRepositoryImpl implements ReservationRepository {

    private final SqlReservationRepository sqlReservationRepository;
    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity save = sqlReservationRepository.save(ReservationEntity.of(reservation));
        return save.toDomain();
    }

    @Override
    public void delete(Reservation reservation) {
        sqlReservationRepository.delete(ReservationEntity.of(reservation));
    }

    @Override
    public Optional<Reservation> findById(String id) {
        return sqlReservationRepository.findById(id)
                .map(ReservationEntity::toDomain);
    }

    @Override
    public Optional<Reservation> findByConferenceRoom_IdAndStartDateLessThanAndEndDateGreaterThan(String conferenceRoomId, LocalDateTime endDate, LocalDateTime startDate) {
        return sqlReservationRepository
                .findByConferenceRoomEntity_IdAndStartDateLessThanAndEndDateGreaterThan(conferenceRoomId,endDate,startDate)
                .map(ReservationEntity::toDomain);
    }
}
