package pl.migibud.conference.room;

import pl.migibud.conference.room.dto.ConferenceRoomView;

import java.util.List;
import java.util.Optional;

interface ConferenceRoomQueryRepository {
    List<ConferenceRoomView> findAllBy();
    List<ConferenceRoomView> findAllById(String id);
}
