package pl.migibud.conference.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.conference.room.ConferenceRoom;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateConferenceRoomRequest {
    private String name;
    private String identifier;
    private Integer level;
    private Boolean availability;
    private Integer numberOfSeats;
    private Long organisationId;
}
