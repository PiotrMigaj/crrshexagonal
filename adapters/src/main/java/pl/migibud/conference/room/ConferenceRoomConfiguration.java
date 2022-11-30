package pl.migibud.conference.room;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.migibud.organisation.OrganisationRepository;

@Configuration
class ConferenceRoomConfiguration {

    @Bean
    ConferenceRoomFacade conferenceRoomFacade(ConferenceRoomRepository conferenceRoomRepository, OrganisationRepository organisationRepository){
        return new ConferenceRoomFacade(conferenceRoomRepository,organisationRepository);
    }
}
