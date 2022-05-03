package pl.apilia.agenda;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.apilia.agenda.model.Conference;
import pl.apilia.agenda.model.SessionType;
import pl.apilia.agenda.model.Track;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AgendaScheduler {

    public List<Track> schedule(List<Conference> conferences) {
        conferences.sort(Comparator.comparing(Conference::getDuration, Collections.reverseOrder()));
        List<Track> tracks = List.of(
                Track.newInstance(SessionType.MORNING_SESSION),
                Track.newInstance(SessionType.AFTERNOON_SESSION),
                Track.newInstance(SessionType.MORNING_SESSION),
                Track.newInstance(SessionType.AFTERNOON_SESSION)
        );
        for (Conference conference : conferences) {
            for (Track track : tracks) {
                if (track.getRemainingTime() - conference.getDuration() >= 0 && !conference.isPlanned()) {
                    track.addConference(conference);
                    conference.planned();
                    break;
                }
            }

        }
        return tracks;
    }
}
