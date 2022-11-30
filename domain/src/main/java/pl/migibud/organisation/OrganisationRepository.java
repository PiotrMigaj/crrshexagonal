package pl.migibud.organisation;

import java.util.Optional;

public interface OrganisationRepository {
    Optional<Organisation> findByName(String name);
    Optional<Organisation> findById(Long id);
    Optional<Organisation> findByIdAndStatus(Long id,Organisation.Status status);
    Organisation save(Organisation organisation);
}
