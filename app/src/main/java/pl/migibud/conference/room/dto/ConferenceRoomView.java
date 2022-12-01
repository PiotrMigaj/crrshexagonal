package pl.migibud.conference.room.dto;

public interface ConferenceRoomView {
    String getId();
    String getName();
    String getIdentifier();
    int getLevel();
    boolean isAvailability();
    int getNumberOfSeats();
    Long getOrganisationEntityId();
}
