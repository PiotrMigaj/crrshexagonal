package pl.migibud.organisation;

import pl.migibud.organisation.dto.OrganisationView;

import java.util.List;
import java.util.Optional;

interface OrganisationQueryRepository {
    List<OrganisationView> findAllByStatus(Organisation.Status status);
    Optional<OrganisationView> findByNameAndStatus(String name,Organisation.Status status);
}
