package pl.apilia.agenda;

import pl.apilia.agenda.model.Conference;
import pl.apilia.agenda.model.Track;

import java.util.List;

public class AgendaFacade {

    public String getAgendaPlan(String path) {
        ConferenceReader conferenceReader = new ConferenceReader();
        List<Conference> conferences = conferenceReader.readFromFile(path);
        if (conferences.size() == 0) {
            throw new IllegalArgumentException("Conference list is empty");
        }
        AgendaScheduler scheduler = new AgendaScheduler();
        List<Track> tracks = scheduler.schedule(conferences);

        AgendaWriter writer = new AgendaWriter();
        return writer.writeContentToString(tracks);
    }
}
