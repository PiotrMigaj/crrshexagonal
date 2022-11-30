package pl.migibud.conference.room;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.migibud.organisation.Organisation;

@Setter
@Getter
@AllArgsConstructor
public class ConferenceRoom {

    private String id;
    private String name;
    private String identifier;
    private Integer level;
    private Boolean availability;
    private Integer numberOfSeats;
    private Organisation organisation;

    public ConferenceRoom(String name, String identifier, Integer level, Boolean availability, Integer numberOfSeats, Organisation organisation) {
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.availability = availability;
        this.numberOfSeats = numberOfSeats;
        this.organisation = organisation;
    }
}
