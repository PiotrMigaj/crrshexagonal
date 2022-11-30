package pl.migibud.conference.room;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.migibud.organisation.Organisation;
import pl.migibud.organisation.OrganisationEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoomEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String name;
    private String identifier;
    private Integer level;
    private Boolean availability;
    private Integer numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private OrganisationEntity organisationEntity;

    public static ConferenceRoomEntity of(ConferenceRoom conferenceRoom) {
        return new ConferenceRoomEntity(
                conferenceRoom.getId(),
                conferenceRoom.getName(),
                conferenceRoom.getIdentifier(),
                conferenceRoom.getLevel(),
                conferenceRoom.getAvailability(),
                conferenceRoom.getNumberOfSeats(),
                OrganisationEntity.of(conferenceRoom.getOrganisation())
        );
    }

    public ConferenceRoom toDomain() {
        return new ConferenceRoom(
                id,
                name,
                identifier,
                level,
                availability,
                numberOfSeats,
                organisationEntity.toDomain()
        );
    }
}
