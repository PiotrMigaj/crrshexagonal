package pl.migibud.organisation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    @Enumerated(EnumType.STRING)
    private Organisation.Status status;

    public static OrganisationEntity of(Organisation organisation) {
        return new OrganisationEntity(
                organisation.getId(),
                organisation.getName(),
                organisation.getStatus()
        );
    }

    public Organisation toDomain() {
        return new Organisation(
                id,
                name,
                status
        );
    }

}
