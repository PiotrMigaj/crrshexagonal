package pl.migibud.organisation;

import lombok.RequiredArgsConstructor;
import pl.migibud.organisation.dto.CreateOrganisationRequest;
import pl.migibud.organisation.dto.OrganisationDto;

@RequiredArgsConstructor
class OrganisationFacade {

    private final OrganisationRepository organisationRepository;

    OrganisationDto registerOrganisation(CreateOrganisationRequest request){
        return organisationRepository.findByName(request.getName())
                .map(organisation -> {
                    if (Organisation.Status.ACTIVE.equals(organisation.getStatus())){
                        throw new OrganisationException(OrganisationError.ORGANISATION_ALREADY_EXISTS);
                    }else {
                        organisation.setStatus(Organisation.Status.ACTIVE);
                        Organisation save = organisationRepository.save(organisation);
                        return OrganisationDto.of(save);
                    }
                }).orElseGet(()-> {
                    Organisation save = organisationRepository.save(new Organisation(request.getName(), Organisation.Status.ACTIVE));
                    return OrganisationDto.of(save);
                });
    }

    OrganisationDto updateOrganisation(Long organisationId,CreateOrganisationRequest request){
        Organisation organisation = organisationRepository.findByIdAndStatus(organisationId, Organisation.Status.ACTIVE)
                .orElseThrow(() -> new OrganisationException(OrganisationError.ORGANISATION_NOT_FOUND));
        organisationRepository.findByName(request.getName())
                .ifPresent(
                        o->{
                            throw new OrganisationException(OrganisationError.ORGANISATION_ALREADY_EXISTS);
                        }
                );
        organisation.setName(request.getName());
        Organisation save = organisationRepository.save(organisation);
        return OrganisationDto.of(save);
    }

    void deleteOrganisation(Long id){
        Organisation organisation = organisationRepository.findById(id)
                .orElseThrow(() -> new OrganisationException(OrganisationError.ORGANISATION_NOT_FOUND));
        organisation.delete();
        organisationRepository.save(organisation);
    }

}
