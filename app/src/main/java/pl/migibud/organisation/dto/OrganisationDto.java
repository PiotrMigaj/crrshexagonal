package pl.migibud.organisation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.organisation.Organisation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationDto implements OrganisationView {
    private Long id;
    private String name;

    public static OrganisationDto of(Organisation organisation) {
        return new OrganisationDto(
                organisation.getId(),
                organisation.getName()
        );
    }
}
