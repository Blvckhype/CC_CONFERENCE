package pl.apilia.agenda;

import pl.apilia.agenda.model.Conference;
import pl.apilia.agenda.model.Track;

import java.util.List;

public class ClincaseAgenda {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid input argument amount");
        }
        ConferenceReader conferenceReader = new ConferenceReader();
        List<Conference> conferences = conferenceReader.readFromFile(args[0]);

        AgendaScheduler scheduler = new AgendaScheduler();
        List<Track> tracks = scheduler.schedule(conferences);

        AgendaWriter writer = new AgendaWriter();
        String content = writer.write(tracks);

        System.out.println(content);
    }
}
