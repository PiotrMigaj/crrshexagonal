package pl.migibud.conference.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.migibud.conference.room.ConferenceRoom;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ConferenceRoomDto {
    private String id;
    private String name;
    private String identifier;
    private int level;
    private boolean availability;
    private int numberOfSeats;
    private Long organisationId;

}
