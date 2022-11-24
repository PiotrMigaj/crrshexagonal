package pl.migibud.organisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlOrganisationQueryRepository extends OrganisationQueryRepository, JpaRepository<OrganisationEntity,Long>{
}

interface SqlOrganisationRepository extends JpaRepository<OrganisationEntity, Long> {
    Optional<OrganisationEntity> findByName(String name);
    Optional<OrganisationEntity> findByIdAndStatus(Long id,Organisation.Status status);
}

@Repository
@RequiredArgsConstructor
class OrganisationRepositoryImpl implements OrganisationRepository {

    private final SqlOrganisationRepository sqlOrganisationRepository;

    @Override
    public Optional<Organisation> findByName(String name) {
//        return sqlOrganisationRepository.findByName(name)
//                .flatMap(organisationEntity -> Optional.of(organisationEntity.toDomain()));
        return sqlOrganisationRepository.findByName(name)
                .map(OrganisationEntity::toDomain);
    }

    @Override
    public Optional<Organisation> findById(Long id) {
        return sqlOrganisationRepository.findById(id)
                .flatMap(organisationEntity -> Optional.of(organisationEntity.toDomain()));
    }

    @Override
    public Optional<Organisation> findByIdAndStatus(Long id, Organisation.Status status) {
        return sqlOrganisationRepository.findByIdAndStatus(id,status)
                .flatMap(organisationEntity -> Optional.of(organisationEntity.toDomain()));
    }

    @Override
    public Organisation save(Organisation organisation) {
        OrganisationEntity organisationEntity = OrganisationEntity.of(organisation);
        return sqlOrganisationRepository.save(organisationEntity).toDomain();
    }
}
