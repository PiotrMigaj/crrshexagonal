package pl.migibud.organisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.organisation.dto.CreateOrganisationRequest;
import pl.migibud.organisation.dto.OrganisationDto;
import pl.migibud.organisation.dto.OrganisationView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/organisations")
@RequiredArgsConstructor
class OrganisationController {

    private final OrganisationFacade organisationFacade;
    private final OrganisationQueryRepository organisationQueryRepository;

    @PostMapping
    ResponseEntity<OrganisationDto> registerOrganisation(@RequestBody CreateOrganisationRequest request){
        OrganisationDto organisation = organisationFacade.registerOrganisation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(organisation);
    }

    @GetMapping
    ResponseEntity<List<OrganisationView>> getAllOrganisations(){
        return ResponseEntity.ok(organisationQueryRepository.findAllByStatus(Organisation.Status.ACTIVE));
    }

    @GetMapping("/{name}")
    ResponseEntity<OrganisationView> getOrganisationByName(@PathVariable String name){
        OrganisationView organisationView = organisationQueryRepository.findByNameAndStatus(name, Organisation.Status.ACTIVE)
                .orElseThrow(() -> new OrganisationException(OrganisationError.ORGANISATION_NOT_FOUND));
        return ResponseEntity.ok(organisationView);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrganisation(@PathVariable Long id){
        organisationFacade.deleteOrganisation(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<OrganisationDto> updateOrganisation(
            @PathVariable Long id,
            @RequestBody CreateOrganisationRequest request
    ){
        OrganisationDto organisationDto = organisationFacade.updateOrganisation(id, request);
        return ResponseEntity.ok(organisationDto);
    }
}
